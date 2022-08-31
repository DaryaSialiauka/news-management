<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <%@ taglib
prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local.nav.nav" var="loc" />

<fmt:message bundle="${loc}" key="nav.news" var="locnews" />
<fmt:message bundle="${loc}" key="nav.newslist" var="locnewslist" />
<fmt:message bundle="${loc}" key="nav.addnews" var="locaddnews" />

<div class="row p-2 m-1 bg-secondary bg-opacity-50">
  <div class="bg-secondary col-11 offset-1 mb-1 text-white text-center">
    <c:out value="${locnews}" />
  </div>
  <c:if test="${sessionScope.user eq 'activ'}">
    <div class="col-9 offset-3 bg-white">
      <div class="col-12">
        <a
          class="text-center"
          href="Controller?command=GO_TO_BASE_PAGE&pagenum=1"
          ><img src="./image/plus.svg" alt="" width="15" />
          <c:out value="${locnewslist}" />
        </a>
      </div>
      <div class="col-12">
        <a class="text-center" href="Controller?command=GO_TO_ADD_NEWS"
          ><img src="./image/plus.svg" alt="" width="15" /><c:out
            value="${locaddnews}"
          />
        </a>
      </div>
    </div>
  </c:if>
</div>
