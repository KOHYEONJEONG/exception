package hello.exception.exception.advice;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @RestControllerAdvice  = @ControllerAdvice + @ResponseBody
 * */
@Slf4j
@RestControllerAdvice(basePackages = "hello.exception.api") //basePackages 속성은 이 패키지에서 나온 에러만 처리하겠다!
public class ExControllerAdvice_API {

    //여기는 api에 대한 예외 처리

    //순서
    // ㄴ 컨트롤러 예외 터짐 ->  exceptionResolver (@ExceptionHandler) 찾아서 호출! -> 정상 리턴 -> 그럼 200 즉 정상 로직 이기때문에
    // ㄴ 우리가 기대하는건 상태코드까지 제대로 반영해야겠지?
    // 그래서 @ResponseStatus(HttpStatus.BAD_REQUEST) 를 붙여준다.

    /**  @ResponseStatus(HttpStatus.BAD_REQUEST)을 해줌으로써 응답 상태코드를 지정해서 보낼 수 있다.(안 그러면 200으로 상태코드가 나간다)
     * ㄴ 400대는 클라리언트가 잘못한거
     * ㄴ 500대는 서버가 잘못한 거
     * */
    @ResponseStatus(HttpStatus.BAD_REQUEST) //400
    @ExceptionHandler(IllegalArgumentException.class) //자식오류
    public ErrorResult illegalExHandle(IllegalArgumentException e) {

        /**
         * ErrorResult 내가 만든 VO,
         * 에러에 대한 코드와 메시지 보내기 위한 간단한 필드가 있다.*/

        log.error("[exceptionHandle] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }
    @ExceptionHandler
    public ResponseEntity<ErrorResult> userExHandle(UserException e) {
        log.error("[exceptionHandle] ex", e);
        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)//500
    @ExceptionHandler
    public ErrorResult exHandle(Exception e) {
        log.error("[exceptionHandle] ex", e);
        return new ErrorResult("EX", "내부 오류");
    }

}
