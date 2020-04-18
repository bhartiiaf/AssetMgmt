$(document).ready(function() {
	$("#formobjectone").hide();
	$("#hrone").hide();
	$("#hrtwo").hide();
	
	$("#addmoreitemsindraft").on('click',function(){
		
		$("#formobjectone").toggle();
		$("#hrone").toggle();
		$("#hrtwo").toggle();
	});
		
	
	$("#addmoreitemOne").on('click',function(e) {
				e.preventDefault();
				
				var codeHeadIdOne = $.trim($("#codeHeadOne").val());
				var codeHeadOne = $("#codeHeadOne option:selected").html();
				var itemTypeIdOne = $.trim($("#itemTypeIdOne").val());
				var itemTypeOne = $("#itemTypeIdOne option:selected").html();
				var itemSubTypeIdOne = $.trim($("#itemSubTypeIdOne").val());
				var itemSubTypeOne = $("#itemSubTypeIdOne option:selected").html();
				var itemQtyOne = $.trim($("#itemQtyOne").val());
				var demandReasonIdOne = $.trim($("#demandReasonOne").val());
				var demandReasonOne = $("#demandReasonOne option:selected").html();
				var demandAuthOne = $.trim($("#demandAuthOne").val());
							
								
				
				var moreDemandDataOne = "<tr>"
						+ "<td><input  type='hidden' id='codeHeadOne' name='codeHeadId' value="+ codeHeadIdOne+ ">"+ codeHeadOne+ "</td>"
						+ "<td><input  type='hidden' id='itemTypeIdOne' name='itemTypeId' value="
						+ itemTypeIdOne
						+ ">"
						+ itemTypeOne
						+ "</td>"
						+ "<td><input  type='hidden' id='itemSubTypeIdOne' name='itemSubTypeId' value="
						+ itemSubTypeIdOne
						+ ">"
						+ itemSubTypeOne
						+ "</td>"
						+ "<td><input  type='hidden' id='itemQtyOne' name='itemQty' value="
						+ itemQtyOne
						+ ">"
						+ itemQtyOne
						+ "</td>"
						+ "<td><input  type='hidden' id='demandReasonOne' name='demandReason' value='"
						+ demandReasonIdOne
						+ "'>"
						+ demandReasonOne
						+ "</td>"
						+ "<td><input  type='hidden' id='id' name='id' value=''><input  type='hidden' id='demandAuthOne' name='demandAuth' value='"
						+ demandAuthOne
						+ "'>"
						+ demandAuthOne
						+ "</td>"
						+ "<td><button class='removeone btn btn-danger'>-</button</td>"
						+ "</tr>";
				
				

				if (!codeHeadIdOne) {
					alert("Please Select Code Head");
				} else if (!itemTypeIdOne) {
					alert("Please Select Item Type");
				} else if (!itemSubTypeIdOne) {
					alert("Please Select Sub Item TYpe");
				} else if (!itemQtyOne) {
					alert("Please entre Item Qty");
				} else if (!demandReasonIdOne) {
					alert("Please select Demand Reason");
				} else if (!demandAuthOne) {
					alert("Please enter Demand Auth");
				} else {
					
					$("#databydemandno").append(
							moreDemandDataOne);
					
					$("#codeHeadOne").val('');
					$("#itemTypeIdOne").val('');
					$("#itemSubTypeIdOne").val('');
					$("#itemQtyOne").val('');
					$("#demandReasonOne").val('');
					$("#demandAuthOne").val('');
				}
						
			});
$(document).on('click', '.removeone', function() {
$(this).parents('tr').remove();


});



$("#itemTypeIdOne").on('change',function(e) {
			e.preventDefault();
			$("#itemSubTypeIdOne").find('option').remove();
			var itidOne = $("#itemTypeIdOne").val();
			$.ajax({
						type : 'GET',
						data : {'itemTypeId' : itidOne},
						url : "fetchsubitem",
						dataType : 'json',
						success : function(result) {
							$("#itemSubTypeIdOne").append($("<option value=''>"	+ 'Select Sub Item Type'+ "</option>"));
							for (var i = 0; i < result.length; i++) {
								$("#itemSubTypeIdOne").append($("<option></option>").attr("value",result[i][0].id).text(result[i][0].subTypeDesc));
							}
						}
					});
		});

$("#codeHeadOne").on('change',function(e) {
			e.preventDefault();
			$("#itemTypeIdOne").find('option').remove();
			$("#itemSubTypeIdOne").find('option').remove();
			var chidOne = $("#codeHeadOne").val();
			$.ajax({
						type : 'GET',
						data : {'codeHead' : chidOne},
						url : "fetchitemtypebycodehead",
						dataType : 'json',
						success : function(result) {
							$("#itemTypeIdOne").append($("<option value=''>"+ 'Select Item Type'+ "</option>"));
							for (var i = 0; i < result.length; i++) {

								
								$("#itemTypeIdOne").append($("<option></option>").attr("value",result[i][0].id).text(result[i][0].itemDescription));
							}
						}
					});
		});








					
					
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
																			for (var i = 0; i < result.length; i++) {
																				if (result[i][0].demandStatus == 'Finalised') {
																					$("#addmoreitemsindraft").hide();
																					$('#databydemandno').append(

																									$("<tr>"
																											+ "<td>"+ result[i][0].codeHeadId.codeHeadDescription+ "</td>"
																											+ "<td>"+ result[i][0].itemTypeId.itemDescription+ "</td>"
																											+ "<td>"+ result[i][0].itemSubTypeId.subTypeDesc+ "</td>"
																											+ "<td>"+ result[i][0].itemQty+ "</td>"
																											+ "<td>"+ result[i][0].demandReason+ "</td>"
																											+ "<td>"+ result[i][0].demandAuth+ "</td>"
																											+ "<td>"+ result[i][0].demandStatus+ "</td>"
																											+
																											"</tr>"

																									));
																				} else if (result[i][0].demandStatus == 'Draft') {
																					$("#addmoreitemsindraft").show();
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
																											+ "<td><input  type='text' id='itemQty' name='itemQty' class='form-control' value="
																											+ result[i][0].itemQty
																											+ "></td>"
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
																											+ "<td id='getDraftRow'><a href="
																											+ 'deletequery/'
																											+ result[i][0].id
																											+ "><i class='md-close text-danger'></i></a></td>"
																											+

																											"</tr>"

																									));
																				}
																			}

																			if (result[0][0].demandStatus == 'Draft') {
																				var submitButtonForDraft = "<input type='submit' class='btn btn-success' name='submitvalue' value='Draft'></button>"+' &nbsp; '+ "<input type='submit' class='btn btn-success' name='submitvalue' value='Finalised'></button>";
																				$(
																						"#demandfordraft")
																						.append(
																								submitButtonForDraft);

																				var rowCount = $("#databydemandno tr").length;
																				if (rowCount == 1) {
																					$(
																							"#getDraftRow")
																							.hide();
																				}

																			} else if (result[0][0].demandStatus == 'Finalised') {
																				$(
																						"#demandfordraft")
																						.html(
																								"");
																			}

																			$(
																					"#demandNoHeader")
																					.append(
																							$("<b>"
																									+ result[0][0].demandNoMaster.demandMasterNo
																									+ "</b>"));
																			
																			$("#databydemandno").append(xxxx);
																		}
																	});

														});
									});

					// Next Action//

					$("#seachDemand").on(
							'click',
							function() {
								$("#demandSearchTable").show();
								var demandNo = $("#demandNo").val();
								if (demandNo == '' || demandNo == null) {
									$("#demandNo").closest('.form-group')
											.addClass('has-error');
									$(".demandno").html("*Enter Demand No.");
									$("#demandNo").focus();
									return false;
								}

							});
					$("#demandNo").on(
							'keypress',
							function(e) {
								$("#demandNo").closest('.form-group')
										.removeClass('has-error');
								$(".demandno").html("");
							});

					$("#moreitem").hide();
					$("#draftdemand").hide();
					$("#raisedemand").hide();
					$("#demandSearchTable").hide();

					$('#area1')
							.attr('placeholder', 'This is a new placeholder');

					$("#demandReason").on(
							'change',
							function() {

								var demandReason = $("#demandReason").val();
								if (demandReason == 'Against Downgradation') {
									$("#demandAuth").attr('placeholder',
											'BOO Authority');
								} else if (demandReason == 'Fresh') {
									$("#demandAuth").attr('placeholder',
											'Reason for Demand');
								} else {
									$("#demandAuth").attr('placeholder',
											'Demand Authority');
								}
							});

					$("#addmoreitem")
							.on(
									'click',
									function(e) {
										e.preventDefault();
										var codeHeadId = $.trim($("#codeHead")
												.val());
										var codeHead = $(
												"#codeHead option:selected")
												.html();
										var itemTypeId = $
												.trim($("#itemTypeId").val());
										var itemType = $(
												"#itemTypeId option:selected")
												.html();
										var itemSubTypeId = $.trim($(
												"#itemSubTypeId").val());
										var itemSubType = $(
												"#itemSubTypeId option:selected")
												.html();
										var itemQty = $.trim($("#itemQty")
												.val());
										var demandReasonId = $.trim($(
												"#demandReason").val());
										var demandReason = $(
												"#demandReason option:selected")
												.html();
										var demandAuth = $
												.trim($("#demandAuth").val());
										var moreDemandData = "<tr>"
												+ "<td><input  type='hidden' id='codeHead' name='codeHeadId' value="
												+ codeHeadId
												+ ">"
												+ codeHead
												+ "</td>"
												+ "<td><input  type='hidden' id='itemTypeId' name='itemTypeId' value="
												+ itemTypeId
												+ ">"
												+ itemType
												+ "</td>"
												+ "<td><input  type='hidden' id='itemSubTypeId' name='itemSubTypeId' value="
												+ itemSubTypeId
												+ ">"
												+ itemSubType
												+ "</td>"
												+ "<td><input  type='hidden' id='itemQty' name='itemQty' value="
												+ itemQty
												+ ">"
												+ itemQty
												+ "</td>"
												+ "<td><input  type='hidden' id='demandReason' name='demandReason' value='"
												+ demandReasonId
												+ "'>"
												+ demandReason
												+ "</td>"
												+ "<td><input  type='hidden' id='demandAuth' name='demandAuth' value='"
												+ demandAuth
												+ "'>"
												+ demandAuth
												+ "</td>"
												+ "<td><button class='remove btn btn-danger'>-</button</td>"
												+ "</tr>";

										if (!codeHeadId) {
											alert("Please Select Code Head");
										} else if (!itemTypeId) {
											alert("Please Select Item Type");
										} else if (!itemSubTypeId) {
											alert("Please Select Sub Item TYpe");
										} else if (!itemQty) {
											alert("Please entre Item Qty");
										} else if (!demandReasonId) {
											alert("Please select Demand Reason");
										} else if (!demandAuth) {
											alert("Please enter Demand Auth");
										} else {
											$("#moreitem").show();
											$("#pushdata").append(
													moreDemandData);
											$("#draftdemand").show();
											$("#raisedemand").show();
											$("#codeHead").val('');
											$("#itemTypeId").val('');
											$("#itemSubTypeId").val('');
											$("#itemQty").val('');
											$("#demandReason").val('');
											$("#demandAuth").val('');
										}
									});
					$(document).on('click', '.remove', function() {
						$(this).parents('tr').remove();

						var rowCount = $("#pushdata tr").length;
						if (rowCount == 0) {
							$("#moreitem").hide();
							$("#draftdemand").hide();
							$("#raisedemand").hide();

						}
					});

					/*
					 * $('#deleteRow').click(function(){ alert('Delete data');
					 * $("#pushdata
					 * tbody").find('input[name="record"]').each(function(){
					 * if($(this).is(":checked")){
					 * $(this).parents("tr").remove(); } }); });
					 */

					/*
					 * $("#codeHead").on('change',function(e){
					 * e.preventDefault();
					 * 
					 * var chid = $("#codeHead").val(); // alert(chid); $.ajax({
					 * type : 'GET', data : {'codeHead' : chid}, url :
					 * "codeheaddata", dataType: 'json', success:
					 * function(result) { for(var i=0; i<result.length; i++){
					 * 
					 * $("#codeHeadDescription").val(result[i][0].codeHeadDescription);
					 * $("#codeHeadType").val(result[i][0].codeHeadType); } }
					 * }); });
					 */

					$("#itemTypeId")
							.on(
									'change',
									function(e) {
										e.preventDefault();
										$("#itemSubTypeId").find('option')
												.remove();
										var itid = $("#itemTypeId").val();
										$
												.ajax({
													type : 'GET',
													data : {
														'itemTypeId' : itid
													},
													url : "fetchsubitem",
													dataType : 'json',
													success : function(result) {
														$("#itemSubTypeId")
																.append(
																		$("<option value=''>"
																				+ 'Select Sub Item Type'
																				+ "</option>"));
														for (var i = 0; i < result.length; i++) {
															$("#itemSubTypeId")
																	.append(
																			$(
																					"<option></option>")
																					.attr(
																							"value",
																							result[i][0].id)
																					.text(
																							result[i][0].subTypeDesc));
														}
													}
												});
									});

					$("#codeHead")
							.on(
									'change',
									function(e) {
										e.preventDefault();
										$("#itemTypeId").find('option')
												.remove();
										$("#itemSubTypeId").find('option')
												.remove();
										var chid = $("#codeHead").val();
										$
												.ajax({
													type : 'GET',
													data : {
														'codeHead' : chid
													},
													url : "fetchitemtypebycodehead",
													dataType : 'json',
													success : function(result) {
														$("#itemTypeId")
																.append(
																		$("<option value=''>"
																				+ 'Select Item Type'
																				+ "</option>"));
														for (var i = 0; i < result.length; i++) {

															// var x = "<option
															// value="+result[i][0].id+">"+result[i][0].itemDescription+"</option>"
															$("#itemTypeId")
																	.append(
																			$(
																					"<option></option>")
																					.attr(
																							"value",
																							result[i][0].id)
																					.text(
																							result[i][0].itemDescription));
															//  
														}
													}
												});
									});

					$("#raisedemand")
							.on(
									'click',
									function() {
										var codeHead = $("#codeHead").val();
										var itemTypeId = $("#itemTypeId").val();
										var itemQty = $("#itemQty").val();
										var demandReason = $("#demandReason")
												.val();
										var demandAuth = $("#demandAuth").val();

										if (codeHeadId == null
												|| codeHeadId == '') {
											$("#codeHead").closest(
													'.form-group').addClass(
													'has-error');
											$(".codehead").html(
													"*Select Code Head.");
											$("#codeHead").focus();
											return false;
										} else if (itemTypeId == null
												|| itemTypeId == '') {
											$("#itemTypeId").closest(
													'.form-group').addClass(
													'has-error');
											$(".itemtypeid").html(
													"*Select Item Type.");
											$("#itemTypeId").focus();
											return false;
										} else if (itemQty == null
												|| itemQty == '') {
											$("#itemQty")
													.closest('.form-group')
													.addClass('has-error');
											$(".itemqty").html(
													"*Enter Item Qty.");
											$("#itemQty").focus();
											return false;
										} else if (demandReason == null
												|| demandReason == '') {
											$("#demandReason").closest(
													'.form-group').addClass(
													'has-error');
											$(".demandreason").html(
													"*Select Demand Reason.");
											$("#demandReason").focus();
											return false;
										} else if (demandAuth == null
												|| demandAuth == '') {
											$("#demandAuth").closest(
													'.form-group').addClass(
													'has-error');
											$(".demandauth").html(
													"*Enter Demand Authority.");
											$("#demandAuth").focus();
											return false;
										}

									});

					$("#codeHead").on(
							'change',
							function(e) {
								$("#codeHead").closest('.form-group')
										.removeClass('has-error');
								$(".codehead").html("");
							});
					$("#itemTypeId").on(
							'change',
							function(e) {
								$("#itemTypeId").closest('.form-group')
										.removeClass('has-error');
								$(".itemtypeid").html("");
							});
					$("#itemQty").on(
							'keypress',
							function(e) {
								$("#itemQty").closest('.form-group')
										.removeClass('has-error');
								$(".itemqty").html("");
							});
					$("#demandReason").on(
							'change',
							function(e) {
								$("#demandReason").closest('.form-group')
										.removeClass('has-error');
								$(".demandreason").html("");
							});
					$("#demandAuth").on(
							'keypress',
							function(e) {
								$("#demandAuth").closest('.form-group')
										.removeClass('has-error');
								$(".demandauth").html("");
							});

				});
