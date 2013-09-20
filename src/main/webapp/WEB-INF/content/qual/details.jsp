<%-- 
    Document   : details
    Created on : 14-Sep-2013, 3:40:02 PM
    Author     : ken
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<style>
    input.ordinal{
        width: 3em;
    }
    .id{
        display: none;
    }
</style>
<script>
    $(document).ready(function() {
        //get the id from the url and use ajax to get the details for the qual
        var thisUrl = document.URL.toString();
        var idComp = thisUrl.split("?")[1];
        var qual_id = idComp.split("=")[1];
        console.log("id: " + qual_id);
        var readEntityUrl = "<s:url namespace="/crud/qual" action="read"/>" + "?" + idComp;
        var updateEntityUrl = "<s:url namespace="/crud/qual" action="update"/>" + "?" + idComp;
        var deleteEntityUrl = "<s:url namespace="/crud/qual" action="delete"/>" + "?" + idComp;
        var getLinesUrl = "<s:url namespace="/qual" action="lines-by-id.action"/>" + "?" + idComp;
        var delQualLineUrl = "<s:url namespace="/crud/qual-line" action="delete"/>" + "?";
        var addQualLineUrl = "<s:url namespace="/crud/qual-line" action="add"/>";
        var updateQualLineUrl = "<s:url namespace="/crud/qual-line" action="update"/>" + "?";

        //var readCallback = function() {
        $.getJSON(readEntityUrl, function(data) {
            $("#name").val(data.name);
            $("#role").val(data.role);
            $("#description").val(data.description);
        });
        //};

        var updateCallback = function() {
            $.getJSON(updateEntityUrl, function(data) {
                $("#name").val(data.name);
                $("#role").val(data.role);
                $("#description").val(data.description);
            });
        };

        var confirmAndDeleteQual = function() {
            $("#dialog-confirm").dialog({
                resizable: false,
                height: 200,
                modal: true,
                buttons: {
                    "Delete Qual": function() {
                        deleteCallback();
                        window.location.href = "<s:url namespace="/qual" action="search-input"/>";
                    },
                    Cancel: function() {
                        $(this).dialog("close");
                    }
                }
            });
        };

        var deleteCallback = function() {
            $.ajax({
                type: "POST",
                url: deleteEntityUrl,
                success: function(data) {
                    data = $.parseJSON(data);
                    if (data["status"] !== "success") {
                        alert(data["message"]);
                    }
                },
                async: false
            });
        };

        var updateCallback = function() {
            $.getJSON(updateEntityUrl, {"name": $("#name").val(), "role": $("#role").val(), "description": $("#description").val()}, function(data) {
                //TODO: Check if error and warn user
            });
        };
        
        //var line_model = 

        //***CONSTRUCTOR***
        function QualRow(id, ordinal, description, mandatory, months) {
            this.tr = $("<tr>");
            var passingRow = this.tr;
            this.delButton = $("<button>").click(function() {
                delQualLine(id, passingRow);
            }).html("del");
            $(this.tr).append($("<td>").append(this.delButton));
            this.saveButton = create_save_button(this.tr, id);
            $(this.tr).append($("<td>").append(this.saveButton));
            $(this.tr).append($("<td>").attr({class: "id"}).html(id));
            this.field_ordinal = $("<input>").attr({class: "ordinal", disabled: "disabled", type: "text", name: "ordinal", placeholder: "ordinal", title: "ordinal"}).val(ordinal);
            $(this.tr).append($("<td>").append(this.field_ordinal));
            this.field_description = $("<textarea>").attr({class: "description", rows: "2", cols: "40", name: "description", placeholder: "Description", title: "Description"}).val(description);
            $(this.tr).append($("<td>").append(this.field_description));
            //this.field_mandatory = $("<input>").attr({class: "mandatory", type: "text", name: "mandatory", placeholder: "false", title: "Mandatory"}).val(mandatory.toString());
            var def_op = $("<option>").attr({value: "true"}).html("true");
            var alt_op = $("<option>").attr({value: "false"}).html("false");
            if(mandatory !== "true"){ //swap
                var temp = def_op;
                def_op = alt_op;
                alt_op = temp;
            }
                
            this.field_mandatory = $("<select>").attr({class: "mandatory", name: "mandatory",  title: "Mandatory"}).append(def_op).append(alt_op);
            $(this.tr).append($("<td>").append(this.field_mandatory));
            this.field_months = $("<input>").attr({class: "months", type: "text", name: "months", placeholder: "Months", title: "Months"}).val(months);
            $(this.tr).append($("<td>").append(this.field_months));
            $("#qual-lines").append(this.tr);
        }

        var addQualLine = function() {
            var qualLineCount = $("#qual-lines tbody tr").length;
            new QualRow("", qualLineCount + 1, "", false, 0);
        };

        var getQualLines = function() {
            $.getJSON(getLinesUrl, function(data) {
                $(data).each(function(i, obj) {
                    new QualRow(data[i].id, data[i].ordinal, data[i].description, data[i].mandatory.toString(), data[i].months);
                });
            });
        };

        var delQualLine = function(id, theRow) {
            var error_condition = "error";
            if (id !== '') {
                $.ajax({
                    async: false,
                    type: "POST",
                    url: delQualLineUrl + "id=" + id,
                    success: function(data) {
                        var data = JSON.parse(data);
                        alert("data contains: " + data["status"]);
                        if (data["status"] === "success") {
                            error_condition = "success";
                        }
                    }
                });
                if (error_condition === "success") {
                    $(theRow).remove();
                } else {
                    alert("was not success? : " + error_condition);
                }
            } else {
                //delete the line but don't call server because the line was never persisted
                $(theRow).remove();
            }
        };

        var saveQualLine = function() {

        };

    <%--
    This function will add a new qual-line if the state is "new", update
    the qual-line state is "save" or do nothing if the state is "saved".
    The state is maintained in the buttons html node. --%>
            var create_save_button = function(tr, id) {
                var button = $("<button>").click(
                        function() {
                            var button = $(this);
                            console.log(qual_id);
                            console.log($(tr).find("input.ordinal").val());
                            console.log($(tr).find("textarea.description").val());
                            console.log($(tr).find("input.mandatory").val());
                            console.log($(tr).find("input.months").val());
                            var enbalm = {
                                "model.qualId.id": qual_id,
                                "model.ordinal": $(tr).find("input.ordinal").val(),
                                "model.description": $(tr).find("textarea.description").val(),
                                "model.mandatory": $(tr).find(".mandatory").val(),
                                "model.months": $(tr).find("input.months").val()
                            };

                            var state = $(button).html();
                            if (state === "save" && !isNaN(parseFloat(id)) && isFinite(id)) {//id _is_ a number
                                $.getJSON(updateQualLineUrl, enbalm, function(data) {
                                    ajax_status_handler(data);
                                });
                            } else if (state === "save") { //save state but id does not yet exist
                                $.getJSON(addQualLineUrl, enbalm, function(data) {
                                    $(button).html("saved");
                                    $(tr).find(".id").html(id);
                                });
                            }
                            else if (state === "saved") {

                            }
                        }
                );

                if (!isNaN(parseFloat(id)) && isFinite(id)) {
                    $(button).html("saved");
                } else {
                    $(button).html("save");
                }
                return button;
            };

            var ajax_status_handler = function(data) {
                if (data.status !== "success") {
                    $("#ajax_status_error").dialog({
                        resizable: false,
                        height: 200,
                        modal: true,
                        buttons: {
                            "Okay": function() {
                                $(this).dialog("close");
                            }
                        }
                    });
                }
            };

            $("#add-qual-line").click(addQualLine);
            $("#update-entity").click(updateCallback);
            $("#delete-entity").click(confirmAndDeleteQual);
            getQualLines();
        });
