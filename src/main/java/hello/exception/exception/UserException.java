package hello.exception.exception;

/**
* HandlerExceptionResolver 활용
* - 예외가 발생하면 WAS까지 예외가 던져지고, WAS에서 오류 페이지 정보를 찾아서 다시 /error 를 호출하는 과정은 생각해보면 너무 복잡하다.
* - ExceptionResolver를 활용하면 예외가 발생했을 때 이런 복잡한 과정 없이 여기에서 문제를 깔끔하게 해결할 수 있다.
* */
public class UserException extends RuntimeException {
    //UserException  : 사용자 정의 예외

    // 에외가 발생하면 WAS까지 예외가 던져지고, WAS에서 오류페이지 정보를 찾아서 다시 /error를 호출하는 과정은 생각해보면 너무 복잡함
    // ExceptionResolver를 활용하여 예외 발생 시 복잡한 과정 없이 해결 가능하다.

    //사용자 정의 예외 생성
    
    public UserException(){
        super();
    }

    public UserException(String message){
        super(message);
    }

    public UserException(String message, Throwable cause){
        super(message, cause);
    }

    public UserException(Throwable cause) {
        super(cause);
    }

    protected UserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


    //ApiExceptionController.java에서 추가해서 사용해보자.
//     if (id.equals("user-ex")) {
//        throw new UserException("사용자 오류");
//    }
}
