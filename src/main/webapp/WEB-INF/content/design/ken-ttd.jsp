<%-- 
    Document   : kenTtd
    Created on : 30-Sep-2012, 5:55:09 PM
    Author     : ken
--%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<body>
    <h1>Ken's TTD</h1>
    <h1>***High Priority*** </h1>
    <ul>
        <li>Add updated columns to Candidates</li>
        <li>Add Call log feature - set up logging table (candidate_log table already exists)</li>
        <li>Sort /candidate/list by last contacted date</li>
    </ul>

    <h1>CRUD</h1>
    <ul>
        <li class="strike">Update - by id (use paramsPrepareParamsStack)</li>
        <li class="strike">Delete - by id (need to test)</li>
        <li class="strike">implement dashes in entity names</li>
        <li>Set up Css to handly nested lists so everything does not look flat</li>
        <li>Set up programming environment on laptop</li>
        <li>?include parameters should only return associated collections</li>
        <li>?exclude parameters should work on individual fields</li>
        <li>Need metainformation service for entities:
            <ol>
                <li>Provide description of property and type</li>
                <li>Paging service should only work with simple types (non) collections</li>
                <li>JS grid application should take into account columns from meta information</li>
                <li>How to prevent, looping back?</li>
                <li>Need service to load collections by (id (owner) and property name)</li>
            </ol>
        </li>
    </ul>
    <h1>Meta View - Rendering</h1>
    <ul>
        <li>This is a huge pain : need to automatically output value into divs and use css/js to style</li>
        <li>properties tag - properties into list and type into list</li>
        <li>properties tag will also need attribute to only provide basic types</li>
    </ul>
    <h1>Resume Widget</h1>
    <ul>
        <li><strike>Don't sum over lapping time</strike></li>
    <li class="strike">not needed<strike>Add calculation fields (they will be hidden later to avoid use of arrays)</strike></li>
<li class="strike">Make include/exclude toggle (and selection radio boxes)</li>      
<li class="strike">Display in years/months or just months</li>
<li class="strike">Put back employee entry form, removed because was taking up too much space</li>
<li>Rewrite this thing because it's a mess!</li>
</ul>
<h1>Data Base - DB Audit & Security</h1>
<ul>
    <li>Add audit columns - created date, last updated</li>
    <li>Add "updated by" column later because it requires application interaction</li>
    <li>Consider - use of version columns for optimistic locking</li>
</ul>
