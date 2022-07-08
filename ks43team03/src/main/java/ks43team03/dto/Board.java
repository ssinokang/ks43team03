package ks43team03.dto;

import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Board {

	private String  boardPostCd;
	private String  boardCtgCd;
	private String  userId;
	private String  boardPostTitle;
	private String  boardPostContent;
	private String  boardPostDate;
	private String  boardPostCommentNum;
	private int  	boardPostViews;
	private char  	boardPostState;
	
	private BoardComment 				boardComment;
	private BoardCtgCd 					boardSubCtgCd;
	private List<Map<String, String>> 	relFileWithBoard;
	private List<TFile> 				tFile;
}
