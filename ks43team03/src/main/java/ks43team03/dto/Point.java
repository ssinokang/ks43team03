package ks43team03.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Point {

	//user_get_point
	private String userGetPointCd;
	private String pointApplyDateCd;
	private String userId;
	private String pointGetCd;
	private String getPoint;
	private String PointEventDate;
	private String getPointDate;
	private String PointEndDate;
	private String getState;
	
	//total_point
	private String applyPointGroupCd;
	private int totalPoint;
	private Date pointUpdateDate;
	
	//apply_point
	private String applyPointCd;
	private String eventDetail;
	private int applyPointAmount;
	private Date applyDate;
	private String appyPointGroupCd;
	
}
