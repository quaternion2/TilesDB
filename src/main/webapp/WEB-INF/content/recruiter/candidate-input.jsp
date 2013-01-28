<h1>Resume Entry</h1>  

<div class="templates hidden">
    <div style=" background-color: white; font-weight: bold;">
        <span style="padding-left:7em;">Company</span> 
        <span style="padding-left:10em;">Role</span> 
        <span style="float:right; padding-right:7em;">Dates</span>
    </div>
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
</div>
