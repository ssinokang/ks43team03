package ks43team03.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Board {

	private String  boardPostCd;
	private String  boardCtgCd;
	private String  boardSubCtgCd;
	private String  userId;
	private String  boardPostTitle;
	private String  boardPostContent;
	private String  boardPostFile;
	private String  boardPostDate;
	private String  boardPostCommentNum;
	private int  boardPostViews;
	private int  boardPostNum;
	private char  boardPostState;
	
}
