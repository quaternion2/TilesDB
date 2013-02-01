/* 
 * Jan 27, 2013
 */
var totalEntryCount = 0;
var start = 1; //first line number on page
var count = 10; //number of lines per page display
var end = count; //last line number on page
            
$(document).ready(function(){
    $(".add").click(addButtonHandler); 
    $( "#tabs" ).tabs();
                
    calculateTableLineCount();
                
    buildTable(start - 1, end);
                
    $("#goToNext").click(goToNext); //function declaration
    $("#goToEnd").click(goToEnd); //function declaration
    $("#goToPrevious").click(goToPrevious); //function declaration
    $("#goToStart").click(goToStart); //function declaration
               

});
            
var buildTable = function(start){
    $.getJSON('<s:property escape="false" value="pageUrl"/>'+"?start="+start+"&count="+count, function(data){          
        $("#testTable").html('');
                    
        var nRows = data.length;
                    
        //TODO: check data and data[0] are greater than 0
        var nCols = Object.keys(data[0]).length;
                    
        //get index of id property in database
        var idIndex=-1;
        for(var i=0;i<data.length;i++){
            if(data[i].name==="id"){
                idIndex=i;
                break;
            }
        }
        
        var jTable = $("<table>");
        //make sure to do data checking
        var jTHead = $("<thead>");
        $(jTable).append(jTHead);
        var jTHeadRow = $("<tr>");
        $(jTHead).append(jTHeadRow);
        //$(jTable).append(jTHead);
        for(var key in data[0]){
            var jHTd = $("<th>").html(key);
            $(jTHeadRow).append(jHTd);
                        
        }
        for(row=0; row < nRows; row++){         
            var jRow = $("<tr>"); 
            $(jTable).append(jRow);
                        
            for(key in data[row]){
                //alert(data[row][key]);
                var jTd = $("<td>").html(data[row][key]);
                //if(key==="id"){
                $(jTd).addClass(key); 
                //}
                $(jRow).append(jTd);                        
            }
            $(jRow).append("  <button class='copy'  >Copy</button>");
            $(jRow).append("<button class='delete'>Delete</button>");
            $(jRow).append("<button class='edit'  >Edit</button>");
        }
        $("#testTable").append(jTable);
        $("button.delete").click(deleteButtonHandler); //function declaration to make it work on refresh
        $("button.copy").click(copyButtonHandler); //function declaration to make it work on refresh
        $("button.edit").click(editButtonHandler); //function declaration to make it work on refresh            
    });           
};

var goToNext = function(){
    //should print table empty in results 
    if(((start + count) < totalEntryCount + 1)){
                    
        start += count; //check if not going past end
        end = start + count - 1; //check if not going past end
    }        
                
    if(end>totalEntryCount+1){                  
        end = totalEntryCount;
        start = end - (end%count);
    //alert("start:"+start+" end:"+end+" count:"+count+" totalEntryCount:"+totalEntryCount);
                    
    }
    //set table display
    $("#tableStartNumber").text(start); //to convert array to line number
    $("#tableEndNumber").text(end);
    $("#totalEntryCount").text(totalEntryCount);
                
    buildTable(start - 1, end);
}

var goToPrevious = function(){
    //should print table empty in results 
    start -= count; //check if not going past start
    end = start + count - 1; //check if not going past start          
    //check to see if end is smaller that the number of lines per page
    if(start<=1){
        start = 1; 
        //check for if end is bigger than count
        end = count;
        if(end>totalEntryCount){                  
            end = totalEntryCount;
        }
    }          
    //set table display
    $("#tableStartNumber").text(start); //to convert array to line number
    $("#tableEndNumber").text(end);
    $("#totalEntryCount").text(totalEntryCount);            
    buildTable(start-1, end);
}

var goToStart = function(){
    start = 1;
    end = count;
    if(end>totalEntryCount){                  
        end = totalEntryCount;
    }
    //set table display
    $("#tableStartNumber").text(start); //to convert array to line number
    $("#tableEndNumber").text(end);
    $("#totalEntryCount").text(totalEntryCount);            
    buildTable(start - 1, end);
}

var goToEnd = function(){

    end = totalEntryCount;
    if(end-count < 0){
        start = 1;
    }else{
        if(count == 1){
            start = end;
        }
        else{
            start = end - (end%count) - 1;
        }
    }
    //alert("after...start:"+start+" end:"+end+" count:"+count+" totalEntryCount:"+totalEntryCount);
    //set table display
    $("#tableStartNumber").text(start); //to convert array to line number
    $("#tableEndNumber").text(end);
    $("#totalEntryCount").text(totalEntryCount);                
    buildTable(start -1, end);
}  

