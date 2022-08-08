<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <%@ taglib
prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />

<fmt:formatDate var="date" value="${now}" pattern="yyyy-MM-dd" />
<fmt:formatDate
  var="userdatebirth"
  value="${userbean.datebirth.time}"
  pattern="yyyy-MM-dd"
/>
<div class="row align-items-start">
  <form action="Controller" method="post">
    <input type="hidden" name="command" value="DO_REGISTRATION" />
    <div class="col-12 text-center text-danger">
      &nbsp;<c:out value="${param.ErrorRegistration}" />&nbsp;
    </div>

    <div class="col-6 offset-3 form-floating mb-2">
      <input
        type="text"
        class="form-control ${param.firstname_error_style}"
        id="firstname"
        name="firstname"
        value="${sessionScope.userbean.firstname}"
        placeholder="First name"
        required
      />
      <label for="firstname" class="form-label">First name</label>
      <div id="firstnameFeedback" class="invalid-feedback d-block">
        <c:out value="${param.firstname_error}" />
      </div>
    </div>

    <div class="col-6 offset-3 form-floating mb-2">
      <input
        type="text"
        class="form-control ${param.lastname_error_style}"
        id="lastname"
        name="lastname"
        value="${sessionScope.userbean.lastname}"
        placeholder="Last name"
        required
      />
      <label for="lastname" class="form-label">Last name</label>
      <div id="lastnameFeedback" class="invalid-feedback d-block">
        <c:out value="${param.lastname_error}" />
      </div>
    </div>

    <div class="col-6 offset-3 form-floating mb-2">
      <input
        type="date"
        class="form-control ${param.datebirth_error_style} ${param.datebirth_min_style}"
        id="date"
        name="datebirth"
        value="${userdatebirth}"
        placeholder="Date birth"
        max="${date}"
        required
      />
      <label for="date" class="form-label">Date birth</label>
      <div id="datebirthFeedback" class="invalid-feedback d-block">
        <c:out value="${param.datebirth_error}" />
        <c:out value="${param.datebirth_min}" />
      </div>
    </div>

    <div class="col-6 offset-3 form-floating mb-2">
      <input
        type="text"
        class="form-control ${param.login_error_style} ${param.login_found_style}"
        id="login"
        name="login"
        value="${sessionScope.userbean.login}"
        placeholder="Login"
        required
      />
      <label for="login" class="form-label">Login</label>
      <div id="loginFeedback" class="invalid-feedback d-block">
        <c:out value="${param.login_error}" />
        <c:out value="${param.login_found}" />
      </div>
    </div>

    <div class="col-6 offset-3 form-floating mb-2">
      <input
        type="email"
        class="form-control form-control-sm ${param.email_error_style} ${param.email_found_style}"
        id="email"
        name="email"
        value="${sessionScope.userbean.email}"
        placeholder="Email"
        required
      />
      <label for="email" class="form-label">Email</label>
      <div id="emailFeedback" class="invalid-feedback d-block">
        <c:out value="${param.email_error}" />
        <c:out value="${param.email_found}" />
      </div>
    </div>

    <div class="col-6 offset-3 form-floating mb-2">
      <input
        type="text"
        class="form-control form-control-sm ${param.phone_error_style} ${param.phone_found_style}"
        id="phone"
        name="phone"
        value="${sessionScope.userbean.phone}"
        pattern="[\+]{0,1}375[\(]{0,1}[0-9]{2}[\)]{0,1}\d{3}[-]{0,1}\d{2}[-]{0,1}\d{2}"
        placeholder="+375(__)___-__-__"
        required
      />
      <label for="phone" class="form-label">Phone</label>
      <div id="phoneFeedback" class="invalid-feedback d-block">
        <c:out value="${param.phone_error}" />
        <c:out value="${param.phone_found}" />
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
      <label for="password" class="form-label">Password</label>
      <div id="passwordFeedback" class="invalid-feedback d-block">
        <c:out value="${param.password_error}" />
      </div>
    </div>
    <div class="col-12 text-center">
      <input type="submit" class="btn btn-primary" value="Registration" />
    </div>
  </form>
</div>
