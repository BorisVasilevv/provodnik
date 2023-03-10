<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.lang.String" %>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.nio.charset.StandardCharsets"%>

<!doctype html>
<html>
<head>
<style>
          .table__row {
            text-align: -webkit-left;
            text-align: -moz-left;
            text-align: -o-left;
            text-align: -ms-left;
            text-align: left;
          }

        </style>
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
        String strPath = (String) request.getAttribute("path");
        String[] arrayS=strPath.split("\\\\");
        Arrays.asList(arrayS).removeAll(Arrays.asList(""));
        if(arrayS.length>1){
            String lastElem=arrayS[arrayS.length-1];
            String stringToReplace="\\"+lastElem;
            strPath= "?path="+URLEncoder.encode(strPath.replace(stringToReplace, ""), StandardCharsets.UTF_8.toString());
            System.out.println(strPath);
            %>
            <a href=<%=strPath%>>Вверх</a>
        <%
        }
        %>


        <br>
        <table>

            <tr class="table__row">
                <th><span class="cutText">Файл</span></th>
                <th><span class="cutText">Размер</span></th>
                <th><span class="cutText">Дата</span></th>
            </tr>

            <%
            List<File> directories= (List<File>) request.getAttribute("directories");
            List<File> files= (List<File>) request.getAttribute("files");

            for(File dir: directories){
                String link="?path=" + URLEncoder.encode(dir.getAbsolutePath(), StandardCharsets.UTF_8.toString())+"\\";
                String str= dir.getName()+"\\";
                %>
                <tr>
                <td>
                <a href=<%=link%>> <%=str%></a>
                </td>
                <td>

                </td>
                <td>
                <%=dir.lastModified()%>
                </td>
                </tr>

            <%
            }

            for(File file: files){
                String[] array=file.getName().split("/");
                String fileName=array[array.length-1];%>
                <tr>
                <td>
                <a href=<%=fileName%>><%=fileName%></a>
                </td>
                <td>
                <%=String.format("%d %s", file.length(), "Byte")%>
                </td>
                <td>
                <%=file.lastModified()%>
                </td>
                </tr>

            <%
            }
            %>

        </table>
    </body>
</html>