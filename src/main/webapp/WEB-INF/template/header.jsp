<%@taglib prefix="s" uri="/struts-tags"%>
<ul id="navBar">
    <li><s:a cssClass="group" value="/index">Employment System</s:a></li>
    <li><s:a cssClass="group" value="/enoch/listResumes001">Resume</s:a></li>
    <li><s:a cssClass="group" value="/enoch/listQualificationForms002">Qualification</s:a></li>                       
    <li><s:a cssClass="group" namespace="" value="/design/index">Design</s:a></li>              
    <li><s:a cssClass="group" value="/index" title="time">
            <script type="text/javascript">
                var mydate=new Date()
                var year=mydate.getFullYear()
                var day=mydate.getDay()
                var month=mydate.getMonth()
                var daym=mydate.getDate()
                //if the current date is less than 10, pad it.
                if (daym<10)
                    daym="0"+daym
                var dayarray=new Array("Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday")
                var montharray=new Array("January","February","March","April","May","June","July","August","September","October","November","December")
                //write out the final results
                document.write(dayarray[day]+", "+montharray[month]+" "+daym+", "+year)
            </script>  
        </s:a></li>
</ul>