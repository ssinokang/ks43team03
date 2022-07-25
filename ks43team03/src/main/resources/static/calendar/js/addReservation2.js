
/* 대관 예약 하기 */
function addReservation(fixedDate) {
   var reservationStartTime = $('input[name="reservationStartTime"]');
   var reservationEndTime   = $('input[name="reservationEndTime"]');
   var clickDay = '';
   var orderCheck = true;

   $(document).off().on('click','.reservation.possible', reservationPossible);
   
   function reservationPossible() {
      
      reservationStartTime.val('');
      reservationEndTime.val('');
      
      var yearMon = $(this).parent().attr("data-date");
      clickDay = yearMon; //전역 변수에 클릭한 날짜 저장
      
      $('.reservationTime').each(function(){
         $(this).addClass('reservation-possible');
      });
      
      
      fixedDate.reservation.forEach(x => {
         var lessonDate = x.reservationDate;
         if(lessonDate == yearMon) {
            
            $('.reservationTime').each(function(){
               
               mHours = moment($(this).val(),'HH:mm');
               mReservationStart = moment(x.reservationStartTime, 'HH:mm');
               mReservationEnd   = moment(x.reservationEndTime, 'HH:mm');
               
               if(moment(mHours).isSameOrAfter(mReservationStart) && moment(mHours).isSameOrBefore(mReservationEnd)) {
                  console.log('작동');
                  $(this).addClass('reservation-impossible');
                  $(this).removeClass('reservation-possible');
               }
            });   
         }
      })
   } 
   
   //
   $(document).on('click','.reservation-possible', function() {
      console.log('동작');
      reserveTime = moment($(this).val(), 'HH:mm');
      lessonTime  = fixedDate.lessonTime;
      
      reservationStartTime.val(reserveTime.format('HH:mm'));
      
      var reservationImpossibleTime = $('.reservation-impossible');
      //선택한 시간 + 레슨 하는 시간
      reservationEndTime.val(reserveTime.add(2, 'hour').format('HH:mm'));
      console.log(reservationEndTime.val());
      if(reservationImpossibleTime != null) {
         console.log('동작1')
         if(reservationImpossibleTime.length != 0) {
            reservationImpossibleTime.each(x => {
               console.log(reservationImpossibleTime.eq(x).val());
               if(reservationImpossibleTime.eq(x).val() == reservationEndTime.val()) {
                  alert('예약할 수 없는 시간입니다.')
                  reservationStartTime.val('');
                  reservationEndTime.val('');
               }
               else {
                  console.log('동작2')
                  var prame = getPrice(fixedDate, reservationStartTime, reservationEndTime);
                  console.log(prame);
               }
            });
         } else {
	         console.log('동작3');
	         var price = 0;
	         price = getPrice(fixedDate, reservationStartTime.val(), reservationEndTime.val());
	         console.log(price)
         }
      }
   });
   //예약 하기
   $('#updateEvent').on('click', function() {
      console.log(clickDay);
   });
   //가격 구하기 함수
   /*
      if(예약 날짜 == 공휴일)
         반절 가격a = 끝나는 시간 - 야간기준시 ? > 0 (일반가+야간가+공휴일가)*(야간기준시 - 끝나는 시간) : (일반가+공휴일가)*(|야간가 - 끝나는 시간|)
         반절 가격b = 시작하는 시간 - 야간기준시 ? > 0 (일반가+야간가+공휴일가)*(야간기준시 - 시작하는 시간) : (일반가+공휴일가)*(|야간가 - 시작하는 시간|)
         최종 가격 = a+
      else
         반절 가격a = 끝나는 시간 - 야간기준시 ? > 0 (일반가+야간가)*(야간가 - 끝나는 시간) : (일반가)*(|야간가 - 끝나는 시간|)
         반절 가격b = 시작하는 시간 - 야간기준시 ? > 0 (일반가+야간가)*(야간가 - 시작하는 시간) : (일반가)*(|야간가 - 시작하는 시간|)
         최종 가격 = a+b
    */
   function getPrice(fixedDate, endTime, startTime) {
      
      var nightTime  = moment("18:00", "HH:mm");
      
      var mEndTime   = moment(endTime, "HH:mm");
      var mStartTime = moment(startTime, "HH:mm");

      console.log((moment.duration(nightTime.diff(mEndTime)).asHours()));
      var price       = 0;
      var dayPrice    = Number(fixedDate.stadiumPrice.dayPrice);
      var nightPrice  = Number(fixedDate.stadiumPrice.nightPrice);
      var holPrice    = Number(fixedDate.stadiumPrice.holPrice);

      console.log(moment.duration(nightTime.diff(mEndTime)).asHours());
      var afterPrice  = 0;
      var beforePrice = 0;
      var day = new Date(clickDay).getDay()
      if(day == 0 || day == 6) {
         afterPrice  = nightTime.isAfter(mEndTime)   ? (dayPrice + nightPrice + holPrice)  : (dayPrice + holPrice));
         beforePrice = nightTime.isAfter(mStartTime) ? (dayPrice + nightPrice + holPrice)  : (dayPrice + holPrice));
      } else {
         afterPrice  = nightTime.isAfter(mEndTime)   ? (dayPrice + nightPrice)  : dayPrice));
         beforePrice = nightTime.isAfter(mStartTime) ? (dayPrice + nightPrice)  : dayPrice));
      }
    
      var sumPrice = afterPrice + beforePrice;
      
      return sumPrice
   }
};