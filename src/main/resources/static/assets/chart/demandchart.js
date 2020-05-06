$(document).ready(function(){
	
	$('#totaldemandraisedbyunit').text('');
	$('#totaldemandraisedbyunit').append('<canvas id="totaldemandraisedbyunit"></canvas>');
	
	
	$.ajax({
        type: 'GET',
        url: 'alldemandbyunit',
        dataType: 'json',
        success: function (res, textStatus, jqXHR) {
       	var unitName=[];
        var dData = [];
        var abr = [];
       	for(var i=0; i<res.length; i++){
       		unitName.push(res[i][1]);
       		dData.push(res[i][0]);
       		abr.push("Total Demand of " +res[i][1] + " is : " + res[i][0])
       	}
       	var sumofpendingandapproved = dData.reduce((a,b)=> a+b,0);
       	$("#totaldemand").append(sumofpendingandapproved);
       	new Chart(document.getElementById("totaldemandraisedbyunit"), {
   	    type: 'doughnut',
   	    data: {
   	    	labels: ["Approved","Pending"],
	    	  	datasets: [
	       	        {
	       	          label: "Unit Name",
	       	          backgroundColor: ['green','red'],
	       	          data: dData,
	       	        }
	       	      ]
   	    },
   	
   	    options: {
   	    	circumference: 1 * Math.PI,
            rotation: 1 * Math.PI,
            cutoutPercentage: 90
   	    		
			}
   	    
   	});
		}
});	
	
	
});