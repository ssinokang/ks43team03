/** class="cart-btn" 
 * 
 */

$(function() {
	$('#like').on('click', function() {
		console.log("작동");
		var data = sessionStorage.getItem("SID");
		console.log(data);
		var data = sessionStorage.getItem("SNAME");
		console.log(data);
		var data = sessionStorage.length
		console.log(data);
		/*if (data.facilityCd == null) {
				alert('입력하지않은 폼이 있습니다.');
				return false;
		} else {
			$.ajax({
				type: 'POST',
				url: '/api/goods',
				dataType: 'JSON',
				contentType: 'application/json; charset=utf-8',
				data: JSON.stringify(data)
			}).done(function (fg) {

				console.log(fg.goodsCtgCd);
				
				$('#gscode').val(fg.facilityGoodsCd);
				if (fg.goodsCtgCd == 'lesson') {
					$lesson.css('display', 'block');
				}
				if (fg.goodsCtg == 'pass') {
					$pass.css('display', 'block');
				}
				if (fg.goodsCtg == 'stadium') {
					$stadium.css('display', 'block');
				}
				//facilityGoodsCd = fg.facilityGoodsCd;
				
				$('#facilityCd-lesson').val(fg.facilityCd);
				$('#facilityGoodsCd-lesson').val(fg.facilityGoodsCd);
				$('#goodsCtgCd-lesson').val(fg.goodsCtgCd);
				$('#userId-lesson').val(fg.userId);
				$('#sportsCd-lesson').val(fg.sportsCd);

				productData = fg;
			}).fail(function (error) {
				alert(JSON.stringify(error));
			});
			
		}*/
	})
});
