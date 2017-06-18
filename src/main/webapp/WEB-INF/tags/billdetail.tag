<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div id="bill-detail-modal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">Bill Detail</h4>
            </div>
            <div class="modal-body bill-detail">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="well well-sm">
                            <div class="row">
	                            <div class="col-xs-12 col-sm-7 col-md-9">
	                                <h2 class="bill-name"></h2>
	                            </div>
	                            <div class="col-xs-12 col-sm-5 col-md-3 text-right">
                                    <h2 class="bill-balance custom-tooltip" data-toggle="tooltip" data-placement="top" title="Current Balance">
                                        <span class="text"></span>
                                        <span class="icon fa fa-fw"></span>
                                    </h2>
	                            </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row bill-board">
                    <div class="col-xs-12 col-sm-6 text-center">
                        <div class="well well-sm">
                            <h5>Amount Due</h5>
                            <h2 class="bill-amount-due"></h2>
                        </div>
                    </div>
                    <div class="col-xs-12 col-sm-6 text-center">
                        <div class="well well-sm">
                            <h5>Next Due</h5>
                            <h2 class="bill-due"></h2>
                        </div>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div class="col-xs-12 col-sm-12 col-md-8 col-lg-9">
                        <div class="well well-sm">
                            <div class="row">
			                    <div class="data-item col-xs-12 col-sm-6">
			                        <div class="label">Account Number</div>
			                        <div class="bill-account"></div>
			                    </div>
			                    <div class="data-item col-xs-12 col-sm-6">
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
	                    </div>
                    </div>
                    <div class="col-xs-12 col-sm-12 col-md-4 col-lg-3">
                        <div class="well well-sm">
                            <div class="row">
                                <div class="col-xs-12 text-center">
                                    Commands
                                    <hr/>
                                </div>
                            </div>
                            <div class="row command-block">
                                <div class="command col-xs-4 col-sm-4 col-md-12"><a class="btn btn-default btn-block">Get Ahead</a></div>
                                <div class="command col-xs-4 col-sm-4 col-md-12"><a class="btn btn-default btn-block">Deactivate</a></div>
                                <div class="command col-xs-4 col-sm-4 col-md-12"><a class="btn btn-block btn-danger">Delete</a></div>
                            </div>
                        </div>
                    </div>
                </div>
                <hr/>
                <div class="row">
                    <div class="data-item col-xs-12">
                        <div class="well well-sm">
                            <div class="label">History</div>
                            <div class="bill-history"></div>
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
    		
    		if (data.balanceAvailable) {
    			modal.find(".bill-balance").removeClass("hide");
    			
    			if (data.currentBalance == 0) {
    				modal.find(".bill-balance .text").html("Paid Off");
    				modal.find(".bill-balance").addClass("paid-off");
    				
    				modal.find(".bill-balance .icon").addClass("fa-check-square-o");
    				modal.find(".bill-balance .icon").removeClass("hide fa-square");
    			} else {
    				modal.find(".bill-balance .text").html(moneyFormat.format(data.currentBalance));
    				modal.find(".bill-balance").removeClass("paid-off");
    				
                    modal.find(".bill-balance .icon").addClass("fa-square");
                    modal.find(".bill-balance .icon").removeClass("hide fa-check-square-o");
    			}
    		} else {
    			modal.find(".bill-balance .text").html("")
    			modal.find(".bill-balance").addClass("hide");
    		}
    		
    		if (data.noteAvailable) {
    			converter = new showdown.Converter({simpleLineBreaks: 'true'});
    			modal.find(".bill-notes").html(converter.makeHtml(data.notes));
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