var deleteButtonHandler = function(){       
    var number = $(this).parent().children(".id").first().html();
    deleteLineById(number);
//alert("delete number:"+number);           
}
var deleteLineById = function(number){             
    $.getJSON('<s:property escape="false" value="deleteUrl"/>'+"?id="+number, function(data){
        if(data.message ==='error'){
            alert("error:"+data.message);                
        }else{
            calculateTableLineCount();
            buildTable(start -1, end);
        }
    });
}

var copyButtonHandler = function(){
    var number = $(this).parent().children(".id").first().html();
    copyLineById(number);
//alert("delete number:"+number);                  
}
var copyLineById = function(number){         
    $.getJSON(
        '<s:property escape="false" value="addUrl"/>', 
        {
            description: "cow",
            name:"dog",
            role:"horse"
        },
        function(data){          
            if(data.message ==='error'){
                alert("error:"+data.message);                
            }else{
                calculateTableLineCount();
                //fix this function need asynchronous receiving of data
                setTimeout(goToEnd, 600);
                        
            //buildTable(start -1, end);
            }
        });         
}

var calculateTableLineCount = function(){
    $.getJSON('<s:property escape="false" value="countUrl"/>', function(data){
        totalEntryCount = data;
        //alert("start:"+start+" end:"+end+" count:"+count+" totalEntryCount:"+totalEntryCount);
        //test if catches error properly
        if(totalEntryCount==0){
            //should print table empty in results
            alert("empty table");
            end = 0;
        // alert("start:"+start+" end:"+end+" count:"+count+" totalEntryCount:"+totalEntryCount);
        }
        if(totalEntryCount<count){
            //figure out what to do
            end = totalEntryCount;
        //alert("start:"+start+" end:"+end+" count:"+count+" totalEntryCount:"+totalEntryCount);
        }
                    
        $("#tableStartNumber").text(start); //to convert array to line number
        $("#tableEndNumber").text(end);
        $("#totalEntryCount").text(totalEntryCount);
                        
    // alert("start:"+start+" end:"+end+" count:"+count+" totalEntryCount:"+totalEntryCount);                   
    });
}
            
var addButtonHandler = function(){

    $('#dialog-add').removeClass("hidden");
    $( "#dialog-add" ).dialog({
        height: 500,
        modal: true,
        buttons: {
            "Update": function() {
                            
                $.getJSON(
                    '<s:property escape="false" value="addUrl"/>', 
                    {
                        description: $("#addDescription").val(),
                        name:$("#addName").val(),
                        role:$("#addRole").val()
                    },
                    function(){
                        //fix this function need asynchronous receiving of data
                        setTimeout(goToEnd, 600);
                    });
                ///ADD ERROR CORRECTING CODE TO HANDLE ERROR MESSAGE
                $( this ).dialog( "close" );                            
            },
            Cancel: function() {
                $( this ).dialog( "close" );
            }
        }
    });
                
                
}
            
var editButtonHandler = function(){
    //make call to lightbox make pop up with empty boxes
    //when press save send to server
    //edit qualification form pop up
    var description = $(this).parent().children(".description").first();
    $("#description").val($(this).parent().children(".description").first().html());
    var name = $(this).parent().children(".name").first();
    $("#name").val($(this).parent().children(".name").first().html());
    var role = $(this).parent().children(".role").first();
    $("#role").val($(this).parent().children(".role").first().html());
               
    var idInSelectedRow = $(this).parent().children(".id").first().html();
               
    //alert($("#de"));scription
    //KEN THINKS THAT THIS DIALOG BOX has weird properties 
    $('#dialog-edit').removeClass("hidden");
    $( "#dialog-edit" ).dialog({
        height: 500,
        modal: true,
        buttons: {
            "Update": function() {
                            
                $.getJSON('<s:property escape="false" value="updateUrl"/>',
                {
                    id: idInSelectedRow,
                    description: $("#description").val(),
                    name: $("#name").val(),
                    role: $("#role").val()
                });
                ///ADD ERROR CORRECTING CODE TO HANDLE ERROR MESSAGE
                $( this ).dialog( "close" );
                            
                $(description).html($('#description').val());
                $(name).html($('#role').val());
                $(role).html($('#name').val());
                            
            },
            Cancel: function() {
                $( this ).dialog( "close" );
            }
        }
    });              
}


