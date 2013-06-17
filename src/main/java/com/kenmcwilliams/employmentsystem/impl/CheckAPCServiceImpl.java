/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.impl;

import com.kenmcwilliams.employmentsystem.orm.Apc;
import com.kenmcwilliams.employmentsystem.service.CheckAPCService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    @Override
    public void checkAPC() {
        openAPCPage();
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    public void openAPCPage() {
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

        for (; start < end - 1; start++) {//don't process last row
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
            log.log(Level.INFO, "Testing guid: {0}", oppGuid);
            if (isOpportunityAlreadySaved(oppGuid) == false) {
                log.info("new opportunity");

                //Apc apc = new Apc();
                //apc.setGuid(oppGuid);
                //apc.setStatus(oppStatus);
                //apc.setTitle(oppTitle);
                //apc.setPageRankingNumber(Integer.parseInt(oppNumber));
                //apc.setDescription(oppDescription);
                try {
                    closing = df.parse(oppClosing);
                    posting = df.parse(oppPosting);
                    //apc.setPosting(posting);
                    //apc.setClosing(closing);
                    Apc apc = new Apc(oppGuid, oppStatus, oppTitle, Integer.parseInt(oppNumber), oppDescription, closing, posting);
                    em.persist(apc);
                    log.info("Saved new Apc");
                } catch (ParseException ex) {
                    Logger.getLogger(CheckAPCServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                }


            } else {
                log.info("old opportunity");
            }

        }
        //following is better form but better get the form right before doing this
        //new Apc(oppGuid, oppStatus, oppTitle, Integer.parseInt(oppNumber), oppDescription, Date closing, Date posting)

    }

    boolean isOpportunityAlreadySaved(String guid) {
        boolean saved = false;
        Apc reference = em.find(Apc.class, guid);
        if (reference != null) {
            saved = true;
        }
        return saved;
    }
}
