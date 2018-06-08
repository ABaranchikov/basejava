<%@ page import="ru.javawebinar.basejava.model.SectionType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="ru.javawebinar.basejava.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<section>
    <h2>${resume.fullName}&nbsp;<a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></h2>
    <p>
        <c:forEach var="contactEntry" items="${resume.contacts}">
            <jsp:useBean id="contactEntry"
                         type="java.util.Map.Entry<ru.javawebinar.basejava.model.ContactType, java.lang.String>"/>
                <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
        </c:forEach>
    <p>
    <table border="0" cellpadding="2">
        <c:forEach var="type" items="<%=SectionType.values()%>">
            <tr>
                <td colspan=2><h3>${type.title}</h3></td>
            </tr>
            <c:choose>
                <c:when test="${type =='PERSONAL' || type =='OBJECTIVE'}">
                    <tr>
                        <td colspan=2>
                                ${resume.getSection(type)}
                        </td>
                    </tr>
                </c:when>
                <c:when test="${type =='ACHIEVEMENT' || type =='QUALIFICATIONS'}">
                    <c:forEach var="item" items="${(resume.getSection(type)).getItems()}">
                        <tr>
                            <td colspan=2>
                                <ul>
                                    <li>${item}</li>
                                </ul>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
            </c:choose>
        </c:forEach>
    </table>
</section>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>