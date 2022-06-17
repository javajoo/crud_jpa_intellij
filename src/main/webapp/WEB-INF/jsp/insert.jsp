<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-06-14
  Time: 오전 9:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <h1 class="text-center">USER 추가</h1>
    <table class="table table-striped text-center">
        <tr>
            <td>아이디</td>
            <td><input type="text" placeholder="아이디" id="loginId" name="loginId"></td>
        </tr>
        <tr>
            <td>비밀번호</td>
            <td><input type="password" placeholder="비밀번호" id="password" name="password"></td>
        </tr>
        <tr>
            <td>생년월일</td>
            <td><input type="text" placeholder="생년월일" id="birth" name="birth"></td>
        </tr>
        <tr>
            <td>직업</td>
            <td><input type="text" placeholder="직업" id="job" name="job"></td>
        </tr>
    </table>
    <button type="button" class="btn insert-btn btn-success" onclick="insertBtn()">추가</button>
<script>
    function insertBtn() {
           let loginId = $('#loginId').val().trim();
           let password = $('#password').val();
           let birth = $('#birth').val().trim();
           let job = $('#job').val().trim();

           if (loginId == '') {
              alert('아이디를 입력해주세요!');
              return;
          }

          if (password == '') {
              alert('비밀번호를 입력해주세요!');
              return;
          }

          if (birth == '') {
              alert('생년월일을 입력해주세요!');
              return;
          }

          if (job == '') {
              alert('직업을 입력 해주세요!');
              return;
          }

          $.ajax({
              type : 'post'
              ,url : '/ex/insert'
              ,data : {
                  'loginId' : loginId
                  ,'password' : password
                  , 'birth' : birth
                  , 'job' : job
              }
              ,success : function(data) {
                  if (data.result == 'success') {
                      alert('추가 성공!!!');
                      location.href='/ex/list_view';
                  } else {
                      alert(data.errorMessage);
                  }
              }
              ,error : function(e) {
                  alert('관리자에게 문의바랍니다.');
              }
          })
    };
</script>
</body>
</html>
