<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <%@ taglib
prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="now" class="java.util.Date" />

<fmt:formatDate var="date" value="${now}" pattern="yyyy-MM-dd" />

<form action="Controller" method="post">
  <div class="col-12 text-center text-danger">
    &nbsp;<c:out value="${param.ErrorNews}" />&nbsp;
  </div>

  <c:if test="${requestScope.news eq null}">
    <input type="hidden" name="command" value="DO_ADD_NEWS" />
  </c:if>
  <c:if test="${not(requestScope.news eq null)}">
    <input type="hidden" name="command" value="DO_EDIT_NEWS" />
  </c:if>

  <input type="hidden" name="id_news" value="${requestScope.news.id}"/>

  <div class="row">
    <div class="col-3 py-3 ps-5 text-secondary">News Title</div>
    <div class="col-8 py-3">
      <input
        type="text"
        class="form-control ${param.title_error_style}"
        id="title"
        name="title"
        value="${sessionScope.news.title}${requestScope.news.title}"
        placeholder="Title"
        required
      />
    </div>
    <div id="titleFeedback" class="invalid-feedback d-block text-center">
      <c:out value="${param.title_error}" />
    </div>

    <div class="col-3 py-3 ps-5 text-secondary">News Date</div>


    <c:if test="${requestScope.news eq null}">
      <fmt:parseDate
        value="${sessionScope.news.date_create}"
        pattern="yyyy-MM-dd"
        var="parsedDate"
        type="date"
      />
    </c:if>
    <c:if test="${not(requestScope.news eq null)}">
      <fmt:parseDate
        value="${requestScope.news.date_create}"
        pattern="yyyy-MM-dd"
        var="parsedDate"
        type="date"
      />
    </c:if>

    <fmt:formatDate
      value="${parsedDate}"
      var="date_news_format"
      type="date"
      pattern="yyyy-MM-dd"
    />


    <div class="col-8 py-3">
      <input
        type="date"
        class="form-control ${param.date_error_style}"
        id="date_news"
        name="date_news"
        value="${date_news_format}"
        placeholder="Date News"
        max="${date}"
        required
      />
    </div>
    <div id="dateFeedback" class="invalid-feedback d-block text-center">
      <c:out value="${param.date_error}" />
    </div>

    <div class="col-3 py-3 ps-5 text-secondary">Brief</div>
    <div class="col-8 py-3">
      <textarea
        class="form-control ${param.brief_error_style}"
        id="brief"
        name="brief"
        placeholder="Brief"
        style="height: 50px"
        required
      >
<c:out value="${sessionScope.news.brief}"/><c:out value="${requestScope.news.brief}"/></textarea
      >
    </div>
    <div id="briefFeedback" class="invalid-feedback d-block text-center">
      <c:out value="${param.brief_error}" />
    </div>

    <div class="col-3 py-3 ps-5 text-secondary">Content</div>
    <div class="col-8 py-3">
      <textarea
        class="form-control ${param.content_error_style}"
        id="content"
        name="content"
        placeholder="Content"
        style="height: 100px"
        required
      >
<c:out value="${sessionScope.news.content}"/><c:out value="${requestScope.news.content}"/></textarea
      >
    </div>
    <div id="contentFeedback" class="invalid-feedback d-block text-center">
      <c:out value="${param.content_error}" />
    </div>
  </div>

  <div class="col-12 text-center">
    <input type="submit" class="btn btn-primary" value="Save" />
    <input
      type="button"
      onClick="window.location='Controller?command=GO_TO_BASE_PAGE&pagenum=1'"
      class="btn btn-secondary"
      value="Cancel"
    />
  </div>
</form>
