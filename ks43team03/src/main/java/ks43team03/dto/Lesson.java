package ks43team03.dto;

import java.io.File;

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
	private String sportsNm;
	private String lessonNm;
	private File lessonPicture;
	private String lessonDetail;
	private String lessonTotalMember;
	private String lessonMember;
	private String lessonStartDate;
	private String lessonEndDate;
	private String lessonPrice;
	private String lessonRegDate;
	private String lessonState;
	
	private Sports sports;
}
