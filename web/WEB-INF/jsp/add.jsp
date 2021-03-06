<%@ page import="ru.javawebinar.basejava.model.ContactType" %>
<%@ page import="ru.javawebinar.basejava.model.SectionType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>Новое резюме</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <dl>
            <dt>Имя:</dt>
            <dd><input type="text" name="fullName" size=30 value=""></dd>
        </dl>
        <h3>Контакты:</h3>
        <c:forEach var="type" items="<%=ContactType.values()%>">
            <dl>
                <dt>${type.title}</dt>
                <dd><input type="text" name="${type.name()}" size=30 value=""></dd>
            </dl>
        </c:forEach>
        <h3>Секции:</h3>
        <table border="0" cellpadding="2">
            <c:forEach var="type" items="<%=SectionType.values()%>">

                <tr>
                    <td colspan=2><h3>${type.title}</h3></td>
                </tr>
                <c:choose>
                    <c:when test="${type =='PERSONAL' || type =='OBJECTIVE'}">
                        <tr>
                            <td colspan=2>
                                <input type="text" name="${type.name()}" size=54 value="">
                            </td>
                        </tr>
                    </c:when>
                    <c:when test="${type =='ACHIEVEMENT' || type =='QUALIFICATIONS'}">
                        <tr>
                            <td colspan=2>
                                <ul>
                                    <li><input type="text" name="${type.name()}" size=50 value=""></li>
                                </ul>
                            </td>
                        </tr>

                    </c:when>
                </c:choose>
            </c:forEach>
        </table>
        <hr>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()">Отменить</button>
    </form>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>