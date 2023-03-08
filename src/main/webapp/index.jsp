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
        <%=request.getAttribute("path")%>
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
        List<File> files= (List<File>) request.getAttribute("files");

        for(File file: files){

            if(!file.isFile()) {
                String str=file.getName()+"/";
            %>
                <a href='${str}'><%=str%></a>
            <%
            }
            else{
                out.print(file.getName());
            }
            %>
        <br>
        <%
        }%>

    </body>
</html>