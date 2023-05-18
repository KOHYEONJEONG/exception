package hello.exception.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * 인터셉터는 스프링 MVC 구조에 특화된 필터 기능을 제공한다고 이해하면 된다.(그래서 서블릿에서 제공하는 핕터는 잘 사용 안한다)
 * ㄴ 스프링 MVC를 사용하고 특별히 필터를 꼭 사용하는 게 아니면 인터셉터를 사용하는것이 더 편리하다.
 * ㄴ 인터셉터는 aop와 다르게 HTTP의 헤더 또는 URL의 정보들이 필요할 때 사용하며 HttpServeltRequest를 제공한다.
 * */
@Slf4j
public class LogInterceptor implements HandlerInterceptor {//HandlerInterceptor는 스프링이 제공

    public static final String LOG_ID = "logId";

    /**
     * 컨트롤러 호출 전에 호출된다.
     * */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestURI = request.getRequestURI();
        String uuid = UUID.randomUUID().toString();
        request.setAttribute(LOG_ID, uuid);

        log.info("REQUEST [{}] [{}] [{}] [{}]",uuid, request.getDispatcherType(), requestURI, handler);

        return true;
    }


    /**
     * 컨트롤러 호출 후에 호출된다(컨트롤러에 예외가 발생하면 호출되지 않아~)
     * ㄴ컨트롤러에서 예외가 발생하면 postHandler은 호출되지 않는다.
     * */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
       //was에서 2번째 요청 시에 즉 ERROR일때는 호출된다.(처음에는 에러가 터졌기 때문에 postHandler()가 호출 안됌- 컨틀롤러에서 에러나면 출력 안한다)
        log.info("postHandle [{}]", modelAndView);
    }

    /**
     * 뷰가 렌더링 된 이후에 호출된다.
     * ㄴ afterCompletion은 항상 호출된다. 이 경우 예외(ex)를 파라미터로 받아서 어떤 예외가 발생했는지 로그로 출력할 수 있다.
     * */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        String requestURI = request.getRequestURI();
        String logId = (String) request.getAttribute(LOG_ID);
        log.info("RESPONSE [{}] [{}] [{}]", logId, request.getRequestURI(), requestURI);
        if(ex != null){
            log.error("afterCompletion error!", ex);
        }
    }
}
