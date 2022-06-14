package ks43team03.dto;

public class MainCtg {
	private String mainCtgCd;
	private String mainCtgNm;
	public String getMainCtgCd() {
		return mainCtgCd;
	}
	public void setMainCtgCd(String mainCtgCd) {
		this.mainCtgCd = mainCtgCd;
	}
	public String getMainCtgNm() {
		return mainCtgNm;
	}
	public void setMainCtgNm(String mainCtgNm) {
		this.mainCtgNm = mainCtgNm;
	}
	@Override
	public String toString() {
		return "MainCtg [mainCtgCd=" + mainCtgCd + ", mainCtgNm=" + mainCtgNm + "]";
	}
	
	
}
