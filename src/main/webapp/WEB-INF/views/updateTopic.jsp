<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
            integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>

    <script src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>

    <title>Форум: редактирование темы</title>

</head>
<body>
<div class="container">
    <div class="row justify-content-end">
        <ul class="nav">
            <li class="nav-item">
                <a href="<c:url value='/'/>">На главную |&nbsp</a>
            </li>
            <li class="nav-item">
                Пользователь : ${user.username} |&nbsp
            </li>
            <li class="nav-item">
                <a href="<c:url value='/logout'/>">Выйти</a>
            </li>
        </ul>
    </div>
    <div class="row">
        <div class="card" style="width: 100%">
            <div class="card-header">
                <ul class="nav nav-pills nav-fill">
                    <li class="nav-item">
                       Редактирование темы
                    </li>
                </ul>
            </div>
            <div class="card-body">
                <form id="form1" name="form1" action="<c:url value='/saveTopic'/>" method="POST">
                    <div class="row">
                        <input type="text" class="form-control" name="id" id="id" value="<c:out value="${topic.id}"/>" hidden>
                        <div class="col">
                            <div class="form-group">
                                <label for="author">Автор редактируемой темы</label>
                                <input type="text" class="form-control" name="author" id="author" value="<c:out value="${topic.author.username}"/>" disabled>
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-group">
                                <label for="name">Название редактируемой темы</label>
                                <input type="text" class="form-control" name="name" id="name" value="<c:out value="${topic.name}"/>">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description">Описание/первое сообщение в теме</label>
                        <textarea class="form-control" name="description" id="description" rows="3"><c:out value="${topic.description}"/></textarea>
                    </div>
                    <button type="submit" class="btn btn-primary">Сохранить изменения</button>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>