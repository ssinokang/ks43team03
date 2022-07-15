package ks43team03.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LessonReservatioin {
	String reservationCd;
	String reservationId;
	String reservationDate;
	String reservationStartTime;
	String reservationEndTime;
	String reservationCancel;
}
