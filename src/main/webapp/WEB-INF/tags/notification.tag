<%@ tag description="User Notification Tag" pageEncoding="UTF-8" %>

<div class="row">
    <div class="col-xs-12">
        <div id="notification-pane" class="alert hide"><span class="message"></span></div>
    </div>
</div>

<script>
    function notify(type, message) {
    	notificationPane = $("#notification-pane");
    	notificationPane.addClass("hide");
    	
    	notificationPane.removeClass(function(index, className) {
    		return (className.match(/(^|\s)alert-\S+/g) || []).join (' ');
    	});
    	
    	notificationPane.addClass("alert-" + type);
    	notificationPane.find(".message").html(message);
    	
    	notificationPane.removeClass("hide");
    }
    
    function hideNotification() {
    	$("#notification-pane").addClass("hide");
    }
</script>