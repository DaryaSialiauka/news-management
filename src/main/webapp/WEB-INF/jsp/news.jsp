<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <%@ taglib
prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="row">
  <c:if test="${not(requestScope.news eq null)}">
    <form action="" method="post">
      <input type="hidden" name="command" value="DO_DELETE_NEWS" />
      <c:forEach var="news" items="${requestScope.news}">
        <div class="col-12">
          <div class="card border-white">
            <div class="card-body row">
              <h6 class="card-title col-10"><c:out value="${news.title}" /></h6>

              <fmt:parseDate
                value="${news.date_create}"
                pattern="yyyy-MM-dd"
                var="parsedDate"
                type="date"
              />

              <fmt:formatDate
                value="${parsedDate}"
                var="date"
                type="date"
                pattern="MM/dd/yyyy"
              />

              <h6 class="card-title col-2 text-end">
                <c:out value="${date}" />
              </h6>
              <p class="card-text">
                <c:out value="${news.brief}" />
              </p>

              <div class="text-end">
                <c:if
                  test="${(sessionScope.role eq 'admin') or (sessionScope.id eq news.id_author)}"
                  var="result"
                  scope="request"
                >
                  <a
                    href="Controller?command=GO_TO_EDIT_NEWS&id_news=${news.id}"
                    class="card-link"
                    >Edit news</a
                  >
                </c:if>
                <c:if
                  test="${(sessionScope.role eq 'admin') or (sessionScope.role eq 'reporter') or (sessionScope.role eq 'user')}"
                >
                  <a
                    href="Controller?command=GO_TO_VIEW_NEWS&id_news=${news.id}"
                    class="card-link"
                    >View news</a
                  >

                  <c:if test="${requestScope.result}">
                    <input
                      class="form-check-input"
                      type="checkbox"
                      name="idNews"
                      value="${news.id}"
                    />
                  </c:if>

                  <c:if test="${not(requestScope.result)}">
                    <input
                      class="form-check-input"
                      type="checkbox"
                      name="idNews"
                      value="${news.id}"
                      disabled
                    />
                  </c:if>
                </c:if>
              </div>
            </div>
          </div>
        </div>
      </c:forEach>
      <c:if
        test="${(sessionScope.role eq 'admin') or (sessionScope.role eq 'reporter')}"
      >
        <div class="d-md-flex justify-content-md-end">
          <input
            type="submit"
            class="btn btn-primary me-md-2"
            value="Delete"
          /></div
      ></c:if>
    </form>
    <div class="col-12">
      <c:if test="${sessionScope.user eq 'activ'}">
        <nav aria-label="Page navigation example">
          <ul class="pagination justify-content-center">
            <c:forEach var="page" begin="1" end="${requestScope.quantityPage}">
              <c:if test="${param.pagenum eq page}">
                <li class="page-item active">
                  <a
                    class="page-link"
                    href="Controller?command=GO_TO_BASE_PAGE&pagenum=${page}"
                    ><c:out value="${page}"
                  /></a>
                </li>
              </c:if>
              <c:if test="${not(param.pagenum eq page)}">
                <li class="page-item">
                  <a
                    class="page-link"
                    href="Controller?command=GO_TO_BASE_PAGE&pagenum=${page}"
                    ><c:out value="${page}"
                  /></a>
                </li>
              </c:if>
            </c:forEach>
          </ul>
        </nav>
      </c:if>
    </div>
  </c:if>
  <c:if test="${requestScope.news eq null}">
    <div class="col-6 offset-3">
      <div class="text-center alert alert-primary">
        <c:out value="${param.news_error}" />
      </div>
    </div>
  </c:if>
</div>
