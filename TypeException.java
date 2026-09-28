public class TypeException extends Exception{

    public TypeException (char t){
        super ("Invalid Person Type: " + t);
    }

}