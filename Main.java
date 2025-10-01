import java.util.ArrayList;
//these are used for Scanner input:
import java.util.Scanner;
import java.io.File;
//these are used for Buffered Reader:
import java.io.BufferedReader;
import java.io.FileReader;

//this is used for output
import java.io.FileWriter;
import java.io.PrintWriter;

import java.io.FileNotFoundException;
import java.io.IOException;


public class Main {

    public static void main(String[] args) {


        //EXAMPLE 1: INPUT USING SCANNER (keyboard & file)
        
        //define scanner for user input from keyboard
        Scanner userInput = new Scanner(System.in);

      	System.out.print("Enter two integers: ");
        int number1 = userInput.nextInt();
	    int number2 = userInput.nextInt();
	    System.out.println(number1 + " / " + number2 + " is " + (number1 / number2));
        userInput.close();


        //declare ArrayList to hold Person objects
        ArrayList<Person> people = new ArrayList<>();

        //define scanner for file input
        System.out.println("\nProcessing input using scanner");

        //NOTE: these statements are commented out here
        //  Having these statements here will cause this error:
        //     Main.java:34: error: unreported exception FileNotFoundException; 
        //                   must be caught or declared to be thrown
        //File inFile = new File("people.txt");
        //Scanner inputScan = new Scanner(inFile);
        
        try{
            //opening the file inside the try block 'handles' the FileNotFoundException
            //will not compile outside the try block

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
        catch(IOException e){
            System.out.println("Error opening the file: " + e.getMessage());
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

            System.out.println("Buffered Reader: closing file input");
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

