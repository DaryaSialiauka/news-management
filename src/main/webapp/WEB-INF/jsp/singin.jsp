<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <%@ taglib
prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local.body.singin.singin" var="loc" />

<fmt:message bundle="${loc}" key="singin.login" var="loclogin" />
<fmt:message bundle="${loc}" key="singin.password" var="locpassword" />
<fmt:message bundle="${loc}" key="singin.button.singin" var="locsingin" />
<fmt:message bundle="${loc}" key="singin.error" var="locerror" />

<div class="row align-items-start">
  <form action="Controller" method="post">
    <input type="hidden" name="command" value="DO_AUTHENTICATION" />
    <div class="col-12 text-center text-danger">
      &nbsp;<c:if test="${param.ErrorAuthentication eq true}"><c:out value="${locerror}"/></c:if>&nbsp;
    </div>
    <div class="col-6 offset-3 form-floating mb-2">
      <input
        type="text"
        class="form-control ${param.invalidclass}"
        id="login"
        name="login"
        value="${param.login}"
        placeholder="Login"
        required
      /><label for="login" class="form-label"><c:out value="${loclogin}"/></label>
    </div>
    <div class="col-6 offset-3 form-floating mb-2">
      <input
        type="password"
        class="form-control ${param.invalidclass}"
        id="password"
        name="password"
        value="${param.password}"
        placeholder="Password"
        required
      />
      <label for="password" class="form-label"><c:out value="${locpassword}"/></label>
    </div>
    <div class="col-12 text-center">
      <input type="submit" class="btn btn-primary" value="${locsingin}" />
    </div>
  </form>
</div>
