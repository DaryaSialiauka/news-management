<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row align-items-start">
  <form action="Controller" method="post">
    <input type="hidden" name="command" value="DO_AUTHENTICATION" />
    <div class="col-12 text-center text-danger">
      &nbsp;<c:out value="${param.ErrorAuthentication}"/>&nbsp;
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
      /><label for="login" class="form-label">Login</label>
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
      <label for="password" class="form-label">Password</label>
    </div>
    <div class="col-12 text-center">
      <input type="submit" class="btn btn-primary" value="Sign In" />
    </div>
  </form>
</div>
