
/*boardList*/
$(function(){
	var $boardTr = $('#boardBody tr');
					console.log($boardTr);
	
					$boardTr.click(function(){
						console.log(this);
	  
						var boardPostCd = $(this).find('input').val();
		console.log(boardPostCd);
			
		$(location).attr('href','/board/boardDetail?boardPostCd='+boardPostCd);
	});
});


/*addBoard*/

$(function(){
	function validationCheck(data){
		if(typeof data == 'undefined' || data == '' || data == null){
			return false;
		}
		return true;
	}
	
	$('#addBoardBtn').click(function(){
		var isSubmit = true;
		var inputObj = $('#addBoardForm').find('input');
		
		$.each(inputObj, function(){
			var inputData = $(this).val();
			var result = validationCheck(inputData);
			
			// 사용자가 입력하지 않은 경우
			if(!result){
				alert('필수 항목입니다. 입력해주세요');
				$(this).focus();
				isSubmit = false;
				return false;
			}
		});
		if(isSubmit){
			if($("#boardPostTitle").val() == ""){
			    alert("제목을 입력해주세요.");
			    $("#boardPostTitle").focus();
			    isSubmit = false;
			    return false;
			}
		}
		console.log('유효여부 폼전송할건지?' + isSubmit )
		
		if(isSubmit) $('#addBoardForm').submit();
	});
});

/*boardDetail*/



/*modifyBoard*/



