package hello.exception.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** ExceptionResolver : 예외 해결사
 * ㄴ 인터셉터에서 컨트롤러 예외가 터지면
 * postHandler가 실행안되는거 알지?
 *
 * 그 부분을 여기서 처리해준다.
 *    참고 : ExceptionResolver 로 예외를 해결해도 postHandle() 은 호출되지 않는다.
 * */

/**
 * 스프링 MVC는 컨트롤러(핸들러) 밖으로 예외가 던져진 경우 예외를 해결하고, 동작을 새로 정의할 수 있는
 * 방법을 제공한다. 컨트롤러 밖으로 던져진 예외를 해결하고, 동작 방식을 변경하고 싶으면
 * HandlerExceptionResolver 를 사용하면 된다. 줄여서 ExceptionResolver 라 한다
 * */
@Slf4j
public class MyHandlerExceptionResolver implements HandlerExceptionResolver {

    //실행이 안되는데 뭐징??

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        log.info("call resolver", ex);
        try{

            //정상적인 ModelAndView로 반환해줘~
            if(ex instanceof IllegalArgumentException){
                log.info("IllegalArgumentException resolver to 400");


                //예외를 response.sendError 서블릿에서 상태 코드에 따른 오류를 처리하도록 위임.
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, ex.getMessage());//400

                //빈 ModelAndView 를 반환하면 뷰를 렌더링 하지않고, 정상 흐름으로 서블릿이 리턴
                return new ModelAndView();//빈 ModelAndView를 리턴시킨다는건 에러를 꿀꺽 삼키겠다는 뜻.
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return null; // null 을 반환하면, 다음 ExceptionResolver 를 찾아서 실행한다

    } //ExceptionResolver라고 부른다.

}
