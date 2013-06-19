/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.impl;

import com.kenmcwilliams.employmentsystem.orm.Apc;
import com.kenmcwilliams.employmentsystem.service.CheckAPCService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ken
 */
@Transactional
public class CheckAPCServiceImpl implements CheckAPCService {

    private static final Logger log = Logger.getLogger(CheckAPCService.class.getName());
    @PersistenceContext
    private EntityManager em;
    private ArrayList<Apc> newOpportunities = new ArrayList();

    @Override
    public void checkAPC() {
        openAPCPage();
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void openAPCPage() {
        newOpportunities.clear();
        WebDriver driver = new FirefoxDriver();
        driver.get("http://vendor.purchasingconnection.ca/search.aspx");
        WebElement foundDropDown = driver.findElement(By.id("Content_Search_ctl00_searchCriteria_ResultsPerPage"));
        Select resultsPerPage = new Select(foundDropDown);
        resultsPerPage.selectByVisibleText("50");
        WebElement foundButton = driver.findElement(By.id("Content_Search_ctl00_StartBrowsing"));
        foundButton.click();
        //need to click on
        WebElement labelback = driver.findElement(By.cssSelector(".labelback td:nth-of-type(6) a"));
        labelback.click();
        String pageSource = driver.getPageSource();
        driver.quit();
        //end selenium involvement
        Document document = Jsoup.parseBodyFragment(pageSource);
        Elements rows = document.select("#Content_Search_ctl00_result_Opportunities tbody tr");
        int start = 1; //skip first row
        int end = rows.size();
        System.out.println("nRows: " + end);
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");
        Apc latestOpportunity = getLatestOpportuntiy();
        if (latestOpportunity == null) {
            log.info("latest opportunity not found setting dummy data");
            latestOpportunity = new Apc();
            Calendar cal = Calendar.getInstance();
            cal.set(1900, 1, 1); //set a time earlier than we could possibly use
            Date date = cal.getTime();
            latestOpportunity.setPosting(date);
        }else{
            log.info("latest opportunity has posting date of " + latestOpportunity.getPosting());
        }
        for (; start < end - 1; start++) {//don't process first OR last row
            Element row = rows.get(start);
            String oppNumber = row.select(".browserowLeftCell").text();
            String oppStatus = row.select("td:eq(1)").text();
            //this is the form of the following: javascript:window.open("/Opportunity.aspx?Guid=9EDA09FE-C67D-4773-B9A9-350A11F2831F&", "", "");undefined;
            //need a regex to exptract the "Guid=9EDA09FE-C67D-4773-B9A9-350A11F2831F" portion

            Element anchor = row.select(".opptitle a").first(); //.first().unwrap().attr("href");
            String oppJSOpen = anchor.attr("href");
            String oppTitle = row.select(".opptitle").text();
            String oppDescription = row.select(".oppdesc").text();
            String oppClosing = row.select("td:eq(4)").text();
            String oppPosting = row.select("td:eq(5)").text();
            String[] split = oppJSOpen.split("\"");
            String[] split1 = split[1].split("=");
            String split2 = split1[1];
            String oppGuid = split2.substring(0, split2.length() - 1);
            //System.out.println(
            //        "num: " + oppNumber
            //        + "\t status: " + oppStatus
            //        + "\t Guid: " + oppGuid
            //        + "\ntitle: " + oppTitle
            //        + "\ndescription: " + oppDescription
            //        + "\nclosing: " + oppClosing + "\tposting: " + oppPosting + "\n");
            Date closing, posting;
            try {
                closing = df.parse(oppClosing);
                posting = df.parse(oppPosting);
                if (latestOpportunity.getPosting().before(posting)) {
                    log.info("new opportunity");
                    Apc apc = new Apc(oppGuid, oppStatus, oppTitle, Integer.parseInt(oppNumber), oppDescription, closing, posting);
                    newOpportunities.add(apc);
                    em.persist(apc);
                    log.info("Saved new Apc");
                } else {
                    log.info("old opportunity");
                }
            } catch (ParseException ex) {
                Logger.getLogger(CheckAPCServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            //Send email       
        }
        //following is better form but better get the form right before doing this
        //new Apc(oppGuid, oppStatus, oppTitle, Integer.parseInt(oppNumber), oppDescription, Date closing, Date posting)
        sendEmail();
    }

    //TODO: Remove following method after review
    //don't need this with getLatestOpportunity
    //will leave just in case
    boolean isOpportunityAlreadySaved(String guid) {
        boolean saved = false;
        Apc reference = em.find(Apc.class, guid);
        if (reference != null) {
            saved = true;
        }
        return saved;
    }

    @Override
    public Apc getLatestOpportuntiy() {
        TypedQuery<Apc> query = em.createQuery("select a from Apc a order by posting desc", Apc.class);
        return query.setMaxResults(1).getSingleResult();
    }

    private void sendEmail() {

        String text;
        String subject;
        if (newOpportunities.size() > 0) {
            subject = "New on APC: " + newOpportunities.size() + " opportunities at " + new Date();
            StringBuilder sb = new StringBuilder();
            for (Apc apc : newOpportunities) {
                sb.append(" Title: ")
                        .append(apc.getTitle())
                        .append("<br><strong>Description<strong>: ")
                        .append(apc.getDescription())
                        .append("<br/><strong>Posting Date</strong>: ")
                        .append(apc.getPosting())
                        .append("<br/><strong>Closing Date</strong>: ")
                        .append(apc.getClosing())
                        .append("<br/><a href='http://vendor.purchasingconnection.ca/Opportunity.aspx?Guid=")
                        .append(apc.getGuid())
                        .append("'>Opportunity details</a>"
                        + "<br/><br/>");
            }
            text = sb.toString();
        } else {
            subject = "Nothing new on APC at " + new Date();
            text = "no new opportunites";
        }

        String to = "ken.mcwilliams@intellexsystems.com";
        String from = "ken.mcwilliams@shaw.ca";
        String host = "mail.shaw.ca";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        //properties.setProperty("mail.user", "myuser");
        //properties.setProperty("mail.password", "mypwd");
        Session session = Session.getDefaultInstance(properties);
        MimeMessage message = new MimeMessage(session);
        try {

            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO,
                    new InternetAddress(to));
            message.setSubject(subject);
            message.setContent(text, "text/html; charset=utf-8");
            Transport.send(message);
            log.info("Sent message successfully....");
        } catch (MessagingException ex) {
            Logger.getLogger(CheckAPCServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
