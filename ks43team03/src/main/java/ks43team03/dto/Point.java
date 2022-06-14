package ks43team03.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Point {

	//포인트적립코드
	private String userGetPointCd;
	//적립날짜정책코드
	private String pointApplyDateCd;
	//회원아이디
	private String userId;
	//포인트적립분류코드
	private String pointGetCd;
	//적립포인트
	private String getPoint;
	//적립이벤트발생날짜
	private String PointEventDate;
	//적립예정날짜
	private String getPointDate;
	//만료예정일
	private String PointEndDate;
	//적립상태
	private String getState;
	
}
