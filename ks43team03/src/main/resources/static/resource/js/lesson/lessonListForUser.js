$(function() {
	$searchBar = $('#searchBar')
	$searchBar.on('keydown', function(e){
		if(e.keyCode == 13) {
			$(this).parent().parent().submit();
		}
	});
});