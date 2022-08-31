<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <%@ taglib
prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="local.footer.footer" var="loc" />

<fmt:message bundle="${loc}" key="footer.title" var="title" />

<div class="text-center"><c:out value="${title}" /></div>
