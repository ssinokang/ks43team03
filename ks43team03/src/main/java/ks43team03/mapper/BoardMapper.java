package ks43team03.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks43team03.dto.Board;
import ks43team03.dto.BoardComment;

@Mapper
public interface BoardMapper {

	//게시글 전체 목록 조회
	public List<Board> getBoardList(Map<String, Object> paramMap);
	
	//게시글 코드로 상세 조회 
	public Board getBoardDetail();
	
	// 게시글 등록
	public int addBoard(Board board);
	
	
	
	
	
	
	/*
	 * // 게시글 수정 public int modifyBoard(Board board);
	 * 
	 * // 게시글 삭제 public int removeBoard(String boardPostCd);
	 * 
	 * // 게시글 조회수 업데이트 public int boardViewUpdate(String boardPostCd);
	 * 
	 * // 게시글 답글 등록 public void addBoardComment(BoardComment boardComment);
	 * 
	 * // 게시글 답글 조회 public List<BoardComment> getBoardCommentList(String
	 * boardPostCd);
	 * 
	 * // 게시글 답글 삭제 public int removeComment(String boardCommentCode);
	 */
	
}
