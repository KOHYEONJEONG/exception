package hello.exception.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import hello.exception.exception.UserException;
@Slf4j
@RestController
public class ApiExceptionV2Controller {

    //ExControllerAdvice.java에서 예외처리를 관리하게 따로 빼뒀다.

    //순서
    // ㄴ 컨트롤러 예외 터짐 ->  exceptionResolver (@ExceptionHandler) 찾아서 호출! -> 정상 리턴 -> 그럼 200 즉 정상 로직 이기때문에
    // ㄴ 우리가 기대하는건 상태코드까지 제대로 반영해야겠지?
    // 그래서 @ResponseStatus(HttpStatus.BAD_REQUEST) 를 붙여준다.
    @GetMapping("/api2/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id) {
        if (id.equals("ex")) {
            throw new RuntimeException("잘못된 사용자");
        }

        if (id.equals("bad")) {
            throw new IllegalArgumentException("잘못된 입력 값");
        }

        if (id.equals("user-ex")) {
            throw new UserException("사용자 오류");
        }
        return new MemberDto(id, "hello " + id);
    }
    @Data
    @AllArgsConstructor
    static class MemberDto {
        private String memberId;
        private String name;
    }

}
