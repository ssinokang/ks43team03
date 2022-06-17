package ks43team03.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TrainerProfile {
	private String trainerCd;
	private String userId;
	private String trainerNickname;
	private String trainerPicture;
	private String trainerRegDate;
	
	private User user;
}
