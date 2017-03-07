<!DOCTYPE html>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:page>
    <div class="row">
        <div class="col-xs-12">
            <h4 class="page-header">Bill Index</h4>
		    <c:forEach var="bill" items="${bills}">
                <div class="row">
                    <div class="col-xs-8">
                        <strong>${bill.name}</strong>
		            </div>
		            <div class="col-xs-4 text-right">
		                <div class="row">
                            <div class="col-xs-12">
                                ${bill.dayOfMonthDue}    
                            </div>
                            <div class="col-xs-12">
                                ${bill.paymentPlanAmount}
                            </div>
		                </div>
		            </div>
		        </div>
		    </c:forEach>
        </div>
    </div>
</t:page>