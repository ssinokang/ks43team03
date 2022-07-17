
/* 레슨 예약 하기 */
$(function() {
	var lessonStart = $('input[name="lesson-start"]');
	var lessonEnd   = $('input[name="lesson-end"]');
	$(document).on('click','.lessonTime.possible', )
	$(document).on('focus', lessonStart, lessonStartFocus());
	
	function startVal() {
		
		if(lessonStart.val() == '' || lessonStart.val() == null) {
			lessonStart.val($(this).val());
		} else {
			lessonEnd.val($(this).val());
		}
	}
	function lessonStartFocus() {
		$(document).on('click','.lessonTime.possible', function() {
			lessonStart
		})
	}
})