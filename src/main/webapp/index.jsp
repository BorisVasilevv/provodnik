<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.lang.String" %>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>


<!doctype html>
<html>
<head>
<title>First</title>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
</head>
    <body>

    <h1>
        ${path}
    </h1>
    <br>
    <%
        Date date =new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        String strDate = dateFormat.format(date);
        response.getWriter().write(strDate);
    %>
    <hr/>
    <%
        List<File> directories= (List<File>) request.getAttribute("directories");
        List<File> files= (List<File>) request.getAttribute("files");

        for(File file: directories){
            String str=file.getName()+"/";
            %>
                <a href=<%=str%>><%=str%></a>
        <br>
        <%
        }

        for(File file: files){
            String[] array=file.getName().split("/");
            String fileName=array[array.length-1];%>
            <a href=<%=fileName%>><%=fileName%></a>
            <br>
        <%
        }%>

    </body>
</html>