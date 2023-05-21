package hello.exception.resolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.exception.exception.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/** ExceptionResolver : 예외 해결사
 * ㄴ 인터셉터에서 컨트롤러 예외가 터지면
 * postHandler가 실행안되는거 알지?
 *
 * 그 부분을 여기서 처리해준다.
 *
 * */
@Slf4j
public class UserHandlerExceptionResolver implements HandlerExceptionResolver{//ExceptionResolver로 부른다.

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        try{

            if(ex instanceof UserException){
                log.info("UserException resolver to 400");
                String acceptHeader = request.getHeader("accept");
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);//상태코드 변경

                if("application/json".equals(acceptHeader)){
                    Map<String, Object> errorResult = new HashMap<>();
                    errorResult.put("ex", ex.getClass());
                    errorResult.put("message", ex.getMessage());

                    String result = objectMapper.writeValueAsString(errorResult);

                    response.setContentType("application/json");
                    response.setCharacterEncoding("utf-8");
                    response.getWriter().write(result);

                    return new ModelAndView();//빈 ModelAndView를 리턴하면 정상적이개 서블릿 호출가능
                }else{
                    return  new ModelAndView("error/500");//이렇게 리턴하면 에러를 먹고 정상 흐름으로 리턴한다.
                }
            }

        }catch (IOException e){
            log.error("resolver ex " ,e);
        }


        return null;
    }
}
