<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <%@ taglib
prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local.header.header" var="loc" />

<fmt:message bundle="${loc}" key="header.title" var="title" />
<fmt:message bundle="${loc}" key="header.localbutton.en" var="button_en"/>
<fmt:message bundle="${loc}" key="header.localbutton.by" var="button_by"/>

<fmt:message bundle="${loc}" key="header.localbutton.singin" var="singin"/>
<fmt:message bundle="${loc}" key="header.localbutton.singout" var="singout"/>
<fmt:message bundle="${loc}" key="header.localbutton.reg" var="reg"/>

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
        <input type="submit" class="btn btn-secondary" value="${singout}" />
      </form>
    </c:if>

    <c:if test="${not (sessionScope.user eq 'activ')}">
      <form action="Controller" method="get" class="d-inline">
        <input type="hidden" name="command" value="GO_TO_AUTHENTICATION" />
        <input type="submit" class="btn btn-primary" value="${singin}" />
      </form>

      <form action="Controller" method="get" class="d-inline">
        <input type="hidden" name="command" value="GO_TO_REGISTRATION" />
        <input type="submit" class="btn btn-secondary" value="${reg}" />
      </form>
    </c:if>
  </div>
  <div class="col-12 text-end">
    <a href="Controller?command=GO_TO_LOCAL&local=en" class="p-2"><c:out value="${button_en}"/></a>
    <a href="Controller?command=GO_TO_LOCAL&local=by" class="p-2"><c:out value="${button_by}"/></a>
  </div>
</div>
