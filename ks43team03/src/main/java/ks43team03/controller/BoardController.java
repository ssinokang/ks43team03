package ks43team03.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ks43team03.dto.Board;
import ks43team03.dto.BoardComment;
import ks43team03.dto.BoardCtgCd;
import ks43team03.dto.User;
import ks43team03.service.BoardService;
import ks43team03.service.UserService;

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

	/* 게시글 등록 post */
	@PostMapping("/addBoard")
	public String addBoard(Board board) {

		boardService.addBoard(board);

		log.info("게시글 등록 post", board);

		return "redirect:/board/boardList";
	}

	/* 게시글 등록 get */
	@GetMapping("/addBoard")
	public String addBoard(Model model, BoardCtgCd boardCtgCd) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<Board> boardList = boardService.getBoardList(paramMap);
		List<BoardCtgCd> boardCtgCdList = boardService.getBoardCtgCdList(boardCtgCd);
		
		log.info("boardCtgCd : {}", boardCtgCd);
		log.info("boardList : {}", boardList);
		log.info("boardCtgCdList : {}", boardCtgCdList);

		model.addAttribute("title", "게시글 등록");
		model.addAttribute("boardList", boardList);
		model.addAttribute("boardCtgCdList", boardCtgCdList);

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
	@PostMapping(value = "/boardDetail", produces = "application/json")
	@ResponseBody
	public String addBoardComment(@RequestBody BoardComment boardComment, Model model, HttpSession session) {
		
		String sessionId = (String)session.getAttribute("SID");
		
		boardComment.setUserId(sessionId);
//		
//		log.info("회원 sessionId : {}", sessionId);

		log.info("게시글 답글 등록 : {}", boardComment.getBoardPostContentDetail());

		// attr.addAttribute("boardPostCd", boardComment.getBoardPostCd());
		return null;
		// return "redirect:/board/boardDetail";
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
