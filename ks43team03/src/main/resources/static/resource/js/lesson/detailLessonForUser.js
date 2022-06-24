/** class="cart-btn" 
 * 
 */

$(function() {
	$('#regist').on('click', function() {
		
		var resDate = $('.resDate input[type = date]').val();
		var resTime = $('.resTime input[type = time]').val();
		
		console.log(resDate);
		console.log(resTime);
		
		$('#res-Date').val(resDate);
		$('#res-Time').val(resTime);
		
		$('#reservation-view').removeClass('none-display');
	})
});
