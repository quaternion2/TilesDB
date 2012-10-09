<!DOCTYPE html>
<html>
    <head>
        <link href="./../style/style.css" rel="stylesheet" type="text/css">
        <script src="../script/jquery/1.8.1/jquery.min.js"></script>
        <script src="../script/jquery-ui-1.8.24.custom/js/jquery-ui-1.8.24.custom.min.js"></script>
        <link href ="../script/jquery-ui-1.8.24.custom/css/ui-lightness/jquery-ui-1.8.24.custom.css" rel="stylesheet" type="text/css">
        
        <script>
            $(document).ready(function() {
                //get all buttons
                //jQuery.getallbuttons();
                $("button").click(deleteButtonHandler);
            });
            var deleteButtonHandler = function(){
                var number = $(this).parent().parent().children(":first").html();
                alert("delete number:"+number);               
            };
        </script>

    </head>
    <body>
        <table>
            <th>ROW</th>
            <th>DELETE</th>
            <tr><td>1</td><td><button>DEL</button></td></tr>
            <tr><td>2</td><td><button>DEL</button></td></tr>
            <tr><td>3</td><td><button>DEL</button></td></tr>
            <tr><td>4</td><td><button>DEL</button></td></tr>
            <tr><td>5</td><td><button>DEL</button></td></tr>
            <tr><td>6</td><td><button>DEL</button></td></tr>
            <tr><td>7</td><td><button>DEL</button></td></tr>
            <tr><td>8</td><td><button>DEL</button></td></tr>
            <tr><td>9</td><td><button>DEL</button></td></tr>
        </table>
    </body>
</html>