$(document).ready(function() {
	$(".fetchitemsubtypedata").each(function() {
		
				$(this).click(function(e) {
					$("#itemsubtypeupdate").empty();
							$('#databyitemsubtype').empty();
							$("#updatebuttonforitemsubtype").empty();

							e.preventDefault();
							var urld = $(this).attr('href');
							$.ajax({
								url : urld,
								dataType : 'json',
								success : function(result) {
										
									for (var i = 0; i < result.length; i++) {
										
										$("#itemsubtypeupdate").append($("<tr>"
												+ "<td><input  type='hidden' id='itemTypeId' name='itemTypeId' value='"+ result[i][0].itemTypeId.id	+ "'>"
												+ result[i][0].itemTypeId.itemDescription
												+ "</td>"
												+ "<td><input  type='text' class='form-control' id='subTypeDesc' name='subTypeDesc' value='"+ result[i][0].subTypeDesc+ "'>"
												+ "</td>"
												+ "<td><input  type='hidden' id='id' name='id' value='"+ result[i][0].id+ "'><input  type='text' class='form-control' id='subItemPrice' name='subItemPrice' value='"
												+ result[i][0].subItemPrice+ "'>"
												+ "</td>"
												+ "</tr>"));
										
										
									}
									var updatebutton = "<input type='submit' class='btn btn-success' name='updatesubitem' value='Update'></button>";

									$("#updatebuttonforitemsubtype").append(updatebutton);
								}

								});
						});
				
	});
});
