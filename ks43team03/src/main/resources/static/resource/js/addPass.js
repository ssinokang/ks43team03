var $stadium = $('#stadium');
var $pass = $('#pass');
var $lesson = $('#lesson');
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
			}).done(function (data) {

				console.log(data.goodsCtgCd);
				$('#gscode').val(data.facilityGoodsCd);
				if (data.goodsCtgCd == 'lesson') {
					$lesson.css('display', 'block');
				}
				if (data.goodsCtg == 'pass') {
					$pass.css('display', 'block');
				}
				if (data.goodsCtg == 'stadium') {
					$stadium.css('display', 'block');
				}


			}).fail(function (error) {
				alert(JSON.stringify(error));
			});
		}
		console.log(data);

	}
}

main.init();