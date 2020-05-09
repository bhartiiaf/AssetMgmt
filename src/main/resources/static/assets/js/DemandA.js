$(document).ready(function(){
	
	
	$(".fetchmoredatafordemand").each(function() {
		$(this).click(function(e) {
			$('#databydemandno').empty();
			$('#demandNoHeader').empty();
			$("#demandfordraft").empty();
							e.preventDefault();
							var urld = $(this).attr('href');
							$.ajax({
										url : urld,
										dataType : 'json',
										success : function(result) {
											var dmasterno = result[0][0].demandNoMaster.demandMasterNo;
											$("#demandNoHeader").append(dmasterno);
											var submitButtonForDraftData = "<input type='submit' class='btn btn-success' name='submitvalue' value='Draft'></button>"+' &nbsp; '+ "<input type='submit' class='btn btn-success' name='submitvalue' value='Finalised'></button>";
											$("#demandfordraft").append(submitButtonForDraftData);

											for (var i = 0; i < result.length; i++) {
												
												
												if (result[i][0].demandStatus == 'Finalised' && result[i][0].demandNoMaster.demandLevel == 'AHQ') {
													var xxxx = "<input type='hidden' name='demandNoMaster' value='"+ result[i][0].demandNoMaster.id+ "'>";
													
													$('#databydemandno').append(

																	$("<tr>"
																			+ "<td><input  type='hidden' id='codeHead' name='codeHeadId' value='"
																			+ result[i][0].codeHeadId.id
																			+ "'>"
																			+ result[i][0].codeHeadId.codeHeadDescription
																			+ "</td>"
																			+ "<td><input  type='hidden' id='itemTypeId' name='itemTypeId' value='"
																			+ result[i][0].itemTypeId.id
																			+ "'>"
																			+ result[i][0].itemTypeId.itemDescription
																			+ "</td>"
																			+ "<td><input  type='hidden' id='itemSubTypeId' name='itemSubTypeId' value='"
																			+ result[i][0].itemSubTypeId.id
																			+ "'>"
																			+ result[i][0].itemSubTypeId.subTypeDesc
																			+ "</td>"
																			+ "<td><input  type='hidden' id='itemQty' name='itemQty' class='form-control' value="
																			+ result[i][0].itemQty
																			+ ">"+ result[i][0].itemQty+"</td>"
																			+ "<td><input  type='hidden' id='demandReason' name='demandReason' value='"
																			+ result[i][0].demandReason
																			+ "'>"
																			+ result[i][0].demandReason
																			+ "</td>"
																			+ "<td><input  type='hidden' id='demandNoMaster' name='demandNoMaster' value='"
																			+ result[i][0].demandNoMaster.id
																			+ "'><input  type='hidden' id='id' name='id' value='"
																			+ result[i][0].id
																			+ "'><input  type='hidden' id='demandAuth' name='demandAuth' value='"
																			+ result[i][0].demandAuth
																			+ "'>"
																			+ result[i][0].demandAuth
																			+ "</td>"
																			+ "<td><input  type='hidden' id='demandStatus' name='demandStatus' value='"
																			+ result[i][0].demandStatus
																			+ "'>"
																			+ result[i][0].demandStatus
																			+ "</td>"
																			+ "<td><input  type='hidden' id='cmdApprovedQty' name='cmdApprovedQty' value='"
																			+ result[i][0].cmdApprovedQty
																			+ "'>"
																			+ result[i][0].cmdApprovedQty
																			+ "</td>"
																			+ "<td><input  type='hidden' id='cmdRemarks' name='cmdRemarks' value='"
																			+ result[i][0].cmdRemarks
																			+ "'>"
																			+ result[i][0].cmdRemarks
																			+ "</td>"
																			
																			+ "<td><input class='form-control'  type='text' id='ditApprovedQty' name='ditApprovedQty' value='"
																			+ result[i][0].ditApprovedQty
																			+ "'>"
																			+ "</td>"
																			+ "<td><textarea cols=20 class='form-control' id='ditRemarks' name='ditRemarks'>"+ result[i][0].ditRemarks+"</textarea>"
																			+ "</td>"
																			
																			+

																			"</tr>"

																	));
												}
												
												
												
												
											}

											if (result[i][0].demandStatus == 'Finalised' && result[i][0].demandNoMaster.demandLevel == 'AHQ') {
												
												var rowCount = $("#databydemandno tr").length;
												if (rowCount == 1) {
													$("#getDraftRow").hide();
												}

											} 
											
											$("#databydemandno").append(xxxx);
										}
									});

						});
	});

	
});