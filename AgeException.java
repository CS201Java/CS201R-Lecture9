public class AgeException extends Exception{
    public AgeException (int age){
        super("This age is invalid: " + age);
    }
}