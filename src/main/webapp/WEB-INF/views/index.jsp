<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.0/font/bootstrap-icons.css">

    <title>Форум разработчиков Java</title>
</head>
<body>
<div class="container">
    <div class="row justify-content-end">
        <ul class="nav">
            <sec:authorize access="isAuthenticated()">
                <li class="nav-item">
                    Пользователь : ${user.username} |&nbsp
                </li>
                <li class="nav-item">
                    <a href="<c:url value='/createTopic'/>">Создать новую тему |&nbsp</a>
                </li>
                <li class="nav-item">
                    <a href="<c:url value='/logout'/>">Выйти</a>
                </li>
            </sec:authorize>
            <sec:authorize access="!isAuthenticated()">
                <li class="nav-item">
                    <a href="<c:url value='/login'/>">Войти</a>
                </li>
            </sec:authorize>
        </ul>
    </div>
    <div class="card" style="width: 100%">
        <div class="card-header">
            <h4>Форум разработчиков Java</h4>
        </div>
        <div class="card-body">
            <div class="row">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col">Автор</th>
                        <th scope="col">Тема</th>
                        <th scope="col">Дата создания</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${topics}" var="topic">
                        <tr>
                            <td><c:out value="${topic.author.username}"/></td>
                            <td>
                                <a href="<c:url value='/topic?id=${topic.id}'/>">
                                    <c:out value="${topic.name}"/>
                                </a>
                            </td>
                            <td><fmt:formatDate value="${topic.created}" pattern="dd/MM/yy HH:mm" /></td>
                            <sec:authorize access="hasAnyRole('ROLE_MODERATOR')">
                                <td>
                                    <a href="<c:url value='/updateTopic?id=${topic.id}'/>">
                                        <i class="bi bi-pencil-square"></i>
                                    </a>
                                    <a href="<c:url value='/deleteTopic?id=${topic.id}'/>">
                                        <i class="bi bi-trash" style="color: red;"></i>
                                    </a>
                                </td>
                            </sec:authorize>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>