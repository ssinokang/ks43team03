
$(function() {
	var $stadium = $('#stadium');
	var $pass = $('#pass');
	var $lesson = $('#lesson');
	var $goodsCtg = $('#goodsCtg')

	var ctg = new Array();
	
	ctg.push($lesson);
	ctg.push($pass);
	ctg.push($stadium);
	
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
					
					$('#facilityCd-lesson').val(fg.facilityCd);
					$('#facilityGoodsCd-lesson').val(fg.facilityGoodsCd);
					$('#goodsCtgCd-lesson').val(fg.goodsCtgCd);
					$('#userId-lesson').val(fg.userId);
					$('#sportsCd-lesson').val(fg.sportsCd);
					console.log(fg);
					console.log(fg.userId);
					console.log(fg.goodsCtgCd);
					console.log($('#facilityCd-lesson').val());
					console.log($('#facilityGoodsCd-lesson').val());
					console.log($('#goodsCtgCd-lesson').val());
					console.log($('#userId-lesson').val());
					console.log($('#sportsCd-lesson').val());
					productData = fg;
				}).fail(function (error) {
					alert(JSON.stringify(error));
				});
				
			}
	
		}

	}
	
	$lesson.on('click', function () {
	})
	main.init();
	
	$goodsCtg.on('change', function(e) {
		console.log(e.target.indexOf('lesson'));
		console.log(e.target.length);
		console.log(e.target.index);
		console.log(e.target.index());
		console.log($(e.target).index());
		
		for(var i = 1; i < e.target.length; i++) {
			ctg[i].addClass("none-display");
			
			if($('e.target').index() == i-1) {
				ctg[i].removeClass('none-display');
			}
		}
		/*
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
		*/
	})
	$(document).on('click', '#register-goods', function() {
		$('#facilityCd').prop('disabled', true);
		$('#sportCtg').prop('disabled', true);
		$('#goodsCtg').prop('disabled', true);
	});
});