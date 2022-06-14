package ks43team03.dto;


public class FacilityUse {
	
	private String facilityUseCd;
	private String facilityUseNm;
	public String getFacilityUseCd() {
		return facilityUseCd;
	}
	public void setFacilityUseCd(String facilityUseCd) {
		this.facilityUseCd = facilityUseCd;
	}
	public String getFacilityUseNm() {
		return facilityUseNm;
	}
	public void setFacilityUseNm(String facilityUseNm) {
		this.facilityUseNm = facilityUseNm;
	}
	@Override
	public String toString() {
		return "FacilityUse [facilityUseCd=" + facilityUseCd + ", facilityUseNm=" + facilityUseNm + "]";
	}
	
	
	
}
