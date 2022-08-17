<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <%@ taglib
prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
  <div class="col-2 text-secondary">News Title</div>
  <div class="col-10"><c:out value="${requestScope.news.title}" /></div>

  <div class="col-2 text-secondary">News Date</div>

  <fmt:formatDate
    var="date"
    value="${requestScope.news.date_create.time}"
    pattern="MM/dd/yyyy"
  />

  <div class="col-10"><c:out value="${date}" /></div>

  <div class="col-2 text-secondary">Brief</div>
  <div class="col-10"><c:out value="${requestScope.news.brief}" /></div>

  <div class="col-2 text-secondary">Content</div>
  <div class="col-10"><c:out value="${requestScope.news.content}" /></div>
</div>
