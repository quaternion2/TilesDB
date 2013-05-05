<%@taglib prefix="s" uri="/struts-tags"%>
<style>
    .hidden{
        display: none;
    }

    .templates{

    }

    .resumeUtil{
    }
    .companyEntry{

    }
    .companyHeader{
        width: 950px;
        background-color: steelblue;
        margin-top: 0.5em;
        margin-bottom: 0.5em;
    }
    .companyName{

    }
    .companyDate{
        float: right;
    }
    .details{

    }
    .detail{
        width: 950px;
        overflow:hidden;
        background-color: beige;
    }
    .detail input{
        width: 650px;
    }
    .right{
        float: right;
    }
    .isDetailSelected{
        display: none;
    }

    .strike{
        text-decoration: line-through;
    }
    .field input{
        width: 15em;                
    }
    .label {
        width: 15em;    
        display: inline-block;
    }
</style>
<script> 
    //TODO: When removing resume jobs, if it is the last job, remove but but put back a new blank one.
    //TODO: Provide switch between months and (yrs, mos).
    //TODO: Add include check box on the roles
    //TODO: Save the data to the DB for the particular use (select from drop down at this time)
    //TODO: Display total months at end of line
    //TODO: Put error css class on incorrect date formats
    //TODO: Mark that line as excluded from calculation 
    //TODO: Add "Include All button"
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
            console.log("doSortCompanies");
            doSortCompanies();
            console.log("doSumTime");
            doSumTime();//sum requires dates be sorted
            console.log("after");
            
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
            console.log("Start calculate Total Months");
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
            startDates = startDates.reverse(); //REVERSED the Dates on a whim, need to check this
            endDates = endDates.reverse();
            console.log("startDates: " + startDates + " endDates: " + endDates);
            var start = startDates[0];
            var end = endDates[0];
            var temp_start = 0;
            var temp_end = 0;
            var sum = 0;
            var new_start;
            var new_end;
            for(var x = 1; x < startDates.length; x++){
                console.log("In loop");
                temp_start = startDates[x];
                temp_end = endDates[x];
                new_start = true;
                new_end = true;
                        
                if (isInRange(temp_start, start, end) === true){
                    console.log("case 1: ");
                    new_start = false;
                }
                if(isInRange(temp_end, start, end) === true){
                    console.log("case 2: ");
                    new_end = false;
                }
                //Date range is totally bracketed, ignore
                if (new_end === false && new_start === false){
                    console.log("case 3: ");
                    continue;
                    //NOT POSSIBLE TODO: REMOVE THIS
                }else if (new_start == false && new_end === true){
                    console.log("case 5 - assign new: " + temp_end);
                    end = temp_end; //expand the end marker
                    //sum += (end - start) + 1;
                }else{
                    //calcuate a normal range...
                    console.log("Case 6: Normal range");
                    sum += dateDifference(start, end);
                    start = temp_start;
                    end = temp_end;
                }
            }
            //catch the last assignment if not acconted for from case 6
            if(temp_start == 0 && temp_end == 0){
                console.log("did not enter for loop sum single range");
                sum = dateDifference(start, end);
            }else{
                var value = dateDifference(start, end);
                console.log("final assignment, start: " + start + " temp_end: " + temp_end + " adding to sum: " + value);
                sum += value;
            }
            console.log("End calculateTotalMonths, returning : " + sum);
            return sum;
        }
        
        
        //Takes: Start THEN End dates.
        var dateDifference = function(start, end){
            console.log("start: " + start + " end: " + end + " diff: " + ((end - start) + 1));
            return (end - start) + 1;
        }
                
        var isInRange = function(value, start, end){
            if (value >= start && value <= end){
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
    //$(function() {
    //    $( "#startDatePicker" ).datepicker({
    //        changeMonth: true,
    //        changeYear: true
    //    });
    //});
    //finish date picker
    //$(function() {
    //    $( "#finishDatePicker" ).datepicker({
    //        changeMonth: true,
    //        changeYear: true
    //    });
    //});
            
            
</script>
<%--  website main page --%>
<h1>Resume Entry</h1>  

<br>
<div class="enoch">
    <s:form cssClass="framed" id="candidate-form" namespace="/candidate" action="search">
        <button type="button" id="search-button">Search</button>
        <button type="button" id="clear-button">Clear</button>
        <button type="button" id="new-candidate-button">Add Candidate</button>

        <div>
            <s:textfield name="model.fname" placeholder="First Name" title="First Name"/>
            <s:textfield name="model.mname" placeholder="Middle Name" title="Middle Name"/>
            <s:textfield  name="model.lname" placeholder="Last Name" title="Last Name"/>
        </div>
        <div>
            <s:textfield name="model.homePhone" placeholder="Day Phone" title="Day Phone"/>
            <s:textfield name="model.cellPhone" placeholder="Cell Phone" title="Cell Phone"/>
            <s:textfield name="model.otherPhone" placeholder="Other Phone" title="Other Phone"/>
        </div>
        <div>
            <s:textfield name="model.street" placeholder="Address" title="Address"/>
        </div>
        <div>
            <s:textfield name="model.city" placeholder="City" title="City"/>
            <s:textfield name="model.state" placeholder="Province" title="Province"/>
            <s:textfield name="model.poCode" placeholder="Area Code" title="Area Code"/>
        </div>
        <div>
            <s:textfield name="model.email" placeholder="Email" title="Email"/>
            <s:textfield name="model.altEmail" placeholder="Alt Email" title="Alt Email"/>
        </div>
        <div>
            <s:textfield name="model.skype" placeholder="Skype ID" title="Skype ID"/>
        </div> 
        <div>
            <s:textfield name="model.desiredRateHour" placeholder="Desired Rate 0.00" title="Desired Rate 0.00"/>
        </div>
    </s:form>
</div>
<div class="templates hidden">
    <div id="companyTemplate">
        <div class="companyEntry">           
            <div class="companyHeader">
                <button class="expand" title="Expand/Collapse">&gt;</button>
                <button class="deleteButton" title="Delete Company">X</button>
                <input class="companyName" type="text" placeholder="Company Name">
                <input class="companyRole" type="text" placeholder="Roles">
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
