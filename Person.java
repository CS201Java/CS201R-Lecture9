public class Person{
    char type;
    String lname;
    String fname;
    int age;

    Person(){
        type = ' ';
        lname = "";
        fname = "";
        age = 0;
    }
    Person(char t, String l, String f, int a){
        type = t;
        lname = l;
        fname = f;
        age = a;
    }
}