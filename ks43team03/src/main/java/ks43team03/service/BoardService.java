package ks43team03.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ks43team03.dto.Board;
import ks43team03.dto.BoardComment;
import ks43team03.mapper.BoardMapper;

@Service
public class BoardService {

	@Autowired
	BoardMapper boardMapper;
	
	/* 게시글목록조회 */
	public List<Board> getBoardList(Map<String, Object> paramMap) {
		List<Board> boardList = boardMapper.getBoardList(paramMap);
		return boardList;
	}
	
	/* 게시글상세보기 */
	public Board getBoardDetail(String boardPostCd) {
		Board board = boardMapper.getBoardDetail(boardPostCd);
		return board;
	}
	
	/* 게시글 등록 */
	public void addBoard(Board board) {
		boardMapper.addBoard(board);
	}
	
	/* 게시글 수정 */
	public int modifyBoard(Board board) {
		return boardMapper.modifyBoard(board);
	}
	
	/* 게시글 삭제 */
	public int removeBoard(String boardPostCd) {
		int result = boardMapper.removeBoard(boardPostCd);
		result += boardMapper.removeBoard(boardPostCd);
		return result;
	}
	
	/* 게시판조회수증가 */
	public int boardViewUpdate(String boardPostCd) {
		return boardMapper.boardViewUpdate(boardPostCd);
	}
	
	/* 답글등록 */
	public void addBoardComment(BoardComment boardComment) {
		boardMapper.addBoardComment(boardComment);
	}
	
	/* 답글조회 */
	public List<BoardComment> getBoardCommentList(String boardPostCd){
		List<BoardComment> boardCommentList = boardMapper.getBoardCommentList(boardPostCd);
		return boardCommentList;
	}
	
	/* 답글삭제 */
	public int removeComment(String boardCommentCode) {
		int result = boardMapper.removeComment(boardCommentCode);
		result += boardMapper.removeComment(boardCommentCode);
		return result;
	}
}
