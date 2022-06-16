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
</head>
<body>
    <div>
        <a href="/ex/insert_view">추가</a>
        <table>
            <tr>
                <th>No</th>
                <th>아이디</th>
                <th>생년월일</th>
                <th>직업</th>
                <th></th>
            </tr>
            <c:forEach items="${userList}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.loginId}</td>
                <td>${user.birth}</td>
                <td>${user.job}</td>
                <td><a href="/ex/detail_view?id=${user.id}"><button type="button" class="update-btn" >수정</button></a></td>
                <td><a><button type="button" onclick="deleteBtn(this)" class="delete-btn" data-user-id="${user.id}">삭제</button></a></td>
            </tr>
            </c:forEach>
        </table>
    </div>
    <script>
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
    </script>
</body>
</html>
