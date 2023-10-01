package exception;

public class StackException extends Exception{

    private String message;
    public StackException(String message){
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage(){
        return this.message;
    }
}
