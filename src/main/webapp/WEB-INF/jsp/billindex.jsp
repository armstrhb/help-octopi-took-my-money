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
        <div id="bill-index-container" class="col-xs-12"></div>
    </div>
    
    <script>
        $(document).on('click', '.bill-grid-item', function() {
        	showBillDetail($(this).data("bill-id"));
        });
        
        $(document).on('click', '#bill-create-modal-show', function() {
        	$("#bill-create-modal").modal({show: true});
        });
        
        function refreshBillList() {
        	$.ajax("<c:url value='/bills'/>", {
        		dataType: 'json',
        		success: function(data) {
        			renderBills(data);
        		},
        		error: function() {
        			notify("error", "Unable to retrieve updated bill index.");
        		}
        	});
        }
        
        function renderBills(bills) {
        	container = $("#bill-index-container");
        	container.empty();
        	
        	if (bills.length > 0) {
        		grid = $("<div/>", {"class": "bill-grid row"});
        		
        		bills.forEach(function(bill) {
        			gridItem = $("<div/>", {"class": "bill-grid-item col-xs-6 col-sm-4 col-md-3", "data-bill-id": bill.id});
        			gridInner = $("<div/>", {"class": "row text-center"});
        			gridNameContainer = $("<div/>", {"class": "col-xs-12"});
        			gridName = $("<h4/>", {"text": bill.name});
        			paymentPlanContainer = $("<div/>", {"class": "col-xs-12"});
        			paymentPlan = $("<h2/>", {"text": new Number(bill.paymentPlanAmount).toFixed(2)});
        			balanceContainer = $("<div/>", {"class": "col-xs-12"});
        			
        			if (bill.balanceAvailable) {
        			    balance = $("<h4/>", {"text": new Number(bill.currentBalance).toFixed(2)});
        			} else {
        				balance = $("<h4/>", {"html": "&nbsp;"});
        			}
        			
        			gridNameContainer.append(gridName);
        			paymentPlanContainer.append(paymentPlan);
       			    balanceContainer.append(balance);
        			gridInner.append(gridNameContainer);
        			gridInner.append(paymentPlanContainer);
        			gridInner.append(balanceContainer);
        			gridItem.append(gridInner);
        			grid.append(gridItem);
        		});
        		
        		container.append(grid);
        	} else {
        		notice = $("<div/>", {"class": "text-center", "text": "No bills are being tracked. Why don't you create some?"});
        		container.append(notice);
        	}
        }
        
        refreshBillList();
    </script>
</t:page>