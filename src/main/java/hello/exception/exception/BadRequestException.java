package hello.exception.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

//원래는 500에러 출력되는데, 400으로 출력되게 만들어주는 애너테이션
//reason을 MessageSource에서 찾는 기능도 제공함(messages.properties)
@ResponseStatus(code= HttpStatus.BAD_REQUEST, reason = "error.bad")
public class BadRequestException extends RuntimeException {


}

