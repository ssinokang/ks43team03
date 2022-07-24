
/* 레슨 예약 하기 */
function addLesson(fixedDate) {
	var reservationStartTime = $('input[name="reservationStartTime"]');
	var reservationEndTime   = $('input[name="reservationEndTime"]');
	var clickDay = '';
	var orderChe;
		var price 	    = 0;
		var dayPrice    = Number(fixedDate.stadiumPrice.dayPrice);
		var nightPrice  = Number(fixedDate.stadiumPrice.nightPrice);
		var holPrice    = Number(fixedDate.stadiumPrice.holPrice);

		console.log(moment.duration(nightTime.diff(mEndTime)).asHours());
		var afterPrice  = 0;
		var beforePrice = 0;
		var day = new Date(clickDay).getDay()
		if(day == 0 || day == 6) {
			afterPrice  = nightTime.isAfter(mEndTime)   ? (dayPrice + nightPrice + holPrice)*Math.abs((moment.duration(nightTime.diff(mEndTime)).asHours()))  : (dayPrice + holPrice)*Math.abs((moment.duration(nightTime.diff(mEndTime)).asHours()));
			beforePrice = nightTime.isAfter(mStartTime) ? (dayPrice + nightPrice + holPrice)*Math.abs((moment.duration(mStartTime.diff(mStartTime)).asHours()))   : (dayPrice + holPrice)*(moment.duration(mStartTime.diff(mStartTime)).asHours());
		} else {
			afterPrice  = nightTime.isAfter(mEndTime)   ? (dayPrice + nightPrice)*Math.abs((moment.duration(nightTime.diff(mEndTime)).asHours()))     : (dayPrice)*Math.abs((moment.duration(nightTime.diff(mEndTime)).asHours()));
			beforePrice = nightTime.isAfter(mStartTime) ? (dayPrice + nightPrice)*Math.abs((moment.duration(mStartTime.diff(startTime)).asHours()))   : (dayPrice)*Math.abs((moment.duration(nightTime.diff(mStartTime)).asHours()));
		}
		
		
		price = afterPrice + beforePrice;
		return price
	}
};