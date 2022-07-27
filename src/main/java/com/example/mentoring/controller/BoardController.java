package com.example.mentoring.controller;


import com.example.mentoring.entity.Board;
import com.example.mentoring.response.Response;
import com.example.mentoring.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class BoardController {
    private final BoardService boardService;

    //전체 게시물 조회
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/boards")
    public Response getBoards(){
        return Response.success(boardService.getBoards());
    }

    //단건 게시글 조회
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/boards/{id}")
    public Response getBoard(@PathVariable ("id") Long id){
        return Response.success(boardService.getBoard(id));
    }
    // HttpStatus.OK == 200, HttpStatus.CREATED == 201
    // POST 게시글 작성
    // 매개변수로 게시글이 들어온다 -> 들어온 게시글을 데이터베이스에 저장해준다.
    // @RequestBody 를 붙이는 이유 -> JSON 타입으로 데이터가 들어오는데, 이걸 자바에서 인식할 수 있게, 자바 클래스로 매핑 시켜준다.
    // REST API -> JSON 형식으로 데이터를 받아야한다.
    // @RequestBody Entity entity -> JSON 형식인 데이터를, 자바 타입으로 바꿔준다.
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/boards")
    public Response save(@RequestBody Board board){
        return  Response.success(boardService.save(board));
    }

    //게시글 수정
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/boards/{id}")
    public Response editBoard(@PathVariable ("id") Long id, Board updateBoard){
        return  Response.success(boardService.editBoard(id,updateBoard));
    }

    //게시글 삭제
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("boards/{id}")
    public Response deleteBoard(@PathVariable ("id") Long id){
        boardService.deleteBoard(id);
        return  Response.success("삭제 완료");
    }
}