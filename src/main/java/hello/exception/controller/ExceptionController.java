package hello.exception.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
public class ExceptionController {

    /**
     * ExceptionController 터지는 에러 페이지 지정은
     * GlobalCatcher 에서 관리한다.
     *  - @ControllerAdvice
     * */

    @RequestMapping("/member")
    public String member() throws Exception {
        return "basic/main";
    }

    @RequestMapping("/ex")
    public String main() throws Exception {
        throw new Exception("예외가 발생했습니다.");
        /*
        2023-05-21 21:01:01.451  INFO 3112 --- [nio-9080-exec-1] h.exception.interceptor.LogInterceptor   : REQUEST [accdd04e-4db8-4fa4-82aa-2dfbf2fe2c70] [REQUEST] [/ex] [hello.exception.controller.ExceptionController#main()]
        2023-05-21 21:01:01.460  WARN 3112 --- [nio-9080-exec-1] .m.m.a.ExceptionHandlerExceptionResolver : Resolved [java.lang.Exception: 예외가 발생했습니다.]
        2023-05-21 21:01:01.576  INFO 3112 --- [nio-9080-exec-1] h.exception.interceptor.LogInterceptor   : RESPONSE [accdd04e-4db8-4fa4-82aa-2dfbf2fe2c70] [/ex] [/ex]
        */
    }

    @RequestMapping("/ex2")
    public String main2() throws Exception {
        throw new NullPointerException("예외가 발생했습니다.");
    }
}
