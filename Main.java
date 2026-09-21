import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        System.out.println("Welcome to classes!");
        
        //CREATE A PERSON OBJECT USING OVERLOADED CONSTRUCTOR
        Person p1 = new Person("Albert","Einstein", 144);
        Person p2 = new Person("Frank", "Instien", 159);

        //CREATE A PERSON OBJECT USING DEFAULT CONSTRUCTOR
        //EXAMPLE 1 d) complete p3 using setters
        
        Person p3 = new Person();

        
        //PRINT OBJECTS 
         System.out.println(p1.printPerson());
         System.out.println(p2.printPerson());
         System.out.println(p3.printPerson());

        ArrayList<Person> myList = new ArrayList<>();
        myList.add(p1);
        myList.add(p2);
        myList.add(p3);

        for (Person p : myList){
            System.out.print(p.printPerson());
        }


        //CREATE AN ARRAYLIST OF PERSON OBJECTS
        ArrayList<Person> people = new ArrayList<>();
        try {
            File inFile = new File("people.txt");
            Scanner inputScan = new Scanner(inFile);
            
            //can do this in a single statement:
            //Scanner inputScan = new Scanner(new File("people.txt"));

            String inputLine;
            while (inputScan.hasNextLine()){
                inputLine = inputScan.nextLine();
                String[] tokens = inputLine.split(",");
                try{
                    int tempAge = Integer.parseInt(tokens[3]);
                    char tempType = tokens[0].toUpperCase().charAt(0);
                    if (tempType != 'P' && tempType != 'T' && tempType != 'E' && tempType != 'S'){
                        throw new TypeException(tempType);
                    }
                    else{
                        Person tempP = new Person(tokens[0].charAt(0), tokens[1], tokens[2], tempAge);
                        people.add(tempP);
                    }
                }
                catch(NumberFormatException e){
                    System.out.println("Number format exception: " + e.getMessage());
                }
                catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("Not enough tokens in input file: " + e.getMessage());
                }
                catch(Exception e){
                    System.out.println("Oops...: " + e.getMessage());
                }
            }

            //using FileWriter to write to output
            FileWriter fw = new FileWriter("outputFW.txt");

            fw.write("Scanner: Printing People ArrayList\n");
            System.out.println("Scanner: Printing People ArrayList Using FileWriter");
            for (Person p : people){
                fw.write(p.type + p.fname + p.lname + p.age + "\n");
                System.out.printf("%-4c %-15s %-15s %5d \n",p.type, p.fname, p.lname, p.age);
            }

            System.out.println("Scanner: closing scanner for file input");
            inputScan.close();
            fw.close();
        }

        catch (FileNotFoundException e){
            System.out.println("Unable to open file");
            return;
        }
        catch (Exception e){
            System.out.println("oops - something went wrong");
        }

        //EXAMPLE 2: INPUT USING BUFFERED READER (file)
        System.out.println("\nProcessing using buffered reader");
        
        try{
            BufferedReader inputBuffer = new BufferedReader(new FileReader("people.txt"));
            String line;
            while ((line = inputBuffer.readLine()) != null){    
                String[] tokens = line.split(",");
                try{
                    int tempAge = Integer.parseInt(tokens[3]);
                    char tempType = tokens[0].toUpperCase().charAt(0);
                    if (tempType != 'P' && tempType != 'T' && tempType != 'E' && tempType != 'S'){
                        throw new TypeException(tempType);
                    }
                    else{
                        Person tempP = new Person(tokens[0].charAt(0), tokens[1], tokens[2], tempAge);
                        people.add(tempP);
                    }
                }
                catch(NumberFormatException e){
                    System.out.println("Number format exception: " + e.getMessage());
                }
                catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("Not enough tokens in input file: " + e.getMessage());
                }
                catch(Exception e){
                    System.out.println("Oops...: " + e.getMessage());
                } 
            }     


            //using PrintWriter to write to output
            PrintWriter outputPW = new PrintWriter("outputPW.txt");
            outputPW.println("Buffered Reader: Printing People ArrayList Using PrintWriter");
            System.out.println("Buffered Reader: Printing People ArrayList");
            for (Person p : people){
                outputPW.printf("%-4c %-15s %-15s %5d \n",p.type, p.fname, p.lname, p.age);
                System.out.printf("%-4c %-15s %-15s %5d \n",p.type, p.fname, p.lname, p.age);
            }
            inputBuffer.close();
            outputPW.close();
        }
        catch (Exception e){
            System.out.println("oops - something went wrong");
        }
        finally {
            System.out.println("Buffered Read is finished.");
        }
        System.out.println("Statement after all is complete");

    }
 

}

