package ks43team03.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ks43team03.dto.Board;
import ks43team03.dto.BoardComment;
import ks43team03.dto.BoardCtgCd;
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

		log.info("boardList 받기 : {}", boardList);

		model.addAttribute("tltle", "게시글 목록 조회");
		model.addAttribute("boardList", boardList);

		return "board/boardList";
	}

	/* 게시글 코드로 상세 조회 */
	/* 게시물 답글 조회 */
	@GetMapping("/boardDetail")
	public String getBoardDetail(Model model,
								@RequestParam(value = "boardPostCd", required = false) String boardPostCd) {
		Board board = boardService.getBoardDetail(boardPostCd);
		
		List<BoardComment> boardCommentList = boardService.getBoardCommentList(boardPostCd);
		
		boardService.boardViewUpdate(boardPostCd);

		model.addAttribute("title", "게시글 상세보기");
		model.addAttribute("board", board);
		model.addAttribute("boardCommentList", boardCommentList);
		
		log.info("boardPostcd : {}", boardPostCd);
		log.info("boardDetail : {}", board);
		log.info("boardCommentList : {}", boardCommentList);
		
		return "board/boardDetail";
	}

	/* 게시글 등록 처리 */
	@PostMapping("/addBoard")
	public String addBoard(Board board
							,@RequestParam(name="boardPostCd", required = false) String boardPostCd
							,HttpServletRequest request) {
		
		boardService.addBoard(board);
		
		return "redirect:/";
	}
	
	/* 게시글 등록 페이지 */
	@GetMapping("/addBoard")
	public String addBoard(Model model, HttpSession session, Board board,BoardCtgCd boardSubCtgCd) {
		
		String sessionId = (String)session.getAttribute("SID");
		board.setUserId(sessionId);
		
		List<BoardCtgCd> boardCtgCdList = boardService.getBoardSubCtgCd(boardSubCtgCd);
		
		log.info(" !!!!!!!!!!" + boardSubCtgCd);
		model.addAttribute("title", "게시글 등록");
		model.addAttribute("getBoardPostCd", board.getBoardPostCd());
		model.addAttribute("getBoardCtgCd", board.getBoardCtgCd());
		model.addAttribute("getBoardPostTitle", board.getBoardPostTitle());
		model.addAttribute("getBoardPostContent", board.getBoardPostContent());
		model.addAttribute("boardCtgCdList", boardCtgCdList);
		log.info("회원이 입력한 게시글 내용 : {}", board);
		
		log.info("sessionId : {}",sessionId);
		log.info("boardCtgCd : {}", board.getBoardCtgCd());
		log.info("boardPostTitle : {}", board.getBoardPostTitle());
		log.info("boardPostContent : {}", board.getBoardPostContent());
		
		/*
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<Board> boardList = boardService.getBoardList(paramMap);
		List<BoardCtgCd> boardCtgCdList = boardService.getBoardCtgCdList(boardCtgCd);
		
		log.info("boardCtgCd : {}", boardCtgCd);
		log.info("boardCtgCdList : {}", boardCtgCdList);
		
		model.addAttribute("title", "게시글 등록");
		model.addAttribute("boardCtgCd", boardCtgCd);
		model.addAttribute("boardCtgCdList", boardCtgCdList);
		
		boardService.addBoard(board);
		*/
		
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
	public String modifyBoard(Model model, @RequestParam(value = "boardPostCd", required = false) String boardPostCd) {
		Board board = boardService.getBoardDetail(boardPostCd);

		model.addAttribute("title", "게시글 수정");
		model.addAttribute("board", board);

		log.info("게시글 수정 get : ");

		return "board/modifyBoard";
	}

	/* 게시물 답글 등록 post */
	@PostMapping(value = "/addBoardComment", produces = "application/json")
	public String addBoardComment(BoardComment boardComment, Board board, HttpSession session, RedirectAttributes attr) {
		
		String sessionId = (String)session.getAttribute("SID");
		log.info("회원 sessionId : {}", sessionId);
		log.info(boardComment.getBoardCtgCd());
		log.info("boardComment : {}", boardComment);
		
		boardComment.setUserId(sessionId);
		
		
		attr.addAttribute("boardPostCd", boardComment.getBoardPostCd());
		attr.addAttribute("boardPostContentDetail", boardComment.getBoardPostContentDetail());
		log.info("게시글 답글 등록 : {}", boardComment.getBoardPostContentDetail());
		
		boardService.addBoardComment(boardComment);
		
		return "redirect:/board/boardDetail";
	}

	/* 게시글 답글 삭제 */
	@DeleteMapping("/removeComment")
	public String deleteComment(Model model, BoardComment boardComment, RedirectAttributes attr,
			@RequestParam(name = "boardPostCommentCd", required = false) String boardPostCommentCd) {
		log.info("게시글 답글 삭제", boardPostCommentCd);

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

		log.info("게시글 삭제 : ", boardService.removeBoard(boardPostCd));

		return "redirect:/board/boardList";
	}

}
