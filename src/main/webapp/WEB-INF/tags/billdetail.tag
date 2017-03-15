<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
                    <div class="data-item col-xs-12 col-sm-12 col-md-6">
                        <div class="label">Account Number</div>
                        <div class="bill-account"></div>
                    </div>
                    <c:choose>
                        <c:when test="${bill.overDue == true}">
                            <div class="data-item overdue col-xs-12 col-sm-12 col-md-6">
                        </c:when>
                        <c:otherwise>
                            <div class="data-item col-xs-12 col-sm-12 col-md-6">
                        </c:otherwise>
                    </c:choose>
                        <div class="label">Next Due</div>
                        <div class="bill-due"></div>
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
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<script>
    function showBillDetail(id) {
    	$.get("/bill/" + id, function(data) {
    		var modal = $("#bill-detail-modal");
    		
    		modal.find(".bill-name").html(data.name);
    		modal.find(".bill-account").html(data.accountNumber);
    		modal.find(".bill-due").html(moment(data.dueDate).format("MM/DD"));
    		modal.find(".bill-website").attr("href", data.website);
    		modal.find(".bill-website").html(data.website);
    		modal.find(".bill-notes").html(data.notes);
    		
    		$("#bill-detail-modal").modal({show: true});
    	});
    }
</script>