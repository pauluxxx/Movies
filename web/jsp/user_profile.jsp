<%--
  Created by IntelliJ IDEA.
  User: Алексей
  Date: 27.03.2016
  Time: 2:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Profile-${user.name} </title>
<link type="text/css" rel="stylesheet" href="../lib/css/style_profile.css">
</head>
<body>
<div class="wepper_main">
            <%@include file="jspf/head.jspf"%>
        <div class="wepper_body">
            <div class="seperator"><h3> </h3></div>

            <div class="profile">
                <div class="avat">
                    <%--сылка на изображения как сделать?--%>
                    <img src="img/person_img/${user.imageId}.jpg">
                    <div><input class="button" type="button" value="Выбрать файл"></input></div>
                </div>
                <div class="desc">
                    <form method="post">
                        <div class="text_field">
                            <text>Имя:</text>
                            <text>Фамилия:</text>
                            <text>Emal:</text>
                            <text>Роль:</text>
                        </div>
                        <div class="fields">
                            <div class="field"><input type="text" value="${user.name}"></input></div>
                            <div class="field"><input type="text" value="${user.surname}"></input></div><!-- Даные достаем из БД -->
                            <div class="field"><input type="text" value="${user.login}"></input></div>
                            <div class="field"><text>get in DB ${user.role}</text></div>
                        </div>
                        <div>
                            <div class="cliks">
                                <div class="clik">
                                    <button class="button" type="submit" value="Value_action" name="this_name_action">Изменить данные</button>
                                </div>
                                <div class="clik">
                                    <button class="button" value="Value_action" name="this_name_action"  >Удалить профиль</button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="seperator"><h3><a href="do?action=favorite_movies">Все избранные фильмы</a></h3></div>
            <%-- 4 рандомных фильма пользователя--%>
            <div class="fime_like">
                <div class="items"><img src="../img/small_baner_film/small-baner-film_02.png"><br/><a href="#">Lin Namek</a></div>
                <div class="items"><img src="../img/small_baner_film/small-baner-film_03.png"><br/><a href="#">Link Name </a></div>
                <div class="items"><img src="../img/small_baner_film/small-baner-film_04.png"><br/><a href="#">Link Name</a></div>
                <div class="items"><img src="../img/small_baner_film/small-baner-film_05.png"><br/><a href="#">Link Name</a></div>
            </div>
        </div>
    <div class="wepper_footer">
        <div class="footer">
            <label class="footer_link">©2016  FilmsSearch • <a href="#">Книга жалоб и предложений</a> • Условия использования сайта • 18+</label>
            <label class="footer_logo"><a href="#">FilmsSearch.ua</a></label>
        </div>
    </div>
</div>
</body>
</html>
