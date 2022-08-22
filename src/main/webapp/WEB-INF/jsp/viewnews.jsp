<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <%@ taglib
prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
  <c:if test="${not(requestScope.news eq null)}">
    <div class="col-3 py-3 ps-5 text-secondary">News Title</div>
    <div class="col-8 py-3"><c:out value="${requestScope.news.title}" /></div>

    <div class="col-3 py-3 ps-5 text-secondary">News Date</div>

    <fmt:formatDate
      var="date"
      value="${requestScope.news.date_create.time}"
      pattern="MM/dd/yyyy"
    />

    <div class="col-8 py-3"><c:out value="${date}" /></div>

    <div class="col-3 py-3 ps-5 text-secondary">Brief</div>
    <div class="col-8 py-3"><c:out value="${requestScope.news.brief}" /></div>

    <div class="col-3 py-3 ps-5 text-secondary">Content</div>
    <div class="col-8 py-3"><c:out value="${requestScope.news.content}" /></div>
  </c:if>
  <c:if test="${requestScope.news eq null}">
    <div class="col-6 offset-3">
      <div class="text-center alert alert-primary">
        <c:out value="${param.news_error}" />
      </div>
    </div>
  </c:if>
</div>
