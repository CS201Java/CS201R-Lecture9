public class Person {
    //EXAMPLE 1 a) update class attributes to private
    char type;
    String fname;
    String lname;
    int age;

    //NOTE: no modifier indicates only classes in the same
    //      package can instantiate the class
    Person(){
        fname = "";
        lname = "";
        age = 0;
        totalPeople++;
    }

    //EXAMPLE 1 b) add overloaded constructor
    Person(String f, String l, int a){
        type = 'P';
        fname = f;
        lname = l;
        age = a;
        totalPeople++;
    }

    //EXAMPLE 1 c)  complete getters & setters
    //create all getters (accessors)
    public String getFName(){return fname;}
 
    
    //create all setters (mutators)
    public void setFName(String fname){this.fname = fname;}
 

    //create the print method
    public String toString(){
        String out = String.format("%-15s%-15s%5d\n",fname,lname, age);
        return out;
    }

    public static int totalPeople = 0;
            
}
