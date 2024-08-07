package hello.exception.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class ServletController {//시작

    /**
     * BasicErrorController가 알아서 error 페이지 경로를 인식하고 있다.
     * - 따로 지정안해도 된다.
     * - resources/templates/error 경로를 생성한 뒤 안에다가 파일을 생성하면 된다. (ex, 4xx.html, 404.html...)
     * */

    //WebServerCustomizer.java

    //WAS(sendError 호출 기록 확인) <- 필터 <- 서블릿 <- 인터셉터 <- 컨트롤러 (response.sendError())
    //response라는게 서블릿 컨테이너까지 절달된다.

    @GetMapping("/error-ex")
    public void errorEx(){
        throw new RuntimeException("예외 발생");
    }

    @GetMapping("/error-404")
    public void error404(HttpServletResponse response) throws IOException {
        // response.sendError(HTTP 상태 코드, 오류 메시지) <--톰캣이 오류가 발생했는지 인지를 한다.

        response.sendError(404, "404 오류!");
    }

    @GetMapping("/error-500")
    public void error500(HttpServletResponse response) throws IOException {
        // response.sendError(HTTP 상태 코드, 오류 메시지(제외가능)) <--톰캣이 오류가 발생했는지 인지를 한다.
        response.sendError(500, "500 오류!!!");
    }
}
