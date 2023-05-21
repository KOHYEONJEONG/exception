package hello.exception;


import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

//REQUEST Header부분에 Accept : text/html 으로 수정하면 api예외가 아닌 html 예외로 처리돼~
/** 이 파일은 서블릿에 에러페이지 등록하는 방법이다. */
//section9에서 주석 해제~
//@Component //section8. 스프링부트가 제공하는 기본 오류 매커니즘을 사용하기 위해서 주석처리 해뒀다.(BasicErrorController가 자동으로 등록한다.)
public class WebServerCustomizer implements WebServerFactoryCustomizer<ConfigurableWebServerFactory> { //implements WebServerFactoryCustomizer<ConfigurableWebServerFactory>  무조건 구현~

    //여기는 api가 아닌 예외 처리(html 예외처리)
    //굳이 ServletController.java 없어도 되는듯;
    //서블릿이 보여주는 기본 에러페이지는 안예뻐
    //그래서 아래 에러가 터지면 보여지는 화면을 지정해서 만든 에러페이지로 보여 줄 수 있다.

    /***/
    @Override
    public void customize(ConfigurableWebServerFactory factory) {

        //에러가 발생하면 WAS까지 도달한다.
        //예외 발생 흐름 : WAS <-- 필터 <-- 서블릿 <-- 인터셉터 <-- 컨트롤러(예외발생)

        //아래 정의한 첫번째 파라미터와 예외가 같다면, WAS가 path경로를 보고 다시 호출(요청)한다.
        //(중요) 오류페이지 요청 흐름 : WAS '/error-page/500' 다시 요청 -> 필터 -> 서블릿 -> 인터셉터 -> 컨트롤러(/error-page/500) -> view(클라이언트에게 보여줌)


        ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/error-page/404");
        ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error-page/500");

        //RunTimeExCeption은 물론이고 RunTimeException의 자식도 함께 처리한다.
        ErrorPage errorPageEx = new ErrorPage(RuntimeException.class, "/error-page/500");

        factory.addErrorPages(errorPage404, errorPage500, errorPageEx);//에러페이지 등록


        //이제 오류가 발생했을 때 처리할 수 있는 컨트롤러가 필요하다. 예를들어서  RunTimeException예외가 발생하면 지정한 /error-page/500이 호출
        //ㄴ ErrorPageController.java
    }
}
