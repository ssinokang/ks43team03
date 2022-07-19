
/* 레슨 예약 하기 */
function addLesson(fixedDate) {
	var reservationStartTime = $('input[name="reservationStartTime"]');
	var reservationEndTime   = $('input[name="reservationEndTime"]');
	var clickDay = '';
	var idx = 0;

	//포커스 이벤트 발생시 위치 저장
	$(document).on('focus', '.inputModal.lesson', function() {
		if($(this).attr("name") == 'reservationStartTime') {
			idx = 0;
		} else if($(this).attr("name") == 'reservationEndTime') {
			idx = 1;
		}
	});

	$(document).on('click','.reservation.possible', reservationPossible);
	
	function reservationPossible() {
		reservationStartTime.val('');
		reservationEndTime.val('');
		
		var yearMon = $(this).parent().attr("data-date");
		clickDay = yearMon; //전역 변수에 클릭한 날짜 저장
		fixedDate.lessonReservation.forEach(x => {
			var lessonDate = x.reservationDate;
			if(lessonDate == yearMon) {
				$('.lessonTime').each(function(){
					hours = moment($(this).val(), "HH:mm");
					
					startTime = moment(x.reservationStartTime, "HH:mm");
					endTime   = moment(x.reservationEndTime, "HH:mm");
	
					if(moment(hours).isSameOrAfter(startTime) && moment(hours).isSameOrBefore(endTime)) {
						$(this).removeClass('reservation-possible');
					}
				});
			} else {
				$('.lessonTime').each(function(){
					hours = moment($(this).val(), "HH:mm");
					if(hours.isSameOrAfter(lessonStartTime) && hours.isSameOrBefore(lessonEndTime)) {
						$(this).addClass('reservation-possible');
					}
				});
			}
		})
	}
	//이전에 포커스를 했다면 그 위치에 밸류값 삽입
	$(document).on('click','.reservation-possible', function() {
		if(idx == 0) {
			reservationStartTime.val($(this).val());
			idx = 1;
		} else {
			reservationEndTime.val($(this).val());
			idx = 0;
		}
	});
	//예약 하기
	$('#updateEvent').on('click', function() {
		console.log(clickDay)
		console.log(reservationStartTime.val())
		console.log(reservationEndTime.val())
	    const data = {
			reservationDate 	 : clickDay,
			reservationStartTime : reservationStartTime.val(),
			reservationEndTime	 : reservationEndTime.val(),
			facilityGoodsCd 	 : $('#facilityGoodsCd').val(),
			reservationCtg       : 'lesson'
		}
		swal({
			title: '😦정말 예약하시겠습니까?',
			showCancelButton: true,
			confirmButtonText: '예약',
			showLoaderOnConfirm: true,
			allowOutsideClick: false
	    }).then((result) => {
			if (result.value) {
				var request = $.ajax({
					url: "/calendar/reservation",
					method: "POST",
					contentType: 'application/json; charset=utf-8',
					data: JSON.stringify(data), 
					dataType: "json",
	   			});
	                
				request.done(function( data ) { //true 일 때 실행
					console.log(data);
					let result = data.result;
						if(result == "1"){
							swal({
								type: 'success',
								title: '🎉 예약이 완료되었습니다.',
							}).then(() => { 
								//location.href = data.movePage;                      
							});
						}
						else if (result == "2"){
							swal({
								type: 'error',
								title: '❌예약할 수 없는 시간입니다.❗',
							}).then(()=>{
								//location.href = data.movePage;
							});
						} 
					});
					request.fail(function( jqXHR, textStatus ) {
					alert( "Request failed: " + textStatus );
				});
			};
		})
	});
};