</script>
<!-- Hidden UI Component-->
<div style="display: none;" id="dialog-confirm" title="Delete the Qual?">
    <p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>This qualification form will be permanently deleted and cannot be recovered. Are you sure?</p>
</div>
<!-- Hidden UI Component-->
<div style="display: none;" id="ajax_status_error" title="An Error has occured">
    <p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span>An error has occurred.</p>
</div>
<!-- End Hidden UI Component-->
<h1>Edit Qualification Form</h1>
<ul>
    <li>graphics for del, unsaved and saved</li>
    <li>delete line renumbers #</li>
    <li>new->saved->(if lined edited)->save->(back to saved state)</li>
    <li>drag & drop to reorder lines</li>
    <li>assign to opportunities (consider plurality) - should probably be singular and offer clone feature</li>
    <li>***See if I can make the legend (text in border) clickable with "show labels" / "hide labels" </li>
</ul>
<div class="framed inline-block">
    <form id="entityDetails"> 
        <div>
            <input type="text" id="name" name="name" placeholder="Name" title="Name"/>
            <input type="text" id="role" name="role" placeholder="Role" title="Role"/>
            <button id="update-entity" type="button">Save</button>
            <button id="delete-entity" type="button">Delete</button>
        </div>
        <div>
            <textarea id="description" style="width:98%"  name="description" placeholder="Description" title="Description"></textarea>
        </div>
    </form>
    <table id="qual-lines">
        <thead>
            <tr>
                <th>Del</th>
                <th>Saved</th>
                <th class="id">id</th>
                <th>#</th>
                <th>Description</th>
                <th>Mandatory</th>
                <th>Months</th>
            </tr>
        </thead>
        <tfoot>
            <tr><td><button id="add-qual-line">New Line</button></td></tr>      
        </tfoot>
    </table>
</div>
