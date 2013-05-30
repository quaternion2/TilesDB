<h1>Bulk Add JS</h1>
<script>
    $(document).ready(function(){
        var doProcessBulkLines = function(){  
            //get content of bulk lines
            var content = $("#bulkLines").val();
            alert(content);
            //split content on new lines
            var allLines = content.split(/\r\n|[\n\v\f\r\x85\u2028\u2029]/);//this should get all lines including empty lines
            //remove empty, null, undefined values from array (copy to new array)
            var cleanedLines = [];
            allLines.forEach(function(entry){
                if (entry){
                    cleanedLines.push(entry);
                }
            });
            //create new entries
            cleanedLines.forEach(function(entry){
                var div = $("<div>").text(entry);
                $("#target1").append(div);
            });
        }
        
        $("#processBulkLines").click(doProcessBulkLines);
   
    });
</script>
<textarea id="bulkLines"></textarea>
<button id="processBulkLines">Bulk</button>
<div id="target1"></div>