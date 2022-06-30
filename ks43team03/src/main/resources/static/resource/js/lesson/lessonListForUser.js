$(function() {
	$searchBar = $('#searchBar');
	$areaCity  = $('#areaCity');
	$searchBar.on('keydown', function(e){
		if(e.keyCode == 13) {
			$(this).parent().parent().submit();
		}
	});
	
	$('#area').on('change', function(){
		console.log($(this).val());
		getAreaCityList($(this).val());
	});
	
	function getAreaCityList (areaCd) {
		var AreaCity = {
			areaCd : areaCd
		}
		
		$.ajax({
			type: 'POST',
			url: '/api/areaCity',
			dataType: 'JSON',
			contentType: 'application/json; charset=utf-8',
			data: JSON.stringify(AreaCity)
		}).done(function (areaList) {
			console.log(areaList);
			console.log($areaCity.children());

			areaList.forEach((al) => {
				var areaOption = "";
    
			     areaOption = "<option value='" + al.cityCd + "'>" + al.cityNm + "</option>";
				
			    
			    $('#areaCity').append(areaOption);
			    
			});
		}).fail(function (error) {
			alert(JSON.stringify(error));
		});
		
		}
});