<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <%@ taglib
prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local.local" var="loc" />

<fmt:message bundle="${loc}" key="local.title" var="title" />
<fmt:message bundle="${loc}" key="local.localbutton.en" var="button_en"/>
<fmt:message bundle="${loc}" key="local.localbutton.by" var="button_by"/>

<div class="col-4 d-flex">
  <h3 class="text-primary m-auto">
    <a
      class="text-decoration-none text-reset"
      href="Controller?command=GO_TO_BASE_PAGE&pagenum=1"
      ><c:out value="${title}"
    /></a>
  </h3>
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
    <a href="Controller?command=GO_TO_LOCAL&local=en" class="p-2"><c:out value="${button_en}"/></a>
    <a href="Controller?command=GO_TO_LOCAL&local=by" class="p-2"><c:out value="${button_by}"/></a>
  </div>
</div>
