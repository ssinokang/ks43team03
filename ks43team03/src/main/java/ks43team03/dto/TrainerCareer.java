package ks43team03.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TrainerCareer {
	private String trainerCareerCd;
	private String trainerCd;
	private String trainerCareerTerm;
	private String trainerCareerCenter;
	private String trainerCareerPosition;
	private String trainerCareerWork;
	private String trainerCareerFile;
	private String trainerCareerRegDate;
	
	private List<TrainerCareer> trainerCareerList;
}
