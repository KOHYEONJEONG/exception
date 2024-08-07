package hello.exception.exception.advice;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("hello.exception.controller") // 지정된 패키지에서만 적용
public class GlobalCatcher {

    @ExceptionHandler(Exception.class)
    public String catcher(Exception ex, Model m) {
        m.addAttribute("ex", ex);
        return "error-page/404";
    }

    @ExceptionHandler(NullPointerException.class)
    public String catcher2(Exception ex, Model m) {
        m.addAttribute("ex", ex);
        return "error-page/null";
    }
}
