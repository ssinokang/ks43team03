
/* 레슨 예약 하기 */
function addLesson(fixedDate) {
	var lessonStart = $('input[name="lessonStartTime"]');
	var lessonEnd   = $('input[name="lessonEndTime"]');
	var idx = 0;

	//포커스 이벤트 발생시 위치 저장
	$(document).on('focus', '.inputModal.lesson', function() {
		if($(this).attr("name") == 'lessonStartTime') {
			idx = 0;
		} else if($(this).attr("name") == 'lessonEndTime') {
			idx = 1;
		}
	});

	$(document).on('click','.reservation.possible', function() {
		
		var yearMon = $(this).parent().attr("data-date");
		
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
			}
		})
		
	});
	
	//이전에 포커스를 했다면 그 위치에 밸류값 삽입
	$(document).on('click','.reservation-possible', function() {
		if(idx == 0) {
			lessonStart.val($(this).val());
			idx = 1;
		} else {
			lessonEnd.val($(this).val());
			idx = 0;
		}
	});
	//예약 가능 버튼 눌렀을 때 날짜 가져오기
	
}