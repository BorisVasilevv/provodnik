<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<head>
</head>
<body>

    <h1>
        Регистрация
    </h1>
    <form method="post" autocomplete="off">
        Имя пользователя
        <br>
        <input type="text" name="login" />
        <br>
        E-mail
        <br>
        <input type="email"  name="email" />
        <br>

        Пароль
        <br>
        <input type="password"  name="password" />
        <br>

        Повторите пароль
        <br>
        <input type="password"  name="password" />
        <br>
        <br>

        <input type="submit" value="Зарегистрироваться" />
        <input type="reset" value="Очистить" />

        </form>
        <br>
        <!-- <form method="get" action="./provodnik">
            <button>Отмена</button>
        </form>-->

        <form method="get" action="./login">
            <button>Перейти ко входу</button>
        </form>

        ${errorMessage}
</body>
</html>