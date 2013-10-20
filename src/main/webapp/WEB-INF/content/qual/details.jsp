<%-- 
    Document   : details
    Created on : 21-Sep-2013, 9:34:05 PM
    Author     : ken
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<ol>
    <li>Note if document needs saving, ie: individual lines might not but order or header has changed</li>
    <li>Add save heading feature</li>
    <li>FEATURE: Bulk save</li>
    <li>FEATURE: Bulk update</li>
    <li>Have save header save the whole qual using bulk operations</li>
    <li>drag & drop to reorder lines</li>
    <li>Clone qual feature (force assign to null opportunity)</li>
    <li>Drop role field (title is good enough)</li>
    <li>Allow selection and assignment to opportunity</li>
</ol>
<div data-ng-app>
    <div data-ng-controller="QualController">
        <form id="entityDetails"> 
            <fieldset>
                <legend><span>Show Fields</span></legend>
                <input type="text" data-ng-model='name' placeholder="Name" title="Name" value='{{name}}'/>
                <input type="text" data-ng-model='role' placeholder="Role" title="Role" value="{{role}}"/>
                <button id="update-entity" type="button">Save</button>
                <button id="delete-entity" type="button">Delete</button>
                <div>
                    <textarea id="description" style="width:98%"  data-ng-model='description' placeholder="Description" title="Description"></textarea>
                </div>
            </fieldset>
            <table id="qual-lines">
                <thead>
                    <tr>
                        <th>Del</th>
                        <th>Saved</th>
                        <!--th class="id">id</th-->
                        <th>#</th>
                        <th>Description</th>
                        <th>Mandatory</th>
                        <th>Months</th>
                    </tr>
                </thead>
                <tbody>
                    <tr ng-repeat="line in lines">
                        <td>
                            <button data-ng-click="deleteLine($index)" class="del-line"></button>
                        </td>
                        <td>
                            <button data-ng-click="saveLine($index)" class="saved-{{isLineSaved($index)}}"></button>
                        </td>
                        <!--td class="id">{{line.id}}</td-->
                        <td>
                            <!--input class="ordinal" type="text" disabled="disabled" placeholder="ordinal" title="ordinal" data-ng-model="line.ordinal"-->
                            <select class="ordinal" name="ordinal" title="ordinal" data-ng-change="moveLine($index)" data-ng-model="line.ordinal" data-ng-options="v for v in getLineNumbers()"></select>
                        </td>
                        <td>
                            <textarea data-ng-model="line.description" class="description" rows="2" cols="40" placeholder="Description" title="Description"></textarea>
                        </td>
                        <td>
                            <select class="mandatory" name="mandatory" title="Mandatory" data-ng-model="line.mandatory" data-ng-options="v for v in mandatoryOptions"></select>
                        </td>
                        <td>
                            <input class="months" type="text" placeholder="Months" title="Months" data-ng-model="line.months" />
                        </td>
                    </tr>
                </tbody>
                <tfoot>
                    <tr><td><button id="add-qual-line" data-ng-click="addLine()">New Line</button></td></tr>      
                </tfoot>
            </table>   
        </form>
    </div>
</div>