package hello.exception.api;

import hello.exception.exception.BadRequestException;
import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequestMapping("api/")
public class ApiExceptionController {
/*
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegalExHandle(IllegalArgumentException e) {
        log.error("[exceptionHandle] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandle(UserException e) {
        log.error("[exceptionHandle] ex", e);
        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
        return new 1ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandle(Exception e) {
        log.error("[exceptionHandle] ex", e);
        return new ErrorResult("EX", "내부 오류");
    }
*/
    @GetMapping("members/{id}")
    public MemberDto getMember(@PathVariable("id") String id){

        //아래 예외가 발생하면 예외내용을 json으로 응답 해줘야한다.
        //지금 현재 만들어둔 html 오류페이지가 전달되는 상황! 이렇게 되면 안돼!

        if(id.equals("ex")){
            //@RequestMapping(value = "/error-page/500", produces = MediaType.APPLICATION_JSON_VALUE)
            //http://127.0.0.1:9080/api/members/ex
            throw new RuntimeException("잘못된 사용자");
        }

        if (id.equals("bad")) {
            throw new IllegalArgumentException("잘못된 입력 값");
        }

        if (id.equals("user-ex")) {
            throw new UserException("사용자 오류");
        }

        //예외가 아닐 시
        return new MemberDto(id, "hello"+id);
    }

    @GetMapping("response-status-ex1")
    public String responseStatusEx1(){

        //내가 만든 리졸버
        throw new BadRequestException();
    }

    @GetMapping("response-status-ex2")
    public String responseStatusEx2(){

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "error.bad", new IllegalArgumentException());
    }

    @Data
    @AllArgsConstructor
    static class MemberDto{
        private String memberId;
        private String name;
    }
}
