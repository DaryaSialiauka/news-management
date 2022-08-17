<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="col-4 d-flex">
  <h3 class="text-primary m-auto">News management</h3>
</div>
<div class="col-8 row d-flex align-items-center">
  <div class="col-12 text-end">
    <c:if test="${sessionScope.user eq 'activ'}">
      <form action="Controller" method="post" class="d-inline">
        <input type="hidden" name="command" value="DO_SINGOUT" />
        <input type="submit" class="btn btn-secondary" value="SingOut" />
      </form>
    </c:if>

    <c:if test="${not (sessionScope.user eq 'activ')}">
      <form action="Controller" method="post" class="d-inline">
        <input type="hidden" name="command" value="GO_TO_AUTHENTICATION" />
        <input type="submit" class="btn btn-primary" value="SingIn" />
      </form>

      <form action="Controller" method="post" class="d-inline">
        <input type="hidden" name="command" value="GO_TO_REGISTRATION" />
        <input type="submit" class="btn btn-secondary" value="Registration" />
      </form>
    </c:if>
  </div>
  <div class="col-12 text-end">
    <a href="#" class="p-2">English</a><a href="#" class="p-2">Russian</a>
  </div>
</div>
