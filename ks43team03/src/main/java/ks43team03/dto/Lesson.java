package ks43team03.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Lesson {
	private String lessonCd;
	private String lessonDivision;
	private String facilityGoodsCd;
	private String facilityCd;
	private String goodsCtgCd;
	private String sportsCd;
	private String userId;
	private String sportsName;
	private String lessonName;
	private String lessonDetail;
	private String lessonTotalMember;
	private String lessonMember;
	private String lessonStartDate;
	private String lessonEndDate;
	private int lessonPrice;
	private String lessonRegDate;
	private String lessonState;
	private String fileCd;
	
	private Sports sports;
}
