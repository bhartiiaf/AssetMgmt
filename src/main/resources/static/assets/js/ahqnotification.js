$(document).ready(function(){

	 $.ajax({
		type : 'GET',
		url : "notificationforahq",
		success : function(result) {
			for (var i = 0; i < result.length; i++) {
				var dnm = result[i].demandMasterNo;
	 			var sts = result[i].demandStatus;
	 			var notif = dnm +'-' + ' Pending For Action';
	 			var nopending = 'No Pending Notification for You';
	 			
	 			$("#notification").append('&nbsp;<i class="icon md-receipt bg-red-600 white icon-circle" aria-hidden="true"></i> &nbsp;'+notif+'<br />');
	 			
	 			
			}
			var countr = result.length;
			$("#count").append(countr);
			$("#count1").append(countr);
	  }
	});
	 
	 
	
	
})