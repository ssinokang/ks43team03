package ks43team03.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Facility {
	private String facilityCd;
	private String mainCtgCd;
	private String townCd;
	private String facilityUseCd;
	private String userId;
	private String facilityNm;
	private String facilityAddr;
	private String facilityTell;
	private String facilityPhoto;
	private String businessNum;
	private String facilityRegDate;
	

	private FacilityUse facilityUse;
	private MainCtg mainCtg;
	private AreaCityTown areaCityTown;
	

}
