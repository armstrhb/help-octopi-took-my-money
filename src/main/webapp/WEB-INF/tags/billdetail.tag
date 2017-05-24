<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div id="bill-detail-modal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">Bill Detail</h4>
            </div>
            <div class="modal-body">
                <h2 class="bill-name"></h2>
                <div class="row">
                    <div class="col-xs-12 col-sm-6 text-center">
                        <h5>Amount Due</h5>
                        <h2 class="bill-amount-due"></h2>
                    </div>
                    <div class="col-xs-12 col-sm-6 text-center">
                        <h5>Next Due</h5>
                        <h2 class="bill-due"></h2>
                    </div>
                </div>
                <div class="row">
                    <div class="data-item col-xs-12 col-sm-8">
                        <div class="label">Account Number</div>
                        <div class="bill-account"></div>
                    </div>
                    <div class="data-item col-xs-12 col-sm-4">
                        <div class="label">Phone Number</div>
                        <div class="bill-phone"></div>
                    </div>
                    <div class="data-item col-xs-12">
                        <div class="label">Website</div>
                        <div><a target="_blank" class="bill-website"></a></div>
                    </div>
                    <div class="data-item col-xs-12">
                        <div class="label">Notes</div>
                        <div class="bill-notes"></div>
                    </div>
                </div>
                <div class="row">
                    <div class="data-item col-xs-12">
                        <div class="label">History</div>
                        <div class="bill-history">
                        
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<script>
    function showBillDetail(id) {
    	$.get("<c:url value='/bills/'/>" + id, function(data) {
    		var moneyFormat = new Intl.NumberFormat('en-US', { 
    			minimumFractionDigits: 2
    		});
    		var modal = $("#bill-detail-modal");
    		
    		modal.find(".bill-name").html(data.name);
    		modal.find(".bill-amount-due").html(moneyFormat.format(data.paymentPlanAmount));
    		modal.find(".bill-account").html(data.accountNumber);
    		modal.find(".bill-phone").html(data.phoneNumber);
    		modal.find(".bill-due").html(moment(data.dueDate).format("MM/DD"));
    		modal.find(".bill-website").attr("href", data.website);
    		modal.find(".bill-website").html(data.website);
    		
    		if (data.notesAvailable) {
    			modal.find(".bill-notes").html(data.notes);
    		} else {
    		    modal.find(".bill-notes").html("No notes available.");
    		}
    		
    		if (data.events.length > 0) {
    			
    		} else {
    			modal.find(".bill-history").html("No history.");
    		}
    		
    		$("#bill-detail-modal").modal({show: true});
    	});
    }
</script>