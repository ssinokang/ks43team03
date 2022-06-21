
$(function() {
	var $stadium  = $('#stadium');
	var $pass 	  = $('#pass');
	var $lesson   = $('#lesson');
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

		var indexNum = e.target.selectedIndex;
		
		for(var i = 1; i < e.target.length; i++) {
			ctg[i-1].addClass('none-display');
			if(indexNum == i) {
				ctg[i-1].removeClass('none-display');
			}
		}
	})
	$(document).on('click', '#register-goods', function() {
		if($(this).html() == '등록') {
			$('#facilityCd').prop('disabled', true);
			$('#sportCtg').prop('disabled', true);
			$('#goodsCtg').prop('disabled', true);
			
			$(this).html('취소');
		} else {
			$('#facilityCd').prop('disabled', false);
			$('#sportCtg').prop('disabled', false);
			$('#goodsCtg').prop('disabled', false);
			
			$(this).html('등록');
		}
	});
	/**
	 *  다중 파일 업로드
	 **/
	var fileInput = 1;
	var maxFileNum = 4;
	var minFileNum = 0;
	//추가
	$('#plus-button').on('click', function() {
		if(fileInput < maxFileNum) {
			var newLi = $(this).parent().clone(true);
			newLi.children('.lesson-img').val('');
			$(this).parent().parent().append(newLi);
			
			fileInput++;
		} else {
			alert("최대 올릴 수 있는 이미지의 개수는 4개입니다.");
		}
	});
	//제거
	$('#minus-button').on('click', function() {
		if(fileInput > 0) {
			$(this).parent().remove()
			
			fileInput--;
		} else {
			alert("더이상 지울 수 없습니다.");
		}
	});

	$sportsCtgVal = $('#sportsCtg').attr('data-sport');
	$goodsCtgVal  = $('#goodsCtg').attr('data-goodsCtg');
	console.log($sportsCtgVal);
	 $('#sportsCtg option[value=' + $sportsCtgVal + ']').attr('selected', 'selected');
	 $('#goodsCtg option[value=' + $goodsCtgVal + ']').attr('selected', 'selected');

});