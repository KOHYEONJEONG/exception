package hello.exception.servlet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Controller
public class ErrorPageController {


    //RequestDispatcher 상수로 정의되어 있음( 그냥 이 클래스에 들어가서 필요한것만 복사해 옴)
    //아래 상수명 복사할때 팁 ->  ctrl*2클릭 -> 방향키로 줄 선택하기 -> shift눌러서 단어 한번 커서 잡힙 -> 복사
    public static final String ERROR_EXCEPTION_TYPE ="javax.servlet.error.exception_type";
    public static final String ERROR_MESSAGE = "javax.servlet.error.message";
    public static final String ERROR_EXCEPTION ="javax.servlet.error.exception";
    public static final String ERROR_REQUEST_URI = "javax.servlet.error.request_uri";
    public static final String ERROR_SERVLET_NAME ="javax.servlet.error.servlet_name";
    public static final String ERROR_STATUS_CODE = "javax.servlet.error.status_code";


    @RequestMapping("/error-page/404")
    public String errorPage404(HttpServletRequest request, HttpServletResponse response){
        log.info("errorPage 404");
        printErrorInfo(request);
        return "error-page/404";
    }

    @RequestMapping("/error-page/500")
    public String errorPage500(HttpServletRequest request, HttpServletResponse response){
        log.info("errorPage 500");
        printErrorInfo(request);
        return "error-page/500";
    }

    //바로 에러페이지로 안가고 에러 정보를 살펴보기 위해서 만든 메서드
    private void printErrorInfo(HttpServletRequest request){
        log.info("ERROR_EXCEPTION_TYPE={}",request.getAttribute(ERROR_EXCEPTION_TYPE));
        log.info("ERROR_MESSAGE={}",request.getAttribute(ERROR_MESSAGE));
        log.info("ERROR_EXCEPTION={}",request.getAttribute(ERROR_EXCEPTION));
        log.info("ERROR_REQUEST_URI={}",request.getAttribute(ERROR_REQUEST_URI));
        log.info("ERROR_SERVLET_NAME={}",request.getAttribute(ERROR_SERVLET_NAME));
        log.info("ERROR_STATUS_CODE={}",request.getAttribute(ERROR_STATUS_CODE));

//  [서블릿 스펙은 실제 고객이 요청한 것인지, 서버가 내부에서 오류 페이지를 요청하는 것인지 DispatcherType으로 구분 쌉가능]
//        DispatcherType
//        REQUEST : 클라이언트 요청 <-- 고객이 처음 요청하면 dispatcherType = REQEUST
//        ERROR : 오류 요청
//        FORWARD : MVC에서 배웠던 서블릿에서 다른 서블릿이나 JSP를 호출할 때 RequestDispatcher.forward(request, response);
//        INCLUDE : 서블릿에서 다른 서블릿이나 JSP의 결과를 포함할 때 RequestDispatcher.include(request, response);
//        ASYNC : 서블릿 비동기 호출
        log.info("dispatcherType = {}", request.getDispatcherType());//중요 log-> dispatcherType = ERROR
    }
}
