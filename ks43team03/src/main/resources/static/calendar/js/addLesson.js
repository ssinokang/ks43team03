
/* 레슨 예약 하기 */
function addLesson(fixedDate) {
	var reservationStartTime = $('input[name="reservationStartTime"]');
	var reservationEndTime   = $('input[name="reservationEndTime"]');
	var clickDay = '';
	reservationImpossibleTime = $('.reservation-impossible');

	$(document).on('click','.reservation.possible', reservationPossible);
	console.log(fixedDate);
	function reservationPossible() {
		reservationStartTime.val('');
		reservationEndTime.val('');
		
		var yearMon = $(this).parent().attr("data-date");
		clickDay = yearMon; //전역 변수에 클릭한 날짜 저장

		fixedDate.reservation.forEach(x => {
			var lessonDate = x.reservationDate;
			if(lessonDate == yearMon) {
				$('.lessonTime').each(function(){
					hours = moment($(this).val(), "HH:mm");
					
					startTime = moment(x.reservationStartTime).format("HH:mm");
					endTime   = moment(x.reservationEndTime).format("HH:mm");
					
					if(hours.isSameOrAfter(startTime) && hours.isSameOrBefore(endTime)) {
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
	//
	$(document).on('click','.reservation-possible', function() {
		reserveTime = moment($(this).val(), 'HH:mm');
		lessonTime  = fixedDate.lessonTime;
		reservationStartTime.val(reserveTime.format('HH:mm'));
		//선택한 시간 + 레슨 하는 시간
		reservationEndTime.val(reserveTime.add(lessonTime, 'hour').format('HH:mm'));
		
		if(reservationImpossibleTime != null) {
			reservationImpossibleTime.each(function() {
				if($(this).val() == reservationEndTime.val()) {
					alert('예약할 수 없는 시간입니다.')
				}
				else {
					reservationEndTime.val(reservationEndTime.val());
				}
			});
		}
		
		
	});
	//예약 하기
	$('#updateEvent').on('click', function() {

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
						else if (result == "0"){
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