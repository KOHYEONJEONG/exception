package hello.exception.resolver;

import hello.exception.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** ExceptionResolver : 예외 해결사
 * ㄴ 인터셉터에서 컨트롤러 예외가 터지면
 * postHandler가 실행안되는거 알지?
 *
 * 그 부분을 여기서 처리해준다.
 *
 * */
@Slf4j
public class UserHandlerExceptionResolver implements HandlerExceptionResolver{
    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        try{

            if(ex instanceof UserException){
                log.info("UserException resolver to 400");
                String acceptHeader = request.getHeader("accept");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);//상태코드 변경

               // if("application/json".equals())
            }

        }catch (IOException e){
            log.error("resolver ex " ,e);
        }


        return null;
    }
}
