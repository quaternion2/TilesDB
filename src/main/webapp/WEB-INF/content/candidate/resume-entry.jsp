<%@taglib prefix="s" uri="/struts-tags"%>
<style>
    #total{
        position: fixed;
        right: 10px;
        top: 275px;
    }

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
        /*float: right;*/
    }

    input[type="text"].line-months{
        width: 3em;
        float: right;
    }

    .details{

    }

    .detail{
        width: 950px;
        overflow:hidden;
        background-color: beige;
        margin-bottom:0.5em;
    }
    .detail input{
        width: 650px;
    }

    .select2-container {
        width: 260px;
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

    .field select{
        width: 15em;  
    }

    .label {
        width: 15em;    
        display: inline-block;
    }

    #modalAddBulkDetails{
        background-color:rgba(255,255,255,1);
    }
</style>
<script>
    //TODO: 11) Shift back based on "end date" (just like the way "restrict to x months" works) 
    var dateRangeRegex = /\W*([A-Za-z]{3})[A-Za-z]*\W+(\d+)\W+\W*([A-Za-z]{3})[A-Za-z]*\W+(\d+)\W*/i;
    var dateRegex = /\W*([A-Za-z]{3})[A-Za-z]*\W+(\d+)\W*/i;
    var toServer = [];
    var shortMonthNames = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
    var submissionDate;
    $(document).ready(function() {

        var crudUrl = function(entity, operation, id) {
            //TODO: check operation is a legal type
            //TODO: different types require different parameters (some don't take an id, some need an object)
            //TODO: should probably require an object and take the keys as arguments as required
            var appUrl = "<s:url includeContext="true"  forceAddSchemeHostAndPort="true" value="/crud"/>" + "/";
            return appUrl + entity + "/" + operation + "?id=" + id;
        }

        var crudDeleteCallback = function(entity, id) {
            if (id != null && id != undefined) {
                var deleteUrl = crudUrl(entity, "delete", id);
                alert("deleteUrl: " + deleteUrl);
                $.getJSON(deleteUrl);
            }
        }

        var appendCompany = function() {
            $($("#companyTemplate > .companyEntry").clone(true)).appendTo("#companies")
        };
        var toggleDetailVisibility = function() {
            var target = $(this).closest(".companyEntry").children(".details");
            if ($(target).hasClass("hidden")) {
                $(this).text("v");
                var detailCount = $(target).children(".detail").length;
                if (detailCount === 0) {
                    //alert("no companies");
                    //no details, add a new detail
                    $($("#companyTemplate .detail").clone(true)).appendTo(target);
                }
                $(target).removeClass("hidden");
            } else {
                $(target).addClass("hidden");
                $(this).text(">");
            }
        };
        //addDetail should be refactored to take a "detail div"
        //this way I don't need to change "this" to be the button
        var addDetail = function() {
            var genericDetail = $(".templates .detail").get(0);
            var genericDetailCopy = $(genericDetail).clone(true);
            var companyDetails = $(this).closest(".details");
            $(genericDetailCopy).appendTo(companyDetails);
            var lastDetail = $(companyDetails).find(".line:last").get(0);
            $(lastDetail).focus();
        };
        var delDetail = function() {
            //check if this is the last one, if so only clear the line?
            //or possibly colapse the company, and opening the colapse
            //restores one entry.
            var companyContainer = $(this).closest(".companyEntry").get(0);
            var nDetail = $(companyContainer).find(".detail").length;
            var id = $(this).closest(".detail").first().find(".line").attr("data-id");
            alert("id: " + id);
            crudDeleteCallback("position-point", id);
            var companyDetail = $(this).closest(".detail");
            $(companyDetail).remove();
            //TODO : following seems to be unreachable
            if (nDetail === 1) {
                var expandButton = $(companyContainer).find(".expand");
                alert(expandButton.tagName);
                toggleVisibility.call(details);
            }
        };
        //format date to consistent format function
        //INPUT Number: Two digit year
        //RETURNS Number: Four digit year
        var twoDigitYearToFour = function(year) {
            if (year > 50) {
                return year + 1900;
            } else {
                return year + 2000;
            }
        }

        var processDateRange = function(strDateRange) {
            var match = dateRangeRegex.exec(strDateRange);
            if (match.length < 4) {
                return;
            }
            //if 2 digit date change to 4 digit
            var y1 = parseInt(match[2], 10);
            var y2 = parseInt(match[4], 10);
            if (y1 < 100) {
                y1 = twoDigitYearToFour(y1);
            }
            if (y2 < 100) {
                y2 = twoDigitYearToFour(y2);
            }
            var m1 = match[1].toLowerCase();
            var m2 = match[3].toLowerCase();
            m1 = m1.charAt(0).toUpperCase() + m1.slice(1);
            m2 = m2.charAt(0).toUpperCase() + m2.slice(1);
            //TODO: Make first letter caps and rest lower
            return m1 + " " + y1 + " - " + m2 + " " + y2;
        }

        var dateRangeToInt = function(strDateRange) {
            var match = dateRangeRegex.exec(strDateRange);
            if (match.length < 4) {
                return;
            }
            var m1 = monthToInt(match[1]);
            var d1 = parseInt(match[2], 10) * 100 + m1;
            var m2 = monthToInt(match[3]);
            var d2 = parseInt(match[4], 10) * 100 + m2;
            //consider if this is backwards...
            return d1 * 1000000 + d2;
        }

        var comparator = function(a, b) {
            //alert(a.tagName);
            var dra = $(a).val();
            //alert(dra.tagName);
            var drb = $(b).val();
            if (dra.length === 0) {
                return 1;
            } else if (drb.length === 0) {
                return -1;
            }
            //TODO: error for following is not checked
            var drav = dateRangeToInt(dra);
            //alert(drav);
            var drbv = dateRangeToInt(drb);
            //alert(drbv);
            return drav > drbv ? -1 : 1;
        }

        var getSortable = function() {
            return this.parentNode.parentNode;
        }

        //TODO: NOT WORKING!
        var doSortCompanies = function() {
            //should use the sort plugin for this
            $("#resume .companyDate").sortElements(comparator, getSortable);
        }

        var doSumTime = function() {
            //$(".companyDate");
            var months = calculateTotalMonths();
            if ($("#yrsMosFrmtButton").is(':checked') == true) {
                console.log("yrsMosFrmtButton is checked");
                var total = months;
                var yrs = Math.floor(total / 12);
                var mos = total % 12;
                var yrsMosStr = "";
                if (yrs !== undefined && yrs > 0 && !isNaN(yrs)) {
                    yrsMosStr += "" + yrs + " yrs ";
                }
                if (mos > 0) {
                    yrsMosStr += "" + mos + " mos";
                }
                $("#total").val(yrsMosStr);
            } else {
                $("#total").val(months);
            }
        }

        //WARNING: Only call this from the date blur window!        
        var doCompanyDateBlur = function() {
            var dateRangeString = $(this).val();
            //TODO: No validation or helpful warnings!
            if (dateRangeString.length != 0) {
                var processedDateRangeString = processDateRange(dateRangeString);
                $(this).val(processedDateRangeString);
            }
            //$(this).attr("dcode", );
            console.log("calcMonthsInRange");
            var lineMonths = $(this).parent().children(".line-months")[0];
            var monthCount = calcMonthsInRange($(this).val());
            $(lineMonths).val(monthCount);
            if (monthCount < 1) {
                $(this).addClass("error");
            } else {
                $(this).removeClass("error");
            }

            //TODO: if the format of companies is wrong I need to bail
            console.log("doSortCompanies");
            doSortCompanies();
            console.log("doSumTime");
            doSumTime(); //sum requires dates be sorted
            console.log("after");
        }

        var calcMonthsInRange = function(dateString) {
            if (dateString) {
                var match = dateRangeRegex.exec(dateString);
            } else {
                return;
            }
            if (match.length < 4) {
                return;
            }
            //TODO: no validation done to make sure date is over 1900
            var m1 = monthToInt(match[1]) - 1; //make jan 0
            var d1 = (parseInt(match[2]) - 1900) * 12; //months since 1900
            var m2 = monthToInt(match[3]) - 1; //make jan 0
            var d2 = (parseInt(match[4]) - 1900) * 12; //months since 1900
            var fromDate = d1 + m1;
            var toDate = d2 + m2;
            return toDate - fromDate + 1;
        }

        var doAddCompanyButton = function() {
            appendCompany.apply(this, null);
            $(".companyName").last().focus();
        }

        //only made to work on sorted and formated dates
        //Transform each date into an absolut value
        //put sorted start dates in one array and end dates in another
        var buildArrays = function(aFrom, aTo) {
            var match = dateRangeRegex.exec($(this).val());
            //TODO: no validation done to make sure date is over 1900
            var m1 = monthToInt(match[1]) - 1; //make jan 0
            var d1 = (parseInt(match[2]) - 1900) * 12; //months since 1900
            var m2 = monthToInt(match[3]) - 1; //make jan 0
            var d2 = (parseInt(match[4]) - 1900) * 12; //months since 1900
            var fromDate = d1 + m1;
            var toDate = d2 + m2;
            aFrom.push(fromDate);
            aTo.push(toDate);
            //alert("fromDate = " + fromDate + " toDate = " + toDate);
            return true; //TODO: return false on error
        }


        //TODO: should pass in the company lines I want the calculation on. 
        var calculateTotalMonths = function() {
            console.log("Start calculate Total Months");
            var total = 0;
            var startDates = new Array();
            var endDates = new Array();
            var parameters = [startDates, endDates];
            //build the tables of months
            $("#resume .companyDate").each(function() {
                var checkBox = $(this).parent().children(".include-line")[0];
                if ($(checkBox).is(':checked')) {
                    if ($(this).val().length != 0) { //skip empty values
                        if (buildArrays.apply(this, parameters) === false) {
                            //TODO: did not parse date
                        };
                    }
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
            for (var x = 1; x < startDates.length; x++) {
                console.log("In loop");
                temp_start = startDates[x];
                temp_end = endDates[x];
                new_start = true;
                new_end = true;
                if (isInRange(temp_start, start, end) === true) {
                    console.log("case 1: ");
                    new_start = false;
                }
                if (isInRange(temp_end, start, end) === true) {
                    console.log("case 2: ");
                    new_end = false;
                }
                //Date range is totally bracketed, ignore
                if (new_end === false && new_start === false) {
                    console.log("case 3: ");
                    continue;
                    //NOT POSSIBLE TODO: REMOVE THIS
                } else if (new_start == false && new_end === true) {
                    console.log("case 5 - assign new: " + temp_end);
                    end = temp_end; //expand the end marker
                    //sum += (end - start) + 1;
                } else {
                    //calcuate a normal range...
                    console.log("Case 6: Normal range");
                    sum += dateDifference(start, end);
                    start = temp_start;
                    end = temp_end;
                }
            }
            //catch the last assignment if not accounted for from case 6
            if (temp_start == 0 && temp_end == 0) {
                console.log("did not enter for loop sum single range");
                sum = dateDifference(start, end);
            } else {
                var value = dateDifference(start, end);
                console.log("final assignment, start: " + start + " temp_end: " + temp_end + " adding to sum: " + value);
                sum += value;
            }
            console.log("End calculateTotalMonths, returning : " + sum);
            return sum;
        }

        function isNumber(n) {
            return !isNaN(parseFloat(n)) && isFinite(n);
        }

        //Takes: Start THEN End dates.
        var dateDifference = function(start, end) {
            var restrictByNMos = parseInt($("#limitByMonths").val()) - 1; //Should this be here?
            console.log("#restrictByNMos: " + restrictByNMos);
            var restrictionDifference = submissionDate - restrictByNMos;
            var difference;
            if (isNumber(restrictByNMos) === true) {
                console.log("submissionDate: " + submissionDate + " restrictionDifference: " + restrictionDifference + " restrictByNMos " + restrictByNMos);
                if (restrictionDifference < start) {
                    difference = (end - start) + 1; //normal range
                } else if (restrictionDifference >= start && restrictionDifference <= end) {
                    difference = (end - restrictionDifference) + 1;
                } else {
                    difference = 0; //restriction prevents sumation
                }

                if (difference < 1 || isNaN(difference)) {
                    difference = 0; //don't zero or negative
                }
            } else {
                difference = (end - start) + 1; //no restrictions
            }
            console.log("start: " + start + " end: " + end + " diff: " + difference);
            return difference;
        }

        var isInRange = function(value, start, end) {
            if (value >= start && value <= end) {
                return true;
            } else {
                return false;
            }
        }

        var checkAllCompanies = function() {
            $(".include-line").each(function() {
                this.checked = true;
            });
            doSumTime();
        }

        var unCheckAllCompanies = function() {
            $(".include-line").each(function() {
                this.checked = false;
            });
            doSumTime();
        }

        var deleteCompany = function() {
            console.log("deleteCompany");
            var companyHeader = $(this).parent();
            var id = $(companyHeader).find(".companyId").first().val();
            if (id != undefined) {
                alert("id " + id);
                crudDeleteCallback("position", id);
            }
            $(companyHeader).parent().remove();
            doSumTime();
        }

        var doUpdateSubmissionDate = function() {
            console.log("doUpdateSubmissionDate");
            var newDateString = processDate($("#submissionDate").val());
            $("#submissionDate").val(newDateString);
            doSumTime();
        }

        var monthToInt = function(mnth) {
            switch (mnth.toLowerCase()) {
                case "jan":
                    return 1;
                case "feb":
                    return 2;
                case "mar":
                    return 3;
                case "apr":
                    return 4;
                case "may":
                    return 5;
                case "jun":
                    return 6;
                case "jul":
                    return 7;
                case "aug":
                    return 8;
                case "sep":
                    return 9;
                case "oct":
                    return 10;
                case "nov":
                    return 11;
                case "dec":
                    return 12;
            }
        }

        //TODO: Highly coupled
        var processDate = function(strDate) {
            var match = dateRegex.exec(strDate);
            //if 2 digit date change to 4 digit
            var m1 = match[1].toLowerCase();
            m1 = m1.charAt(0).toUpperCase() + m1.slice(1);
            var y1 = parseInt(match[2], 10);
            if (y1 < 100) {
                y1 = twoDigitYearToFour(y1);
            }
            var nMonth = monthToInt(m1);
            submissionDate = (y1 - 1900) * 12 + (nMonth - 1); //need zero based month
            console.log("Updating submissionDate to: " + submissionDate);
            //TODO: Make first letter caps and rest lower
            return m1 + " " + y1;
        }

        var setSubmissionDate = function(mm, yyyy) {
            var mmm = shortMonthNames[mm];
            submissionDate = (yyyy - 1900) * 12 + mm; //jan is 0! 
            console.log("setting submission date to: " + submissionDate);
            $("#submissionDate").val(mmm + " " + yyyy);
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
        var saveWorkHistoryButton = function() {
            if (!$("#resume_name").val()) {
                alert($("#resume_name").val());
                alert("Resume must be named");
                return;
            }
            toServer = []; //used to wipe out array
            $("#resume .companyEntry").each(magicalParsing);
            var header = prepareHeaderToServer(); //example of data returned by f(): {"candidateId":"1","resumeId":"17","name":"res1:32"}
            var temp = {};
            temp["header"] = header;
            temp["roles"] = toServer;
            //toServer = [header, toServer];

            var roles = [];
            //puch role objects onto roles array
            var enbalm = {"header.candidateId": header.candidateId,
                "header.resumeId": header.resumeId,
                "header.name": header.name,
                "header.description": header.description
            };
            var doOnce = true;
            var i = 0;
            $.each(toServer, function(index, obj) {
                doOnce = true;
                console.log("obj.id[" + index + "]: " + !obj.id);
                console.log("obj.companyName[" + index + "]: " + !obj.companyName);
                console.log("obj.role[" + index + "]: " + !obj.role);
                console.log("obj.dateWorked[" + index + "]: " + !obj.dateWorked);
                console.log("obj.details.lenght[" + index + "]: " + obj.details.lenght);
                if (!obj.id && !obj.companyName && !obj.role && !obj.dateWorked && ((obj.details.lenght == undefined) || (obj.details.lenght == 0))) {
                    alert("company index: " + index + " is empty");
                    return true; //continue if company is null
                }
                $.each(obj, function(key, value) {
                    //if (){}
                    if (key == "id") {
                        enbalm["roles[" + index + "].id"] = value;
                    } else if (key == 'companyName') {
                        enbalm["roles[" + index + "].companyName"] = value;
                    } else if (key == 'role') {
                        enbalm["roles[" + index + "].role"] = value;
                    } else if (key == 'dateWorked') {
                        enbalm["roles[" + index + "].dateWorked"] = value;
                    } else if (key == 'details') {
                        if (doOnce == true) { //this is strange there should be a better way?
                            var j = 0;
                            $.each(value, function(index2, positionPoint) {
                                if (!positionPoint.description) {
                                    return true;
                                }
                                ; //continue, don't save empty descriptions
                                //can't count on index2 being continous due to continue on above line
                                enbalm["roles[" + index + "].details[" + j + "].id"] = positionPoint.id;
                                enbalm["roles[" + index + "].details[" + j + "].description"] = positionPoint.description;
                                enbalm["roles[" + index + "].details[" + j + "].rank"] = j;
                                j += 1;
                            });
                            doOnce = false;
                        }
                    }
                    i++;
                });
            });
            $.post("<s:url namespace="/candidate" action="add-resume"/>",
            enbalm,
            persistResumeCallback);
            alert(JSON.stringify(toServer));
        }

        var persistResumeCallback = function(data) {
            //TODO: do this
        }
        var prepareHeaderToServer = function() {
            var myobject = $("#headerInfo").serializeForm();
            console.log("prepareHeaderToServer:" + JSON.stringify(myobject));
            return myobject;
        }

        var magicalParsing = function(index, elem) {
            //alert($($(elem).find(".companyName").get(0)).val());
            var who = new WorkHistoryObject();
            who.id = $($(elem).find(".companyId").get(0)).val();
            who.companyName = $($(elem).find(".companyName").get(0)).val();
            who.role = $($(elem).find(".companyRole").get(0)).val();
            who.dateWorked = $($(elem).find(".companyDate").get(0)).val();
            console.log("#detail lines:" + $(elem).find(".detail .line").size());
            var tempArray = [];
            $(elem).find(".detail .line").each(
            function(index, elem) {
                var pp = new PositionPoint();
                pp.description = $(elem).val();
                pp.id = $(elem).attr("data-id");
                //pp.positionId //inferable...
                pp.rank = index;
                tempArray.push(pp);
            }
        );
            who.details = tempArray;
            toServer.push(who);
        }

        //TODO: need to add another object to represent details
        var WorkHistoryObject = function() {
            id:"";
            companyName: "";
            role: "";
            dateWorked: ""; //should be something automatic if currently employed
            details: "";
        };
        var PositionPoint = function() {
            id: "";
            description: "";
            rank: "";
            positionId: "";
        };
        appendCompany();
        //company actions
        $("#addCompanyButton").click(doAddCompanyButton);
        $(".deleteButton").click(deleteCompany);
        $(".expand").click(toggleDetailVisibility);
        //detail actions

        $(".deleteDetail").click(delDetail);
        $(".addDetail").click(addDetail);
        $(".companyDate").blur(doCompanyDateBlur);
        $("#select-all").click(checkAllCompanies);
        $("#select-none").click(unCheckAllCompanies);
        $(".include-line").click(doSumTime);
        $("#yrsMosFrmtButton").change(doSumTime);
        $("#limitByMonths").blur(doSumTime);
        $("#submissionDate").blur(doUpdateSubmissionDate);
        var today = new Date();
        var yyyy = today.getFullYear();
        var mm = today.getMonth();
        setSubmissionDate(mm, yyyy);
        $("#saveWorkHistoryButton").click(saveWorkHistoryButton);
        $(".companyDate").trigger("blur");
        //bulk add functionality***************************


        $(".addBulk").click(function() {
            var genericDetail = $(".templates .detail").get(0);
            //var genericDetailCopy = $(genericDetail).clone(true);
            var companyDetails = $(this).closest(".details");
            //$(genericDetailCopy).appendTo(companyDetails);


            $("#modalAddBulkDetails").dialog({
                autoOpen: false,
                height: 500,
                width: 750,
                modal: true,
                buttons: {
                    "Add Lines": function() {
                        //get content of bulk lines
                        var content = $("#bulkdetails").val();
                        //alert(content);
                        //split content on new lines
                        var allLines = content.split(/\r\n|[\n\v\f\r\x85\u2028\u2029]/); //this should get all lines including empty lines
                        //remove empty, null, undefined values from array (copy to new array)
                        var cleanedLines = [];
                        allLines.forEach(function(entry) {
                            if (entry) {
                                cleanedLines.push(entry);
                            }
                        });
                        //create new entries
                        cleanedLines.forEach(function(entry) {
                            //var div = $("<div>").text(entry);
                            var genericDetailCopy = $(genericDetail).clone(true);
                            $(genericDetailCopy).find(".line:last").val(entry.trim());
                            $(genericDetailCopy).appendTo(companyDetails);
                            //$("#target1").append(div);
                        });
                        var lastDetail = $(companyDetails).find(".line:last").get(0);
                        $(lastDetail).focus();
                    },
                    Cancel: function() {
                        $(this).dialog("close");
                    }
                },
                close: function() {
                }
            });
            $("#modalAddBulkDetails").dialog("open");
        });
        $(function() {
            $(".details").sortable({
                connectWith: ".details"
            });
        });

        $("#tab_include").click(function() {
            if ($(this).attr("checked") == "checked") {
                $(".include-line").removeAttr('disabled');
                $(".include-line").removeAttr('disabled');
            } else {
                $(".include-line").attr('disabled', 'disabled');
            }
        });

        $("#tab_expand").click(function() {
            if ($(this).attr("checked") == "checked") {
                $(".expand").removeAttr('disabled');
            } else {
                $(".expand").attr('disabled', 'disabled');
            }
        });

        $("#tab_delete").click(function() {
            if ($(this).attr("checked") == "checked") {
                $(".deleteButton").removeAttr('disabled');
            } else {
                $(".deleteButton").attr('disabled', 'disabled');
            }
        });

        $("#tab_name").click(function() {
            if ($(this).attr("checked") == "checked") {
                $(".companyName").removeAttr('disabled');
            } else {
                $(".companyName").attr('disabled', 'disabled');
            }
        });

        $("#tab_roles").click(function() {
            if ($(this).attr("checked") == "checked") {
                $(".companyRole").removeAttr('disabled');
                $(".companyRole").parent().removeClass("hidden");
            } else {
                $(".companyRole").attr('disabled', 'disabled');
                $(".companyRole").parent().addClass("hidden");
            }
        });

        $("#tab_location").click(function() {
            if ($(this).attr("checked") == "checked") {
                $(".companyLocation").removeAttr('disabled');
                $(".companyLocation").parent().removeClass("hidden");
            } else {
                $(".companyLocation").attr('disabled', 'disabled');
                $(".companyLocation").parent().addClass("hidden");
            }
        });

        $("#tab_date").click(function() {
            if ($(this).attr("checked") == "checked") {
                $(".companyDate").removeAttr('disabled');
            } else {
                $(".companyDate").attr('disabled', 'disabled');
            }
        });


        $(".companyRole").select2({
            multiple: true,
            width: 'off',
            dropdownAutoWidth: false,
            placeholder: "Job Roles",
            minimumInputLength: 1,
            ajax: {
                url: "http://localhost:8080/EmplymentSystem/crud/job-tags/page",
                dataType: 'json',
                data: function(term, page) {
                    return {
                        start: 0, // search term
                        count: 20
                    };
                },
                results: function(data, page) { // parse the results into the format expected by Select2.
                    return {results: data};
                }
            },
            formatResult: function(data) {
                return "<div class='select2-user-result'>" + data.name + "</div>";
            },
            formatSelection: function(data, container) {
                return "<div class='select2-user-selection' data-id='" + data.id + "'>" + data.name + "</div>";
            },
            id: function(data) {
                return data.name;
            }
        });

        $("#tabs").accordion({
            collapsible: true,
            active: false
        });
    });
</script>
<%--  website main page --%>
<h1>Resume Entry for <s:property value="candidate.fname"/> <s:property value="candidate.lname"/></h1>  

<div class="templates hidden">
    <div id="companyTemplate">
        <div class="companyEntry">           
            <div class="companyHeader">
                <span class="include-line-span">
                    <input autocomplete="off" class="include-line" type="checkbox" name="include" checked="checked"/>
                </span>
                <button class="expand" title="Expand/Collapse">&gt;</button>
                <button class="deleteButton" title="Delete Company">X</button>
                <input autocomplete="off" class="companyName" type="text" placeholder="Company Name"/>
                <input autocomplete="off" class="companyDate" type="text" placeholder="MMM YY - MMM YY"/>
                <input autocomplete="off" class="line-months" disabled="disabled" type="text" placeholder=""/>
                <div class="hidden">
                    <input autocomplete="off" class="companyRole" type="hidden" placeholder="Roles" multiple="multile"/>
                </div>
                <div class="hidden companyLocation">
                    <select class="country">
                        <option>Canada</option>
                        <option>USA</option>
                    </select>
                    <select class="state">
                        <option>a</option>
                        <option>b</option>
                    </select>
                    <select class="city" placeholder="Location"></select>
                </div>
            </div>
            <ul class="details hidden">
                <li class="detail">
                    <input autocomplete="off"  class="isDetailSelected" type="checkbox" checked="checked"/>
                    <button class="deleteDetail">Del</button>
                    <span class="detailNumber"></span>
                    <span class="right">
                        <input autocomplete="off" class="line" type="text" placeholder="Details">
                        <button class="addDetail">New</button>
                        <button class="addBulk">Bulk</button>
                    </span>
                </li>
            </ul>
        </div>
    </div> 
</div>

<form id="headerInfo">
    <s:hidden name="candidateId" value="%{candidate.id}"/>
    <s:hidden name="resumeId" value="%{resume.id}"/>
    Name: <input autocomplete="off" id="resume_name" name="name" type="text" value="<s:property value="resume.name"/>"/>
    (TODO assign to opportunity)
</form>

<br/>
<div id="resume" class="">
    <div id="options">
        <button id="select-all" type="button">All</button> 
        <button id="select-none" type="button">None</button> 
        <input autocomplete="off" id="yrsMosFrmtButton" type="checkbox" name="selectAll"  checked="checked"/>Yrs, Mos format
        <span style="float: right;"> 
            Restrict to x months: <input id="limitByMonths" type="text" name="selectAll"/>
            to date: <input id="submissionDate" type="text" name="selectAll" placeholder="MMM YY"/>
        </span>
    </div>

    <div id="tabs">
        <h3>Control Resume View</h3>
        <div>
            <div><span style="display: inline-block;width:10em;">line-selected:</span><input id="tab_include" type="checkbox" checked="checked"/></div>
            <div><span style="display: inline-block;width:10em;">details:</span><input id="tab_expand" type="checkbox" checked="checked"/></div>
            <div><span style="display: inline-block;width:10em;">delete:</span><input id="tab_delete" type="checkbox" checked="checked"/></div>
            <div><span style="display: inline-block;width:10em;">company:</span><input id="tab_name" type="checkbox" checked="checked"/></div>
            <div><span style="display: inline-block;width:10em;">roles:</span><input id="tab_roles" type="checkbox"/></div>
            <div><span style="display: inline-block;width:10em;">location:</span><input id="tab_location" type="checkbox"/></div>
            <div><span style="display: inline-block;width:10em;">date:</span><input id="tab_date" type="checkbox" checked="checked"/></div>
        </div>
    </div>
    <div id="companies">
        <s:if test="resume != null">
            <s:iterator value="resume.positionCollection">
                <div class="companyEntry">           
                    <div class="companyHeader">
                        <input autocomplete="off" class="companyId" type="hidden" value="<s:property value="id"/>"/>
                        <input autocomplete="off" class="include-line" type="checkbox" name="include" checked="checked"/>
                        <button class="expand" title="Expand/Collapse">&gt;</button>
                        <button class="deleteButton" title="Delete Company">X</button>
                        <input autocomplete="off" class="companyName" type="text" value="<s:property value="companyId.name"/>" placeholder="Company Name"/>
                        <input autocomplete="off" class="companyDate" type="text" value="<s:property value="formatDate(startDate) + ' - ' + formatDate(endDate)"/>" placeholder="MMM YY - MMM YY"/>
                        <input autocomplete="off" class="line-months" disabled="disabled" type="text" placeholder=""/>
                        <div class="hidden">
                            <input autocomplete="off" class="companyRole" type="hidden" placeholder="Roles" multiple="multile"/>
                        </div>
                        <div class="hidden companyLocation">
                            <select class="country">
                                <option>Canada</option>
                                <option>USA</option>
                            </select>
                            <select class="state">
                                <option>a</option>
                                <option>b</option>
                            </select>
                            <select class="city" type="text" placeholder="Location"></select>
                        </div>
                    </div>
                    <ul class="details hidden">
                        <s:iterator value="positionPointCollection">
                            <li class="detail">
                                <input  class="isDetailSelected" type="checkbox" checked="checked"/>
                                <button class="deleteDetail">Del</button>
                                <span class="detailNumber"></span>
                                <span class="right">
                                    <input class="line" data-id="<s:property value="id"/>" data-rank="<s:property value="rank"/>" type="text" value="<s:property value="description"/>" placeholder="Details">
                                    <button class="addDetail">New</button>
                                    <button class="addBulk">Bulk</button>
                                </span>
                            </li>
                        </s:iterator>
                    </ul>
                </div>
            </s:iterator>
        </s:if>
    </div>
    <div class="companyHeader">
        <button id="addCompanyButton">New Company</button>
        <div id="total">
            <span>Total:</span><input contenteditable="false" id="" class="" type="text" value="" readonly="readonly" placeholder="Total Time">
        </div>
    </div>
    <br><button id="saveWorkHistoryButton" type="button">Save Resume</button>
    <div id="modalAddBulkDetails"  class="ui-widget hidden">
        <p class="validateTips">Add resume details, each line will be a separate detail.</p>
        <form>
            <fieldset>
                <lable for="bulkdetails">Details:</lable>
                <textarea cols="30" rows="15" name="bulkdetails" id="bulkdetails" class="text ui-widget-content ui-corner-all"></textarea>
            </fieldset>
        </form>
    </div>
</div>