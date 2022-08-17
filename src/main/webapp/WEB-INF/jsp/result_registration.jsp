<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="formstyle">
  <p>Login: <c:out value="${requestScope.login}"/></p>
  <p>Password: <c:out value="${requestScope.password}"/></p>

  <p>Name: <c:out value="${requestScope.name}"/></p>
  <p>Date of Birth: <c:out value="${requestScope.datebirth}"/></p>
  <p>Phone: <c:out value="${requestScope.phone}"/></p>
  <p>E-mail: <c:out value="${requestScope.email}"/></p>
</div>
