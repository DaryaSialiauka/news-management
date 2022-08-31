<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <%@ taglib
prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />

<fmt:formatDate var="date" value="${now}" pattern="yyyy-MM-dd" />

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local.body.registration.reg" var="loc" />

<fmt:message bundle="${loc}" key="reg.firstname" var="locfirstname" />
<fmt:message bundle="${loc}" key="reg.lastname" var="loclastname" />
<fmt:message bundle="${loc}" key="reg.datebirth" var="locdatebirth" />
<fmt:message bundle="${loc}" key="reg.login" var="loclogin" />
<fmt:message bundle="${loc}" key="reg.email" var="locemail" />
<fmt:message bundle="${loc}" key="reg.phone" var="locphone" />
<fmt:message bundle="${loc}" key="reg.password" var="locpassword" />
<fmt:message bundle="${loc}" key="reg.button.reg" var="locreg" />

<fmt:message bundle="${loc}" key="reg.error" var="locerror" />

<fmt:message bundle="${loc}" key="reg.errorfirstname" var="errorfirstname" />
<fmt:message bundle="${loc}" key="reg.errorlastname" var="errorlastname" />
<fmt:message bundle="${loc}" key="reg.errordatebirth" var="errordatebirth" />
<fmt:message bundle="${loc}" key="reg.mindatebirth" var="mindatebirth" />
<fmt:message bundle="${loc}" key="reg.errorlogin" var="errorlogin" />
<fmt:message bundle="${loc}" key="reg.foundlogin" var="foundlogin" />
<fmt:message bundle="${loc}" key="reg.erroremail" var="erroremail" />
<fmt:message bundle="${loc}" key="reg.foundemail" var="foundemail" />
<fmt:message bundle="${loc}" key="reg.errorphone" var="errorphone" />
<fmt:message bundle="${loc}" key="reg.foundphone" var="foundphone" />
<fmt:message bundle="${loc}" key="reg.errorpassword" var="errorpassword" />

<div class="row align-items-start">
  <form action="Controller" method="post">
    <input type="hidden" name="command" value="DO_REGISTRATION" />
    <div class="col-12 text-center text-danger">
      &nbsp;<c:if test="${param.ErrorRegistration eq true}"
        ><c:out value="${locerror}" /></c:if
      >&nbsp;
    </div>

    <div class="col-6 offset-3 form-floating mb-2">
      <input
        type="text"
        class="form-control ${param.firstname_error_style}"
        id="firstname"
        name="firstname"
        value="${param.firstname}"
        placeholder="First name"
        required
      />
      <label for="firstname" class="form-label"
        ><c:out value="${locfirstname}"
      /></label>
      <div id="firstnameFeedback" class="invalid-feedback d-block">
        <c:if test="${param.firstname_error eq true}">
          <c:out value="${errorfirstname}" />
        </c:if>
      </div>
    </div>

    <div class="col-6 offset-3 form-floating mb-2">
      <input
        type="text"
        class="form-control ${param.lastname_error_style}"
        id="lastname"
        name="lastname"
        value="${param.lastname}"
        placeholder="Last name"
        required
      />
      <label for="lastname" class="form-label"
        ><c:out value="${loclastname}"
      /></label>
      <div id="lastnameFeedback" class="invalid-feedback d-block">
        <c:if test="${param.lastname_error eq true}">
          <c:out value="${errorlastname}" />
        </c:if>
      </div>
    </div>

    <div class="col-6 offset-3 form-floating mb-2">
      <input
        type="date"
        class="form-control ${param.datebirth_error_style} ${param.datebirth_min_style}"
        id="date"
        name="datebirth"
        value="${param.datebirth}"
        placeholder="Date birth"
        max="${date}"
        required
      />
      <label for="date" class="form-label"
        ><c:out value="${locdatebirth}"
      /></label>
      <div id="datebirthFeedback" class="invalid-feedback d-block">
        <c:if test="${param.datebirth_error eq true}">
          <c:out value="${errordatebirth}" />
        </c:if>
        <c:if test="${param.datebirth_min eq true}">
          <c:out value="${mindatebirth}" />
        </c:if>
      </div>
    </div>

    <div class="col-6 offset-3 form-floating mb-2">
      <input
        type="text"
        class="form-control ${param.login_error_style} ${param.login_found_style}"
        id="login"
        name="login"
        value="${param.login}"
        placeholder="Login"
        required
      />
      <label for="login" class="form-label"
        ><c:out value="${loclogin}"
      /></label>
      <div id="loginFeedback" class="invalid-feedback d-block">
        <c:if test="${param.login_error eq true}">
          <c:out value="${errorlogin}" />
        </c:if>
        <c:if test="${param.login_found eq true}">
          <c:out value="${foundlogin}" />
        </c:if>
      </div>
    </div>

    <div class="col-6 offset-3 form-floating mb-2">
      <input
        type="email"
        class="form-control form-control-sm ${param.email_error_style} ${param.email_found_style}"
        id="email"
        name="email"
        value="${param.email}"
        placeholder="Email"
        required
      />
      <label for="email" class="form-label"
        ><c:out value="${locemail}"
      /></label>
      <div id="emailFeedback" class="invalid-feedback d-block">
        <c:if test="${param.email_error eq true}">
          <c:out value="${erroremail}" />
        </c:if>
        <c:if test="${param.email_found eq true}">
          <c:out value="${foundemail}" />
        </c:if>
      </div>
    </div>

    <div class="col-6 offset-3 form-floating mb-2">
      <input
        type="tel"
        class="form-control form-control-sm ${param.phone_error_style} ${param.phone_found_style}"
        id="phone"
        name="phone"
        value="${param.phone}"
        pattern="[\+]{0,1}375[\(]{0,1}[0-9]{2}[\)]{0,1}\d{3}[-]{0,1}\d{2}[-]{0,1}\d{2}"
        placeholder="+375(__)___-__-__"
        required
      />
      <label for="phone" class="form-label"
        ><c:out value="${locphone}"
      /></label>
      <div id="phoneFeedback" class="invalid-feedback d-block">
        <c:if test="${param.phone_error eq true}">
          <c:out value="${errorphone}" />
        </c:if>
        <c:if test="${param.phone_found eq true}">
          <c:out value="${foundphone}" />
        </c:if>
      </div>
    </div>

    <div class="col-6 offset-3 form-floating mb-2">
      <input
        type="password"
        class="form-control form-control-sm ${param.password_error_style}"
        id="password"
        name="password"
        placeholder="Password"
        required
      />
      <label for="password" class="form-label"
        ><c:out value="${locpassword}"
      /></label>
      <div id="passwordFeedback" class="invalid-feedback d-block">
        <c:if test="${param.password_error eq true}">
          <c:out value="${errorpassword}" />
        </c:if>
      </div>
    </div>
    <div class="col-12 text-center">
      <input type="submit" class="btn btn-primary" value="${locreg}" />
    </div>
  </form>
</div>
