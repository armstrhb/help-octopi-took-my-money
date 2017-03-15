<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:page>
    <div class="row">
        <div class="col-xs-12">
            <h4 class="page-header">Bill Index</h4>
            <div class="bill-grid row">
			    <c:forEach var="bill" items="${bills}">
			        <div class="bill-grid-item col-xs-6 col-sm-4 col-md-3" data-bill-id="${bill.id}">
			            <div class="row text-center">
			                <div class="col-xs-12">
			                    <h4>${bill.name}</h4>
			                </div>
			                <div class="col-xs-12">
			                    <h2><fmt:formatNumber pattern="#,##0.00" value="${bill.paymentPlanAmount}"/></h2>
			                </div>
			                <div class="col-xs-12">
			                    Due
			                    <h4><fmt:formatDate pattern="MM/dd" value="${bill.getDueDate()}"/></h4>
			                </div>
			            </div>
			        </div>
			    </c:forEach>
		    </div>
        </div>
    </div>
    
    <script>
        $(document).on('click', '.bill-grid-item', function() {
        	showBillDetail($(this).data("bill-id"));
        });
    </script>
</t:page>