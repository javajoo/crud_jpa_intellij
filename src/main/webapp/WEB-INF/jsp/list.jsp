<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-06-14
  Time: 오전 9:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>

    <!-- 합쳐지고 최소화된 최신 CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- 부가적인 테마 -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">

    <!-- 합쳐지고 최소화된 최신 자바스크립트 -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
</head>
<body>
    <div>
        <h1>USER 게시판</h1><hr>
        <a href="/ex/insert_view"><button class="btn btn-success ">글쓰기</button></a><hr>

        <table id="userTable" class="table table-striped text-center">
            <tr>
                <th>No</th>
                <th>아이디</th>
                <th>생년월일</th>
                <th>직업</th>
                <th></th>
                <th></th>
            </tr>

            <c:forEach items="${userList}" var="user">
            <tr id="tBody">
                <td>${user.id}</td>
                <td>${user.loginId}</td>
                <td>${user.birth}</td>
                <td>${user.job}</td>
                <td><a href="/ex/detail_view?id=${user.id}"><button type="button" class="btn update-btn btn-danger" >수정</button></a></td>
                <td><a><button type="button" onclick="deleteBtn(this)" class="btn delete-btn btn-primary" data-user-id="${user.id}">삭제</button></a></td>
            </tr>
            </c:forEach>

        </table>

        <select name="type">
            <option>아이디</option>
        </select>

        <input type="text" id="loginId">
        <button type="button" class="btn search-btn btn-secondary" onclick="searchBtn()">검색</button>

    </div>
    <script>

        $('#inputText').focus();
            function deleteBtn(obj) {
                let id = $(obj).data('user-id');
                //alert(id);

                $.ajax({
                   url : '/ex/delete'
                   ,type: 'delete'
                   ,data: {'id' : id}
                   ,success: function(data) {
                        alert('삭제성공!!!!!');
                        location.reload();
                    }
                   ,error: function(e) {
                       alert('관리자에게 문의바랍니다.');
                    }
                });
            };

            function searchBtn() {
             // alert('click');
                let loginId = $('#loginId').val().trim();
                //alert(loginId);

                $.ajax({
                   url : '/ex/search/' + loginId
                    ,type : 'get'
                    ,success : function(data) {
                        $('#tBody').empty();
                       if (data.result == 'success') {
                           let html = '';
                           for (let user of data.userList) {
                                html += '<tr>';
                                html += '<td>' + user.id + '</td>';
                                html += '<td>' + user.loginId + '</td>';
                                html += '<td>' + user.birth + '</td>';
                                html += '<td>' + user.job + '</td>';
                                html += '</tr>';
                           }
                           $('#tBody').html(html);
                       } else {
                            if (!data.userList.length) {
                                let html = '<tr>';
                                html += '<td>검색한 조건을 찾을 수 없습니다.</td>'
                                html += '</tr>';
                                $('#tBody').html(html);
                            }
                       }
                    }
                    ,error : function(e) {
                       alert('관리자에게 문의바랍니다.');
                    }
                });
            };
    </script>
</body>
</html>
