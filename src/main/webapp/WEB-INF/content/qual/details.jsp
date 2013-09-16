<%-- 
    Document   : details
    Created on : 14-Sep-2013, 3:40:02 PM
    Author     : ken
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script>
    $(document).ready(function() {
        //get the id from the url and use ajax to get the details for the qual
        var thisUrl = document.URL.toString();
        var idComp = thisUrl.split("?")[1];
        var id = idComp.split("=")[1];
        console.log("id: " + id);
        var readEntityUrl = "<s:url namespace="/crud/qual" action="read"/>" + "?" + idComp;
        var updateEntityUrl = "<s:url namespace="/crud/qual" action="update"/>" + "?" + idComp;
        var deleteEntityUrl = "<s:url namespace="/crud/qual" action="delete"/>" + "?" + idComp;
        var getLinesUrl = "<s:url namespace="/qual" action="lines-by-id.action"/>" + "?" + idComp;
        var delQualLineUrl = "<s:url namespace="/crud/qual-line" action="delete"/>" + "?";
        var addQualLineUrl = "<s:url namespace="/crud/qual-line" action="add"/>" + "?";
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

        var getQualLines = function() {
            $.getJSON(getLinesUrl, function(data) {
                $(data).each(function(i, obj) {
                    var tr = $("<tr>");
                    var id = data[i].id;
                    var delThis = $("<button>").click(function() {
                        delQualLine(id);
                    }).html("del");
                    $(tr).append($("<td>").append(delThis));
                    $(tr).append($("<td>").html("saved"));
                    $(tr).append($("<td>").html(data[i].id));
                    var field_ordinal = $("<input>").attr({type: "text", name: "ordinal", placeholder: "ordinal", title: "ordinal"}).val(data[i].number);
                    $(tr).append($("<td>").append(field_ordinal));
                    var field_description = $("<textarea>").attr({name: "description", placeholder: "Description", title: "Description"}).val(data[i].description);
                    $(tr).append($("<td>").append(field_description));
                    var field_mandatory = $("<input>").attr({type: "text", name: "mandatory", placeholder: "false", title: "Mandatory"}).val(data[i].mandatory.toString());
                    $(tr).append($("<td>").append(field_mandatory));
                    var field_months = $("<input>").attr({type: "text", name: "months", placeholder: "Months", title: "Months"}).val(data[i].months);
                    $(tr).append($("<td>").append(field_months));
                    $("#qual-lines").append(tr);
                });
            });
        };

        var delQualLine = function(id) {
            var destination = delQualLineUrl + "id=" + id;
            var status = "error";
            $.getJSON(destination, function(data) {
                if (data.status === "success") {
                    status = "success";
                }
            });
            if (status === "success") {
                //delete this line
            } else {
                //modal alert that delete failed
            }
        };

        var saveQualLine = function() {

        }

    <%--
    This function will add a new qual-line if the state is "new", update
    the qual-line state is "save" or do nothing if the state is "saved".
    The state is maintained in the buttons html node. --%>
            var create_save_button = function(id) {
                $("<button>").click(
                        function() {
                            var state = $(this).html();
                            if (state === "new") {
                                $.getJSON(addQualLineUrl, function(data) {
                                    
                                });
                            } else if (state === "save") {
                                $.getJSON(updateQualLineUrl + "id="+id, function(data) {

                                });
                            }
                        }
                ).html("new");
            };

            var addQualLine = function() {
                var tr = $("<tr>");
                var delThis = $("<button>").click(function() {
                    delQualLine(id);
                }).html("del");
                $(tr).append($("<td>").append(delThis));
                $(tr).append($("<td>").html("unsaved"));
                $(tr).append($("<td>"));
                var qualLineCount = $("#qual-lines tbody tr").length;
                var field_ordinal = $("<input>").attr({type: "text", name: "ordinal", placeholder: "ordinal", title: "ordinal"}).val(qualLineCount + 1);
                $(tr).append($("<td>").append(field_ordinal));
                var field_description = $("<textarea>").attr({name: "description", placeholder: "Description", title: "Description"});
                $(tr).append($("<td>").append(field_description));
                var field_mandatory = $("<input>").attr({type: "text", name: "mandatory", placeholder: "false", title: "Mandatory"}).val("false");
                $(tr).append($("<td>").append(field_mandatory));
                var field_months = $("<input>").attr({type: "text", name: "months", placeholder: "Months", title: "Months"}).val(0);
                $(tr).append($("<td>").append(field_months));
                $("#qual-lines").append(tr);
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
<!-- End Hidden UI Component-->
<h1>Edit Qualification Form</h1>

<div class="framed inline-block">
    <form id="entityDetails"> 
        <div>
            <input type="text" id="name" name="name" placeholder="Name" title="Name"/>
            <input type="text" id="role" name="role" placeholder="Role" title="Role"/>
            <button id="update-entity" type="button">Save</button>
            <button id="delete-entity" type="button">Delete</button>
        </div>
        <div>
            <input type="text" id="description" style="width:98%"  name="description" placeholder="Description" title="Description"/>
        </div>
    </form>
    <table id="qual-lines">
        <thead>
            <tr>
                <th>Del</th>
                <th>Saved</th>
                <th>id</th>
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
