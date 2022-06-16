package ks43team03.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ks43team03.dto.Board;
import ks43team03.dto.BoardComment;
import ks43team03.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {

	private final BoardService boardService;

	private static final Logger log = LoggerFactory.getLogger(BoardController.class);

	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	/* 게시글 전체 목록 조회 */
	@GetMapping("/boardList")
	public String getBoardList(Model model) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<Board> boardList = boardService.getBoardList(paramMap);
		log.info("boardList 받기 : {}",boardList);
		model.addAttribute("tltle", "게시글 전체 목록 조회");
		model.addAttribute("boardList",boardList);
		return "board/boardList";
	}
	
	/* 게시글 코드로 상세 조회 */
	@GetMapping("/boardDetail")
	public String getBoardDetail(Model model,
								@RequestParam(value = "boardPostCd",required = false) String boardPostCd) {
		Board board = boardService.getBoardDetail(boardPostCd);
		List<BoardComment> boardCommentList = boardService.getBoardCommentList(boardPostCd);
		model.addAttribute("title", "게시글 상세보기");
		model.addAttribute("board", board);
		model.addAttribute(boardCommentList);
		log.info("boardDetail : ", board);
		return "board/boardDetail";
	}
	
	/* 게시글 등록  post */
	@PostMapping("/addBoard") 
	public String addBoard(Board board) {
		boardService.addBoard(board); 
		log.info("게시글 등록 post", board); 
		return"redirect:/board/boardList"; 
	}
	
	/* 게시글 등록  get */
	@GetMapping("/addBoard") 
	public String addBoard(Model model) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		List<Board> boardList = boardService.getBoardList(paramMap);
		model.addAttribute("title", "게시글 등록");
		model.addAttribute("boardList",boardList);
		return "board/addBoard";
	}
	
	/* 게시글수정 post */
	@PostMapping("/modifyBoard")
	public String modifyBoard(Board board) {
		boardService.modifyBoard(board);
		log.info("게시글 수정 post");
		return "redirect:/board/boardList";
	}
	
	/* 게시글수정 get */
	@GetMapping("/modifyBoard")
	public String modifyBoard(Model model,
							@RequestParam(value = "boardPostCd", required = false) String boardPostCd) {
		Board board = boardService.getBoardDetail(boardPostCd);
		model.addAttribute("title", "게시글 수정 페이지");
		model.addAttribute("board",board);
		log.info("게시글 수정 get : ");
		return "board/modifyBoard";
	}
	
	/* 게시물 답글 등록 post */
	@PostMapping("/boardDetail")
	public String addBoardComment(BoardComment boardComment, RedirectAttributes attr) {
		boardService.addBoardComment(boardComment);
		log.info("게시글 답글 등록", boardComment);
		attr.addAttribute("boardPostCd", boardComment.getBoardPostCd());
		return "redirect:/board/boardDetail";
	}
	
	/* 게시글 답글 삭제 */
	@DeleteMapping("/removeComment") 
	public String deleteComment(Model model,BoardComment boardComment, RedirectAttributes attr,
			 					@RequestParam(name = "boardPostCommentCd", required = false) String boardPostCommentCd) { 
		log.info("게시글 답글 삭제",boardPostCommentCd);
		boardService.removeComment(boardPostCommentCd); 
		attr.addAttribute("boardPostCd", boardComment.getBoardPostCd());
		return "redirect:/board/boardList"; 
	}
	
	/* 게시글 삭제 */
	@DeleteMapping("/removeBoard") 
	public String removeBoard(Board board) {
		String boardPostCd = board.getBoardPostCd();
		boardService.removeComment(boardPostCd);
		boardService.removeBoard(boardPostCd);
		log.info("게시글 삭제 : ",boardService.removeBoard(boardPostCd));
		return "redirect:/board/boardList";
	}
	
	/*
	 * 답글등록
	 * 
	 * @PostMapping("/boardDetail") public String addBoardComment(BoardComment
	 * boardComment, RedirectAttributes attr) {
	 * boardService.addBoardComment(boardComment); log.info("게시글 답글 등록",
	 * boardComment); attr.addAttribute("boardPostCd",
	 * boardComment.getBoardPostCd()); return "redirect:/board/boardDetail"; }
	 * 
	 * 게시글삭제
	 * 
	 * 
	 * 
	 * 답글삭제
	 * 
	 * 
	 * 
	 * 
	 */

}
