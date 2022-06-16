<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-06-14
  Time: 오전 9:39
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
    <input type="text" placeholder="아이디" id="loginId" name="loginId" value="${user.loginId}"><br>
    <input type="password" placeholder="비밀번호" id="password" name="password" value="${user.password}"><br>
    <input type="text" placeholder="생년월일" id="birth" name="birth" value="${user.birth}"><br>
    <input type="text" placeholder="직업" id="job" name="job" value="${user.job}"><br>
    <button type="button" class="update-btn" onclick="updateBtn()" data-user-id="${user.id}">수정</button>
</div>
   <script>
       function updateBtn() {
           //alert('click');
           let loginId = $('#loginId').val().trim();
           let password = $('#password').val().trim();
           let birth = $('#birth').val().trim();
           let job = $('#job').val().trim();
           let id = $('.update-btn').data('user-id');
           $.ajax({
              type : 'put'
              ,url : '/ex/update'
              ,data : {
                    'loginId' : loginId
                   ,'password' : password
                   ,'birth' : birth
                   ,'job' : job
                   ,'id' : id
                }
               ,success : function(data) {
                  if (data.result == 'success') {
                      alert('수정성공!!!!');
                      location.href = '/ex/list_view';
                  } else {
                      alert(data.errorMessage);
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