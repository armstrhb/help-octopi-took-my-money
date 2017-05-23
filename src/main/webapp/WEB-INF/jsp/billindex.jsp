<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<t:page>
    <div class="row">
        <div class="col-xs-12">
            <h4 class="page-header">Bill Index</h4>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-12">
            <div class="well well-sm">
                <a id="bill-create-modal-show" class="btn btn-primary">Add a bill</a>
            </div>
            <hr/>
        </div>
    </div>
    <t:notification/>
    <div class="row">
        <div class="col-xs-12">
            <c:choose>
                <c:when test="${fn:length(bills) > 0}">
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
			    </c:when>
			    <c:otherwise>
			        <div class="text-center"> 
                        No bills are being tracked. Why don't you create some?
                    </div>			    
			    </c:otherwise>
		    </c:choose>
        </div>
    </div>
    
    <script>
        $(document).on('click', '.bill-grid-item', function() {
        	showBillDetail($(this).data("bill-id"));
        });
        
        $(document).on('click', '#bill-create-modal-show', function() {
        	$("#bill-create-modal").modal({show: true});
        });
    </script>
</t:page>