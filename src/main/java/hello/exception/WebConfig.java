package hello.exception;

import hello.exception.filter.LogFilter;
import hello.exception.interceptor.LogInterceptor;
import hello.exception.resolver.MyHandlerExceptionResolver;
import hello.exception.resolver.UserHandlerExceptionResolver;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) { //인터셉터 등록하기.
        //인터셉터는 서블릿이 제공하는 기능이 아니라 스프링이 제공하는 기능이다. 따라서
        //DispatcherType과 무관하게 항상 호출된다(FILTER는 setDispatcherTypes로 등록해서 사용하면되지만~, 인터셉터는 없기에 excludePathPatterns를 사용해서 제외시켜주자)
        //오류 페이지 경로를 excludePathPatterns 를 사용해서 빼주면 된다.

        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**","*.ico",
                        "/error","error-page/**");//오류 페이지 경로

        //WAS(/error-page/500, dispatchType=ERROR) -> 필터(x) -> 서블릿 -> 인터셉터(X) ->컨트롤러(/error-page/500) -> View
    }


    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
        resolvers.add(new MyHandlerExceptionResolver());
        resolvers.add(new UserHandlerExceptionResolver());
    }

    // @Bean
    public FilterRegistrationBean logFilter(){

        FilterRegistrationBean<Filter>  filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(new LogFilter());
        filterFilterRegistrationBean.setOrder(1);
        filterFilterRegistrationBean.addUrlPatterns("/*");
        /**
         * public enum DispatcherType {
         *     FORWARD,
         *     INCLUDE,
         *     REQUEST,  <-- 고객이 요청을하면 dispatchType=REQUEST
         *     ASYNC,
         *     ERROR;
         *
         *     private DispatcherType() {
         *     }
         * }
         * */
        filterFilterRegistrationBean.setDispatcherTypes(DispatcherType.REQUEST, DispatcherType.ERROR);//이 두가지 일때만 사용기능하다.

        return filterFilterRegistrationBean;
    }

}
