<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row align-items-start">
  <form action="Controller" method="post">
    <input type="hidden" name="command" value="DO_REGISTRATION" />
    <div class="col-12 text-center text-danger">
      &nbsp;<c:out value="${requestScope.ErrorRegistration}" />&nbsp;
    </div>

    <div class="col-6 offset-3 form-floating mb-2">
      <input
        type="text"
        class="form-control ${requestScope.invalidclass}"
        id="firstname"
        name="firstname"
        value="${param.firstname}"
        placeholder="First name"
        required
      />
      <label for="firstname" class="form-label">First name</label>
    </div>

    <div class="col-6 offset-3 form-floating mb-2">
      <input
        type="text"
        class="form-control ${requestScope.invalidclass}"
        id="lastname"
        name="lastname"
        value="${param.lastname}"
        placeholder="Last name"
        required
      />
      <label for="lastname" class="form-label">Last name</label>
    </div>

    <div class="col-6 offset-3 form-floating mb-2">
      <input
        type="date"
        class="form-control ${requestScope.invalidclass}"
        id="date"
        name="datebirth"
        value="${param.datebirth}"
        placeholder="Date birth"
        required
      />
      <label for="date" class="form-label">Date birth</label>
    </div>

    <div class="col-6 offset-3 form-floating mb-2">
      <input
        type="text"
        class="form-control ${requestScope.invalidclass}"
        id="login"
        name="login"
        value="${param.login}"
        placeholder="Login"
        required
      />
      <label for="login" class="form-label">Login</label>
    </div>

    <div class="col-6 offset-3 form-floating mb-2">
      <input
        type="email"
        class="form-control form-control-sm ${requestScope.invalidclass}"
        id="email"
        name="email"
        value="${param.email}"
        placeholder="Email"
        required
      />
      <label for="email" class="form-label">Email</label>
    </div>

    <div class="col-6 offset-3 form-floating mb-2">
      <input
        type="text"
        class="form-control form-control-sm ${requestScope.invalidclass}"
        id="phone"
        name="phone"
        value="${param.phone}"
        pattern="\+375[\(]{0,1}[0-9]{2}[\)]{0,1}\d{3}[-]{0,1}\d{2}[-]{0,1}\d{2}"
        placeholder="+375(__)___-__-__"
        required
      />
      <label for="phone" class="form-label">Phone</label>
    </div>

    <div class="col-6 offset-3 form-floating mb-2">
      <input
        type="password"
        class="form-control form-control-sm ${requestScope.invalidclass}"
        id="password"
        name="password"
        value="${param.password}"
        placeholder="Password"
        required
      />
      <label for="password" class="form-label">Password</label>
    </div>
    <div class="col-12 text-center">
      <input type="submit" class="btn btn-primary" value="Registration" />
    </div>
  </form>
</div>
