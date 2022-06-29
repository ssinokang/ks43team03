package ks43team03.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public String getBoardList(Model model
							,@RequestParam(name = "currentPage", required = false, defaultValue = "1") int currentPage) {
		System.out.println("------------------------게시글 전체 목록 조회-----------------------------");
		
		Map<String, Object> resultMap =  boardService.getBoardList(currentPage);
		
		log.info("resultMap 받기 : {}", resultMap);
		log.info("resultMap.get(\"boardList\") : {}",resultMap.get("boardList"));

		model.addAttribute("title", 				"게시판");
		model.addAttribute("resultMap", 			resultMap);
		model.addAttribute("currentPage", 			currentPage);
		model.addAttribute("boardList",				resultMap.get("boardList"));
		model.addAttribute("lastPage", 				resultMap.get("lastPage"));
		model.addAttribute("startPageNum", 			resultMap.get("startPageNum"));
		model.addAttribute("endPageNum", 			resultMap.get("endPageNum"));
		
		System.out.println("------------------------게시글 전체 목록 조회 끝-----------------------------");
		
		return "board/boardList";
	}
	
	/* 게시글 코드로 상세 조회 */
	/* 게시물 답글 조회 */
	@GetMapping("/boardDetail")
	public String getBoardDetail(Model model
								,@RequestParam(value = "boardPostCd", required = false) String boardPostCd
								,@RequestParam(name = "currentPage", required = false, defaultValue = "1") int currentPage) {
		System.out.println("------------------------게시글 상세조회-----------------------------");
		Board board = boardService.getBoardDetail(boardPostCd);
		
		List<BoardComment> boardCommentList = boardService.getBoardCommentList(boardPostCd);
		boardService.boardViewUpdate(boardPostCd);
		
		model.addAttribute("title", 			"게시글 상세보기");
		model.addAttribute("board", 			board);
		model.addAttribute("boardCommentList",	boardCommentList);
		
		log.info("boardPostcd : {}", boardPostCd);
		log.info("boardDetail : {}", board);
		log.info("boardCommentList : {}", boardCommentList);
		
		
		Map<String, Object> resultMap =  boardService.getBoardList(currentPage);
		model.addAttribute("resultMap", 			resultMap);
		model.addAttribute("currentPage", 			currentPage);
		model.addAttribute("boardList",				resultMap.get("boardList"));
		model.addAttribute("lastPage", 				resultMap.get("lastPage"));
		model.addAttribute("startPageNum", 			resultMap.get("startPageNum"));
		model.addAttribute("endPageNum", 			resultMap.get("endPageNum"));
		
		
		System.out.println("------------------------게시글 상세조회 끝-----------------------------");
		
		return "board/boardDetail";
	}
	
	/* 게시글 등록 처리 */
	@PostMapping("/addBoard")
	public String addBoard(Board board) {
		System.out.println("------------------------게시글 등록 처리-----------------------------");
		
		log.info("회원이 입력한 게시글 내용 : {}", board); 
		
		int result = boardService.addBoard(board);
		
		log.info("result : {}", result);
		System.out.println("------------------------게시글 등록 처리 끝-----------------------------");
		
		return "redirect:/board/boardList";
	}
	
	/* 게시글 등록 페이지 */
	@GetMapping("/addBoard")
	public String addBoard(Board board, BoardCtgCd boardSubCtgCd, Model model) {
		System.out.println("------------------------게시글 등록 페이지-----------------------------");
		
		List<BoardCtgCd> boardCtgCdList = boardService.getBoardSubCtgCd(boardSubCtgCd);
		
		model.addAttribute("title", "게시글 등록");
		model.addAttribute("boardCtgCdList", boardCtgCdList);
		
		log.info("boardCtgCdList : {}", boardCtgCdList);
		System.out.println("------------------------게시글 등록 페이지 끝-----------------------------");
		
		return "board/addBoard";
	}
	
	/* 게시글수정 처리 */
	@PostMapping("/modifyBoard")
	public String modifyBoard(Board board, Model model) {
		System.out.println("------------------------게시글 수정 처리------------------------");
		
		int result = boardService.modifyBoard(board);
		
		log.info("result : {}", result);
		System.out.println("------------------------게시글 수정 처리 끝------------------------");
		
		return "redirect:/board/boardList";
	}
	
	/* 게시글수정 페이지 */
	@GetMapping("/modifyBoard")
	public String modifyBoard(Model model, BoardCtgCd boardSubCtgCd,
							@RequestParam(name = "boardPostCd", required = false) String boardPostCd) {
		System.out.println("---------------------게시글 수정 페이지-----------------------");
		
		log.info("받아온 boardPostCd : {}", boardPostCd);
		
		Board board = boardService.getBoardDetail(boardPostCd);
		
		List<BoardCtgCd> boardCtgCdList = boardService.getBoardSubCtgCd(boardSubCtgCd);
		
		model.addAttribute("title", "게시글 수정");
		model.addAttribute("board", board);
		model.addAttribute("boardCtgCdList", boardCtgCdList);

		System.out.println("---------------------게시글 수정 페이지 끝-----------------------");

		return "board/modifyBoard";
	}
	
	/* 게시물 답글 등록 */
	@PostMapping(value = "/addBoardComment", produces = "application/json")
	public String addBoardComment(BoardComment boardComment, Board board, RedirectAttributes attr
								,@RequestParam(name = "boardPostCd", required = false) String boardPostCd) {
		System.out.println("------------------------게시글 답글 등록-----------------------------");
		
		attr.addAttribute("boardPostCd", boardComment.getBoardPostCd());
		attr.addAttribute("boardPostContentDetail", boardComment.getBoardPostContentDetail());
		
		log.info("답글 : {}", boardComment.getBoardPostContentDetail());
		
		boardService.countComment(boardPostCd);
		boardService.addBoardComment(boardComment);
		System.out.println("------------------------게시글 답글 등록 끝-----------------------------");
		
		return "redirect:/board/boardDetail";
	}
	
	/* 게시글 답글 삭제 */
	@GetMapping("/removeComment")
	public String deleteComment(Model model, BoardComment boardComment, RedirectAttributes attr,
								@RequestParam(name = "boardPostCommentCd", required = false) String boardPostCommentCd
								,@RequestParam(name = "boardPostCd", required = false) String boardPostCd) {
		System.out.println("------------------------게시글 답글 삭제-----------------------------");
		
		log.info("삭제할 답글 코드 : {}", boardPostCommentCd);
		int result = boardService.removeComment(boardPostCommentCd);
		
		log.info("result : {}", result);
		log.info("boardPostCd : {}", boardPostCd);
		
		attr.addAttribute("boardPostCd", boardPostCd);
		
		boardService.countCommentMinus(boardPostCd);
		System.out.println("------------------------게시글 답글 삭제 끝-----------------------------");

		return "redirect:/board/boardDetail";
	}
	
	/* 게시글 삭제 */
	@GetMapping("/removeBoard")
	public String removeBoard(Board board) {
		System.out.println("------------------------게시글 삭제-----------------------------");
		String boardPostCd = board.getBoardPostCd();

		int resultBC = boardService.removeCommentByPostCd(boardPostCd);
		int resultB = boardService.removeBoard(boardPostCd);
		
		System.out.println("------------------------게시글 삭제 끝-----------------------------");

		return "redirect:/board/boardList";
	}
	
}
