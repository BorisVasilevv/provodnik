<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<head>

</head>
 <body>
     <h1>
         Вход
     </h1>

    <form method="post" autocomplete="off">
        Email
        <br>
        <input type="email" name="email" />
        <br>
        <br>
        Пароль
        <br>
        <input type="password"  name="password" />
        <br>
        <br>
        <input type="submit" value="Войти" />
        <input type="reset" value="Очистить" />

    </form>
    <br>
    <form method="get" action="http://localhost:8080/my-app/provodnik">
        <button>Отмена</button>
    </form>
    <br>
    <form method="get" action="http://localhost:8080/my-app/registration">
        <button>Перейти к регистрации</button>
    </form>

    ${errorMessage}
 </body>
 </html>