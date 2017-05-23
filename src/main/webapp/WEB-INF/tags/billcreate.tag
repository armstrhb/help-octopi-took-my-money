<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="bill-create-modal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">Bill Creation</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="form-group">
                            <label for="bill-create-bill-name">Name</label>
                            <input type="text" id="bill-create-bill-name" class="form-control" placeholder="Give it something unique"></input>
                        </div>
                        <div class="form-group">
                            <label for="bill-create-account-number">Account Number</label>
                            <input type="text" id="bill-create-account-number" class="form-control" placeholder="All bills have an identifier from the company, include it here."></input>
                        </div>
                        <hr/>
                        <div class="form-group">
                            <label for="bill-create-website">Website</label>
                            <input type="url" id="bill-create-website" class="form-control" placeholder="All companies have a website nowadays."></input>
                        </div>
                        <div class="form-group">
                            <label for="bill-create-phone-number">Phone Number</label>
                            <input type="tel" id="bill-create-phone-number" class="form-control" placeholder="Most still have phone numbers too."></input>
                        </div>
                        <hr/>
                        <div class="form-group">
                            <label for="bill-create-payment-plan-amount">Payment Plan</label>
                            <input type="number" min="0" max="1000" id="bill-create-payment-plan-amount" class="form-control" placeholder="How much are you paying a month?"></input>
                        </div>
                        <div class="form-group">
                            <label for="bill-create-day-of-month-due">Day of Month Due</label>
                            <input type="number" min="1" max="31" id="bill-create-day-of-month-due" class="form-control" placeholder="When do you pay it?"></input>
                        </div>
                        <div class="form-group">
                            <label for="bill-create-initial-balance">Initial Balance</label>
                            <input type="number" min="1" id="bill-create-initial-balance" class="form-control" placeholder="Have an initial balance?"></input>
                            <small id="bill-create-initial-balance-help" class="form-text text-muted">Not all bills have this - you can leave this blank.</small>
                        </div>
                        <hr/>
                        <div class="form-group">
                            <label for="bill-create-notes">Notes</label>
                            <textarea id="bill-create-notes" class="form-control" rows="3" placeholder="Include any pertinent details here (deals, website credentials, etc.)"></textarea>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" id="bill-create-submit-button" class="btn btn-primary" data-dismiss="modal">Create</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
            </div>
        </div>
    </div>
</div>

<script>
  $("#bill-create-submit-button").click(function() {
	  bill = getBillCreateFormData();
	  
	  $.ajax("<c:url value="/bills/create"/>", {
		  data: JSON.stringify(bill),
		  contentType: 'application/json',
		  type: 'POST',
		  success: function(result) {
			  if (result) {
				   notify("success", "Bill '" + bill.name + "' created.");
				   refreshBillList();
			  } else {
				   notify("error", "Whoops. Errors encountered while creating bill '" + bill.name + "'.");				  
			  }
		  },
		  error: function() {
			  notify("error", "Whoops. Errors encountered while creating bill '" + bill.name + "'.");
		  }
	  });
  });
  
  function getBillCreateFormData() {
	  formData = {};
	  
	  formData.name = $("#bill-create-bill-name").val();
	  formData.accountNumber = $("#bill-create-account-number").val();
	  formData.website = $("#bill-create-website").val();
	  formData.phoneNumber = $("#bill-create-phone-number").val();
	  
	  if ($("#bill-create-payment-plan-amount").val()) {
	      formData.paymentPlanAmount = $("#bill-create-payment-plan-amount").val();
	  }
	  
	  formData.dayOfMonthDue = $("#bill-create-day-of-month-due").val();
	  
	  if ($("#bill-create-initial-balance").val()) {
	      formData.initialBalance = $("#bill-create-initial-balance").val();
	  }
	  
	  formData.notes = $("#bill-create-notes").val();
	  
	  return formData;
  }
</script>