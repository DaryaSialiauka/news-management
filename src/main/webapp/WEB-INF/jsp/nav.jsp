<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row p-2 m-1 bg-secondary bg-opacity-50">
  <div class="bg-secondary col-11 offset-1 mb-1 text-white text-center">
    News
  </div>
  <c:if test="${sessionScope.user eq 'activ'}">
    <div class="col-9 offset-3 bg-white">
      <div class="col-12">
        <a class="text-center" href="#"
          ><img src="./image/plus.svg" alt="" width="15" />News list</a
        >
      </div>
      <div class="col-12">
        <a class="text-center" href="#"
          ><img src="./image/plus.svg" alt="" width="15" />Add news</a
        >
      </div>
    </div>
  </c:if>
</div>
