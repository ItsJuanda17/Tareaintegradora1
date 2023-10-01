package exception;

public class QueueException extends Exception{

    private String message;
    public QueueException(String message){
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage(){
        return this.message;
    }
}
