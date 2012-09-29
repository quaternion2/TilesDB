<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./../style/style.css" rel="stylesheet" type="text/css">
        <script src="../script/jquery/1.8.1/jquery.min.js"></script>

        <title>Employment System - Resume Entry</title>

        <script> 
            var dateRangeRegex = /\W*([A-Za-z]{3})[A-Za-z]*\W+(\d+)\W+\W*([A-Za-z]{3})[A-Za-z]*\W+(\d+)\W*/i;
            var toServer = [];
            
            
            $(document).ready(function(){
                
                var appendCompany = function(){$($("#companyTemplate > .companyEntry").clone(true)).appendTo("#companies")};
                var toggleDetailVisibility = function(){
                    var target = $(this).closest(".companyEntry").children(".details");
                    if ($(target).hasClass("hidden")){
                        $(this).text("v");
                        var detailCount = $(target).children(".detail").length;
                        if(detailCount === 0){
                            alert("no companies");
                            //no details, add a new detail
                            $($("#companyTemplate .detail").clone(true)).appendTo(target);
                        }
                        $(target).removeClass("hidden");
                    }else{
                        $(target).addClass("hidden");
                        $(this).text(">");
                    }
                };
                
                //addDetail should be refactored to take a "detail div"
                //this way I don't need to change "this" to be the button
                var addDetail = function(){
                    var genericDetail = $(".templates .detail").get(0);
                    var genericDetailCopy = $(genericDetail).clone(true);
                    var companyDetails = $(this).closest(".details");
                    $(genericDetailCopy).appendTo(companyDetails);
                };
                var delDetail = function(){
                    //check if this is the last one, if so only clear the line?
                    //or possibly colapse the company, and opening the colapse
                    //restores one entry.
                    var companyContainer =  $(this).closest(".companyEntry").get(0);
                    var nDetail = $(companyContainer).find(".detail").length;
                    alert("nDetail: " + nDetail);
                    var companyDetail = $(this).closest(".detail");
                    $(companyDetail).remove();
                    if (nDetail === 1){
                        var expandButton = $(companyContainer).find(".expand");
                        alert(expandButton.tagName);
                        toggleVisibility.call(details);
                    }
                };
                
                //format date to consistent format function
                //INPUT Number: Two digit year
                //RETURNS Number: Four digit year
                var twoDigitYearToFour = function(year){
                    if(year > 50){
                        return year + 1900;
                    }else{
                        return year + 2000;
                    }
                }
                
                var processDate = function(strDateRange){        
                    var match = dateRangeRegex.exec(strDateRange);
                    //if 2 digit date change to 4 digit
                    var y1 = parseInt(match[2], 10);
                    var y2 = parseInt(match[4], 10);
                    if(y1 < 100){
                        y1 = twoDigitYearToFour(y1);
                    }
                    if(y2 < 100){
                        y2 = twoDigitYearToFour(y2);
                    }
                    var m1 = match[1].toLowerCase();
                    var m2 = match[3].toLowerCase();
                    m1 = m1.charAt(0).toUpperCase() + m1.slice(1);
                    m2 = m2.charAt(0).toUpperCase() + m2.slice(1);
                    //TODO: Make first letter caps and rest lower
                    return m1 + " " + y1 + " - " + m2 + " " + y2;
                }
                
                var monthToInt = function(mnth){
                    switch(mnth.toLowerCase()){
                        case "jan": return 1; 
                        case "feb": return 2;
                        case "mar": return 3;
                        case "apr": return 4;
                        case "may": return 5; 
                        case "jun": return 6;
                        case "jul": return 7;
                        case "aug": return 8;
                        case "sep": return 9;
                        case "oct": return 10;
                        case "nov": return 11;
                        case "dec": return 12;
                    }
                }
                
                var dateRangeToInt = function(strDateRange){
                    var match = dateRangeRegex.exec(strDateRange);
                    var m1 = monthToInt(match[1]);
                    var d1 = parseInt(match[2], 10)*100 + m1;
                    var m2 = monthToInt(match[3]);
                    var d2 = parseInt(match[4], 10)*100 + m2;
                    //consider if this is backwards...
                    return d1 * 1000000 + d2;
                }
                
                var comparator = function(a,b){
                    //alert(a.tagName);
                    var dra = $(a).val();
                    //alert(dra.tagName);
                    var drb = $(b).val();
                    if (dra.length === 0){
                        return 1;
                    }else if (drb.length === 0){
                        return -1;
                    }
                    //TODO: error for following is not checked
                    var drav = dateRangeToInt(dra);
                    //alert(drav);
                    var drbv = dateRangeToInt(drb);
                    //alert(drbv);
                    return drav > drbv ? -1 : 1;
                }
                
                var getSortable = function(){
                    return this.parentNode.parentNode;
                }
                
                //TODO: NOT WORKING!
                var doSortCompanies = function(){
                    //should use the sort plugin for this
                    $("#resume .companyDate").sortElements(comparator, getSortable);
                }
                
                var doSumTime = function(){
                    //$(".companyDate");
                    var months = calculateTotalMonths();
                    $("#total").val(months);
                }
                
                var doCompanyDateBlur = function(){
                    var dateRangeString = $(this).val();
                    //TODO: No validation or helpful warnings!
                    if(dateRangeString.length != 0){
                        var processedDateRangeString = processDate(dateRangeString);
                        $(this).val(processedDateRangeString);
                    }
                    //$(this).attr("dcode", );
                    
                    //TODO: if the format of companies is wrong I need to bail
                    doSortCompanies();
                    doSumTime();//sum requires dates be sorted
                }
                
                var doAddCompanyButton = function(){
                    appendCompany.apply(this,null);
                    $(".companyName").last().focus();
                }
                
                //only made to work on sorted and formated dates
                //Transform each date into an absolut value
                //put sorted start dates in one array and end dates in another
                var buildArrays = function(aFrom, aTo){
                    var match = dateRangeRegex.exec($(this).val());
                    //TODO: no validation done to make sure date is over 1990
                    var m1 = monthToInt(match[1]) - 1; //make jan 0
                    var d1 = (parseInt(match[2]) - 1900) * 12; //months since 1900
                    var m2 = monthToInt(match[3]) - 1;//make jan 0
                    var d2 = (parseInt(match[4]) - 1900) * 12;//months since 1900
                    var fromDate = d1 + m1;
                    var toDate = d2 + m2; 
                    aFrom.push(fromDate);
                    aTo.push(toDate);
                    //alert("fromDate = " + fromDate + " toDate = " + toDate);
                    return true;//TODO: return false on error
                }
                
                var calculateTotalMonths = function(){
                    //alert("Start calculate Total Months");
                    var total = 0;
                    var startDates = new Array();
                    var endDates = new Array();
                    var parameters = [startDates, endDates];
                    //build the tables of months
                    $("#resume .companyDate").each(function(){
                        //TODO: Change name of following fn as it only pushes dates into the arrays
                        if($(this).val().length != 0){ //skip empty values
                            if(buildArrays.apply(this, parameters) === false){
                                //TODO: did not parse date
                            };
                        }
                    });
                    //itterate over the table of months, summing the months
                    //if(startDates.length !== endDates.length){
                    //    alert("Sanity test: LENGTH NOT EQUAL");  
                    //}
                    //alert("startDates: " + startDates + " endDates: " + endDates);
                    var start = startDates[0];
                    var end = endDates[0];
                    var sum = end - start + 1;
                    for(var x = 1; x < startDates.length; x++){
                        //alert("In loop");
                        var temp_start = startDates[x];
                        var temp_end = endDates[x];
                        var new_start = true;
                        var new_end = true;
                        
                        if (isInRange(temp_start, start, end) === true){
                            //alert("case 1: ");
                            new_start = false;
                        }
                        if(isInRange(temp_end, start, end) === true){
                            //alert("case 2: ");
                            new_end = false;
                        }
                        //Date range is totally bracketed 
                        if (new_end === false && new_start == false){
                            //alert("case 3: ");
                            continue;
                            //Date range is partially bracketed
                        }else if (new_start == true && new_end == false){
                            //alert("case 4: ");
                            //find the total and subtract the overlap
                            start = temp_start;
                            sum += (end - start) + 1;//this line appears 3 times, should factor down
                            //NORMAL RANGE
                        }else if (new_start == false && new_end == true){
                            //alert("case 5: ");
                            end = temp_end;
                            sum += (end - start) + 1;
                        }else{
                            //, calcuate a normal range...
                            //this will not work... need different logic depending
                            //if one of the above values is being replaced
                            //alert("Case 6: Normal range");
                            start = temp_start;
                            end = temp_end;
                            sum += end - start + 1;
                        }
                    }
                    alert("End calculateTotalMonths, returning : " + sum);
                    return sum;
                }
                
                var isInRange = function(value, start, end){
                    if (value <= start && value >= end){
                        return true;
                    } else {
                        return false;
                    }
                }

                appendCompany();
                //company actions
                $("#addCompanyButton").click(doAddCompanyButton);
                $(".deleteButton").click(function(){$(this).parent().parent().remove()});
                $(".expand").click(toggleDetailVisibility);
                //detail actions
                
                $(".deleteDetail").click(delDetail);
                $(".addDetail").click(addDetail);
                $(".companyDate").blur(doCompanyDateBlur);
            });
            
            
            
            $(document).ready(function(){
                $("#saveResumeButton").click(saveResumeButton);
            });   
            var saveResumeButton = function(){
                alert("beam me up");  
                ContactInfo.firstName = $('#firstName').val();
                ContactInfo.lastName = $('#lastName').val();
                ContactInfo.streetAddress = $('#streetAddress').val();
                ContactInfo.city = $('#city').val();
                ContactInfo.province = $('#province').val();
                ContactInfo.postalCode = $('#postalCode').val();
                ContactInfo.phone = $('#phone').val();
                ContactInfo.email = $('#email').val();
                alert(JSON.stringify(ContactInfo));
                
            }          
            var ContactInfo = {
                firstName: "",
                lastName: "",
                streetAddress: "",
                city: "",
                province: "",
                postalCode: "",
                phone: "",
                email: ""//should add extra comma for cut and paste ie doesn't support it,
            };
            
            $(document).ready(function(){
                $("#saveWorkHistoryButton").click(saveWorkHistoryButton);
            });   
            var saveWorkHistoryButton = function(){
                toServer = []; //used to wipe out array
                $("#resume .companyEntry").each(magicalParsing);  
                alert(JSON.stringify(toServer));
            }   
            var magicalParsing = function(index, elem){
                //alert($($(elem).find(".companyName").get(0)).val());
                var who = new WorkHistoryObject();
                who.companyName = $($(elem).find(".companyName").get(0)).val();
                who.role = $($(elem).find(".companyRole").get(0)).val();
                who.dateWorked = $($(elem).find(".companyDate").get(0)).val();
                console.log($(elem).find(".detail .line").size());
                var tempArray = [];
                $(elem).find(".detail .line").each(
                function(index, elem){
                    tempArray.push($(elem).val());
                }          
            );
                who.details = tempArray;
                toServer.push(who);           
            } 
            var WorkHistoryObject = function(){
                companyName: "";
                role: "";
                dateWorked: ""; //should be something automatic if currently employed
                details: "";                     
            };
            //start date picker
            $(function() {
                $( "#startDatePicker" ).datepicker({
                    changeMonth: true,
                    changeYear: true
                });
            });
            //finish date picker
            $(function() {
                $( "#finishDatePicker" ).datepicker({
                    changeMonth: true,
                    changeYear: true
                });
            });
            
            
        </script>
    </head>


    <body>

        <%--  website header --%>
        <div id="wrapper">
            <div id="content">
                <nav>
                    <ul id="navBar">
                        <li><s:a cssClass="group" value="/index">Employment System</s:a></li>
                        <li><s:a cssClass="group" value="/enoch/listResumes001">Resume</s:a></li>
                        <li><s:a cssClass="group" value="/enoch/listQualificationForms002">Qualification</s:a></li>                       
                        <li><s:a cssClass="group" value="/enoch/designDocument">Design</s:a></li>              
                        <li><s:a cssClass="group" value="/index" title="time">
                                <script type="text/javascript">
                                    var mydate=new Date()
                                    var year=mydate.getFullYear()
                                    var day=mydate.getDay()
                                    var month=mydate.getMonth()
                                    var daym=mydate.getDate()
                                    //if the current date is less than 10, pad it.
                                    if (daym<10)
                                        daym="0"+daym
                                    var dayarray=new Array("Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday")
                                    var montharray=new Array("January","February","March","April","May","June","July","August","September","October","November","December")
                                    //write out the final results
                                    document.write(dayarray[day]+", "+montharray[month]+" "+daym+", "+year)
                                </script>  
                         </s:a></li>
                    </ul>
                </nav>
                <%--  end website header --%>

                <div><%-- website body --%>
                    <br>
                    <h1>Resume Entry</h1>  
                    <br>
                    <%--  Contact Info --%>
                    <div class="boxHeader"><h2 class="boxHeader">Contact Info</h2></div>
                    <div class="boxBody">
                        <div class="boxInterior"> 
                            <form>
                                <div class ="inputLine"><label class="labelLine">First Name:</label><input class="inputLine" type="text" placeholder="First Name" id="firstName"></div>
                                <div class ="inputLine"><label class="labelLine">Last Name:</label><input class="inputLine" type="text" placeholder="Last Name" id="lastName"></div>
                                <div class ="inputLine"><label class="labelLine">Street Address:</label><input class="inputLine" type="text" placeholder="Street Address" id="streetAddress"></div>
                                <div class ="inputLine"><label class="labelLine">City:</label><input class="inputLine" type="text" placeholder="City" id="city" ></div>
                                <div class ="inputLine"><label class="labelLine">Province:</label><input class="inputLine" type="text" placeholder="Province"  id="province"></div>
                                <div class ="inputLine"><label class="labelLine">Postal Code:</label><input class="inputLine" type="text" placeholder="Postal Code" id="postalCode" ></div>
                                <div class ="inputLine"><label class="labelLine">Phone:</label><input class="inputLine" type="text" placeholder="Phone"  id="phone"></div>
                                <div class ="inputLine"><label class="labelLine">Email:</label><input class="inputLine" type="text" placeholder="Email" id="email" ></div>
                                <button id="saveResumeButton" type="button">SAVE RESUME CONTACTS + JSON</button>
                            </form><br>
                        </div>
                    </div>
                    <br>
                    <%--  Experience --%>
                    <br>
                    <div class="boxHeader"><h2 class="boxHeader">Experience</h2></div>
                    <div class="boxBody">
                        <div class="boxInterior"> 
                            <div class="templates hidden">
                                <div id="companyTemplate">
                                    <div class="companyEntry">           
                                        <div class="companyHeader">
                                            <button class="expand" title="Expand/Collapse">&gt;</button>
                                            <button class="deleteButton" title="Delete Company">X</button>
                                            <input class="companyName" type="text" placeholder="Company Name">
                                            <input class="companyRole" type="text" placeholder="Role">
                                            <input class="companyDate" type="text" placeholder="MMM YY - MMM YY">
                                        </div>
                                        <div class="details hidden">
                                            <div class="detail">
                                                <input  class="isDetailSelected" type="checkbox"/>
                                                <button class="deleteDetail">Del</button>
                                                <span class="detailNumber"></span>
                                                <span class="right">
                                                    <input class="line" type="text" placeholder="Details">
                                                    <button class="addDetail">New Detail</button>
                                                </span>
                                            </div>
                                        </div>
                                    </div>
                                </div> 
                            </div>
                            <div id="resume">
                                <div id="companies">
                                </div>
                                <div class="companyHeader">
                                    <button id="addCompanyButton">New Company</button>
                                    <input contenteditable="false" id="total" class="right" type="text" value="" placeholder="Total Time">
                                </div>
                                <br><button id="saveWorkHistoryButton" type="button">SAVE WORK HISTORY + JSON</button>
                            </div>
                        </div>
                    </div>
                </div>
                <br>
                <%--  Education --%>
                <br>
                <div class="boxHeader"><h2 class="boxHeader">Education</h2></div>
                <div class="boxBody">
                    <div class="boxInterior"> 
                        <form>
                            <div class ="inputLine"><label class="labelLine">School Name:</label><input class="inputLine" type="text" placeholder="School Name" ></div>
                            <div class ="inputLine"><label class="labelLine">School City:</label><input class="inputLine" type="text" placeholder="School City"></div>
                            <div class ="inputLine"><label class="labelLine">School Province:</label><input class="inputLine" type="text" placeholder="School Province"></div>
                            <div class ="inputLine"><label class="labelLine">School Country:</label><input class="inputLine" type="text" placeholder="School Country"></div>
                            <div class ="inputLine"><label class="labelLine">Degree/Program:</label><input class="inputLine" type="text" placeholder="Degree/Program"></div>
                            <div class ="inputLine"><label class="labelLine">Presently Enrolled:</label><input class="inputLine" type="text" placeholder="Enrolled?"></div>
                            <button type="button">SAVE EDUCATION + JSON</button>
                        </form><br>
                    </div>
                </div>
                <br>
                <%--  Extra Skills --%>
                <br>
                <div class="boxHeader"><h2 class="boxHeader">Extra Skills</h2></div>
                <div class="boxBody">
                    <div class="boxInterior"> 
                        <form>
                            <div class ="inputLine"><label class="labelLine">Extra Skill:</label><input class="inputLine" type="text" placeholder="Extra Skill" ></div>
                            <button type="button">SAVE EXTRA SKILL + JSON</button>
                        </form><br>
                    </div>
                </div>
                <br>
            </div>


        </div><%-- end website body --%>

        <div> <%--  website footer --%>
            <br>
            <p class="siteFooter">
                <br>© 2012, Scrotum Solutions Inc.<br>
                All Rights Reserved.
            </p>
        </div> <%--  end website footer --%>
    </div>
</body>
</html>
