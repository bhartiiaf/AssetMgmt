$(document).ready(function() {
	$(".fetchitemsubtypedata")
	.each(
			function() {
				$(this)
				.click(
						function(e) {
							$(
									'#databyitemsubtype')
									.empty();

							e.preventDefault();
							var urld = $(this)
							.attr(
									'href');
							$
							.ajax({
								url : urld,
								dataType : 'json',
								success : function(result) {
										
									for (var i = 0; i < result.length; i++) {
										alert(result[i][0]);
										$("#msubItemPrice").text=result.subTypeDesc;

									}
								}

								});
						});
