<%@ page language="java" contentType="text/html; charset=utf-8"
pageEncoding="ISO-8859-1"%> <%@ taglib uri="http://java.sun.com/jsp/jstl/core"
prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>News management</title>

    <link rel="stylesheet" type="text/css" href="css/style.css" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
  </head>
  <body>
    <div class="container mt-1">
      <div class="card">
        <div class="card-header hat row mx-0">
          <c:import url="/WEB-INF/jsp/header.jsp" />
        </div>
        <div class="row bodyheight">
          <div class="col-2">
            <c:import url="/WEB-INF/jsp/nav.jsp" />
          </div>
          <div class="col-10 border-start border-secondary">
            <c:if test="${not (sessionScope.user eq 'activ')}">
              <c:if test="${param.body eq 'singin'}">
                <c:import url="/WEB-INF/jsp/singin.jsp" />
              </c:if>

              <c:if test="${param.body eq 'registration'}">
                <c:import url="/WEB-INF/jsp/registration.jsp" />
              </c:if>

            </c:if>
          </div>
        </div>
        <div class="card-footer">
          <c:import url="/WEB-INF/jsp/footer.jsp" />
        </div>
      </div>
    </div>
  </body>
</html>
