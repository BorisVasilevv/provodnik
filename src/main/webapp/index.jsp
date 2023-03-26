<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.lang.String" %>
<%@ page import="java.lang.StringBuffer" %>
<%@ page import="java.text.DateFormat"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="java.nio.file.Files"%>
<%@ page import="java.nio.charset.StandardCharsets"%>
<%@ page import="java.nio.file.attribute.FileTime"%>


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

          .login {
              position: absolute;
              right: 30px;
              top: 40px;
          }

          .registration {
              position: absolute;
              right: 100px;
              top: 40px;
          }

</style>
<title>First</title>
<script>


</script>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
</head>
    <body>

    <h1>
        ${path}
    </h1>
    <!--<% boolean isUserLogin=(boolean) request.getAttribute("isUserLogin");

    if(!isUserLogin){%>
        <form method="get" action="./registration">
        <div class="registration">
            <button>Зарегистрироваться</button>
        </div>
        </form>

        <form method="get" action="./login">
        <div class="login">
            <button>Войти</button>
        </div>
        </form>
    <%
    }
    else{%>-->

        <div class="registration">
            <b>Вы вошли в систему, как <%=request.getAttribute("login")%><b>
        </div>
        </form>

        <form method="POST" action="./logout">
            <div class="login">
                <button>Выйти</button>
            </div>
        </form>

    <!--<%
    }%>-->


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
        String[] arrayS2=strPath.split("/");
        if(arrayS.length>1){
            String lastElem=arrayS[arrayS.length-1];
            StringBuffer strBuffer = new StringBuffer();
            for(int i=0;i<arrayS.length-1;i++) {
                strBuffer.append(arrayS[i]);
                strBuffer.append('\\');
            }
            strPath= "?path="+URLEncoder.encode(strBuffer.toString(), StandardCharsets.UTF_8.toString());
            %>
            <a href=<%=strPath%>>Вверх</a>
            <%
        }
        else if(arrayS2.length>1){
            String lastElem=arrayS[arrayS.length-1];
            StringBuffer strBuffer = new StringBuffer("/");
            for(int i=1;i<arrayS2.length-1;i++) {
                strBuffer.append(arrayS2[i]);
                strBuffer.append('/');
            }
            strPath= "?path="+URLEncoder.encode(strBuffer.toString(), StandardCharsets.UTF_8.toString());
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
                String link="?path=" + URLEncoder.encode(dir.getAbsolutePath(), StandardCharsets.UTF_8.toString());
                String str= "\\" + dir.getName();
                %>
                <tr>
                <td>
                <a href=<%=link%>> <%=str%></a>
                </td>
                <td>

                </td>
                <td>
                <%=Files.getLastModifiedTime(dir.toPath()).toString()%>
                </td>
                </tr>

            <%
            }%>
            <form action="./download" method="post">
            <%
            for(File file: files){
                %>
                <tr>
                <td>
                <a href=<%="download?path="+URLEncoder.encode(file.getAbsolutePath(), StandardCharsets.UTF_8.toString())%>><%=file.getName()%></a>
                </td>
                <td>
                <%=String.format("%d %s", file.length(), "Byte")%>
                </td>
                <td>
                <%=Files.getLastModifiedTime(file.toPath()).toString()%>
                </td>
                </tr>

            <%
            }
            %>
            </form>

        </table>
    </body>
</html>