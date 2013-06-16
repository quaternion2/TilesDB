/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kenmcwilliams.employmentsystem.batch;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author ken
 */
public class CheckAPC {

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
            System.out.println(
                    "num: " + oppNumber
                    + "\t status: " + oppStatus
                    + "\t js: " + oppJSOpen
                    + "\ntitle: " + oppTitle
                    + "\ndescription: " + oppDescription
                    + "\nclosing: " + oppClosing + "\tposting: " + oppPosting + "\n");
        }
    }
}