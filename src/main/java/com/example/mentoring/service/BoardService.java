package com.example.mentoring.service;


import com.example.mentoring.entity.Board;
import com.example.mentoring.exception.BoardNotFoundException;
import com.example.mentoring.exception.WriterNotFoundException;
import com.example.mentoring.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public List<Board> getBoards() {
        return boardRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Board getBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        return board;
    }
    @Transactional
    public Board save(Board board) {
        // 작성자가 없을 때 예외 터뜨려보기
        if(board.getWriter().equals(" ")) {
            throw new WriterNotFoundException();
        }
        return boardRepository.save(board);
    }
    @Transactional
    // Transactional 을 붙이면 더티체킹이 일어나서, 저장하지 않아도 메서드가 성공적으로 끝나면 저장이 된다.
    public Board editBoard(Long id, Board update){
        //기존 게시물을 꺼내온다.
        Board board = boardRepository.findById(id).get();
        //기존 게시물에, updateBoard 정보를 덮어씌어준다.
        board.setTitle(update.getTitle());
        board.setContent(update.getContent());
        return  board;
    }
    @Transactional
    public void  deleteBoard(Long id){
        boardRepository.deleteById(id);
    }
}