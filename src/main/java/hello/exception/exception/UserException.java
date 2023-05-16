package hello.exception.exception;

public class UserException extends RuntimeException {

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

}
