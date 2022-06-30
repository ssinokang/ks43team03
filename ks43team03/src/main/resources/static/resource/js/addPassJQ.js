
$(function() {
	var $stadium  	 = $('#stadium');
	var $pass 	  	 = $('#pass');
	var $lesson   	 = $('#lesson');
	var $goodsCtg 	 = $('#goodsCtg');
	var $lessonNu 	 = $('#lessonNu');
	var $lessonPrice = $('#lessonPrice');
	
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
	//코드 중복 제거 할 것
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
	var fileInput 	= 1;
	var lessonInput = 1;
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
		if(fileInput > 1) {
			$(this).parent().remove()
			
			fileInput--;
		} else {
			alert("더이상 지울 수 없습니다.");
		}
	});

	
	$goodsCtgVal = $('#goodsCtg').attr('data-goodsCtgCd');
	console.log($goodsCtgVal);
	$('#goodsCtg option[value=' + $goodsCtgVal + ']').attr('selected', 'selected');
	
    $('input:radio[name="lessonDivision"]').change(function() {
        if ($(this).val() === '개인') {
            $lessonNu.removeClass('none-display');
        } else {
        	$lessonNu.addClass('none-display');
        }
    });
    
    $(document).ready(function() {        												// INPUT 박스에 들어간 ID값을 적어준다.
        $("#START_TIME,#END_TIME").timepicker({            
				'minTime': '09:00am', 												// 조회하고자 할 시작 시간 ( 09시 부터 선택 가능하다. )
	            'maxTime': '20:00pm', 												// 조회하고자 할 종료 시간 ( 20시 까지 선택 가능하다. )
	            'timeFormat': 'H:i',
	            'step': 30 															// 30분 단위로 지정. ( 10을 넣으면 10분 단위 )
	    });
        $(window).scroll(function(){
	        $(".ui-timepicker-wrapper").hide();
	    });
	});
    	
    

});