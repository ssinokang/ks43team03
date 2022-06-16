$(function() {
	var $stadium = $('#stadium');
	var $pass = $('#pass');
	var $lesson = $('#lesson');
	var $goodsCtg = $('#goodsCtg')
	var productData;
	//var facilityGoodsCd = '';
	const main = {


		init: function () {
			var _this = this;
			$('#register-goods').on('click', function () {
				_this.save();
			})
		},

		save: function () {
			const data = {
				facilityCd: $('#facilityCd').val(),
				userId: $('#user-id').val(),
				goodsCtgCd: $('#goodsCtg').val(),
				sportsCd: $('#sportCtg').val()
			};

			if (data.facilityCd == null) {
				alert('입력하지않은 폼이 있습니다.');
				return false;
			} else {
				$.ajax({
					type: 'POST',
					url: '/api/pass',
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
					$('#facilityCd2').val(fg.facilityCd);
					$('#facilityGoodsCd').val(fg.facilityGoodsCd);
					$('#userId2').val(fg.userId);
					$('#sportCtg2').val(fg.sportCtg);
					console.log(fg.facilityGoodsCd);
					console.log(fg.facilityGoodsCd);
					productData = fg;
				}).fail(function (error) {
					alert(JSON.stringify(error));
				});
				
			}
	
		}
	}
	
	$lesson.on('click', function () {
		console.log(productData);
		console.log(productData.facilityGoodsCd);
//		console.log(facilityGoodsCd);
	})
	main.init();
	
	$goodsCtg.on('change', function(e) {
		if(this.value == 'lesson') {
			$lesson.removeClass("none-display");
			$pass.addClass("none-display");
			$stadium.addClass("none-display");
		} else if (this.value == 'pass') {
			$pass.removeClass("none-display");
			$stadium.addClass("none-display");
			$lesson.addClass("none-display");
		} else if(this.value == 'stadium'){
			$stadium.removeClass("none-display");
			$pass.addClass("none-display");
			$lesson.addClass("none-display");
		} else {
			$lesson.addClass("none-display");
			$pass.addClass("none-display");
			$stadium.addClass("none-display");
		}
	})
	
});