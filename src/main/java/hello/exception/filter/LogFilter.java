package hello.exception.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {//서블릿에서 제공하는 필터

    //작성 후 WebConfig.java 파일에 등록하자~

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        log.info("log filter init");

    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String requestURI = httpServletRequest.getRequestURI();

        String uuid = UUID.randomUUID().toString();

        try {
            log.info("REQUEST [{}] [{}] [{}]", uuid, request.getDispatcherType(), requestURI);
            chain.doFilter(request, response);   //예외가 잡히면 다시 서블릿으로 거슬러 올라가서 요청을 해 그때는 DispatcherType이 - ERROR 이다.
        }catch (Exception e){

            log.info("EXCEPTION {}",e.getMessage());

            throw e;//에러가 발생하면 다시 WAS로 올라가 그럼 다시 필터가 실행될때 그때는  REQUEST [uuid] [ERROR] [/error-page/500] <-- 이렇게 출력되겠지? 다시 실행하니까
        }finally {
            log.info("RESPONSE [{}] [{}] [{}]", uuid, request.getDispatcherType(), requestURI);
        }
    }

    @Override
    public void destroy() {
        log.info("log filter destroy");
    }
}
