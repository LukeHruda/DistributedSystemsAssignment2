import java.io.*; 
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class NumberClient{

   private NumberClient() {}

   public static void main(String argv[]) {
    try {
        //Connect to the registry running on localhost
        Registry registry = LocateRegistry.getRegistry("localhost");
        NumberInterface stub = (NumberInterface) registry.lookup("NumberInterface");

        //Print to user that connection to RMI server has been established. Ask for input
        System.out.println("Connection established. Type a command or type 'help'");
        while(true)
        {   
            // Enter data using BufferReader
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            // Reading data using readLine
            String input = reader.readLine();

            //if user inputs close, the client exits
            if(input.equals("close"))
            {
                System.exit(0);
            }
            else if(input.equals("fib"))
            {
                //if the user enters fibonacci the client asks for an integer and parses it
                System.out.println("Please enter an integer.");
                input = reader.readLine();
                int n = parseToInt(input);
                if(n!=-1)
                {
                    //if the integer passess the parsing run the fibonnaci method and return the results
                    System.out.println(stub.fibonacci(n));
                }
                else{
                    //print error message
                    System.out.println("Value entered is not an integer");
                }
                System.out.println("Next command:");
            }
            else if(input.equals("prime"))
            {
                //user has entered the prime command, client asks for an integer and parses it
                System.out.println("Please enter an integer.");
                input = reader.readLine();
                int n = parseToInt(input);
                if(n!=-1)
                {
                    //call the prime number method and print the results
                    System.out.println(stub.primeNum(n));
                }
                else{
                    //print error message
                    System.out.println("Value entered is not an integer");
                }
                System.out.println("Next command:");
            }
            else if(input.equals("3p1"))
            {
                //client calls the 3p1 algorithm, an integer is requested and parsed
                System.out.println("Please enter an integer.");
                input = reader.readLine();
                int n = parseToInt(input);
                if(n!=-1)
                {
                    //call the 3p1 method and print the results
                    System.out.println(stub.threeNPO(n));
                }
                else{
                    //print error message
                    System.out.println("Value entered is not an integer");
                }
                System.out.println("Next command:");
            }
            else if(input.equals("dice"))
            {
                //client calls the dice algorithm, two integers are requested and parsed
                System.out.println("Please enter two integers separated by a space.\nFirst int is sides of dice, second int is number of rolls.");
                input = reader.readLine();
                String[] parsed = input.split("\\s+");
                if(parsed.length == 2){
                    int n = parseToInt(parsed[0]);
                    int c = parseToInt(parsed[1]);
                    if((n!=-1)&&(c!=-1))
                    {
                        //call dice algorithm and print result
                        System.out.println(stub.nSidedDie(n,c));
                    }
                    else
                    {
                        //print error message
                        System.out.println("One of the values is not a number. Please try again.");
                    }
                }
                else
                {
                    //print error message
                    System.out.println("Incorrect number of arguments. Please try again.");
                }
                System.out.println("Next command:");
            }
            else if(input.equals("rps"))
            {
                //client calls the rps algorithm, an integer is requested and parsed
                System.out.println("Please enter an integer of 1 2 or 3.");
                input = reader.readLine();
                int n = parseToInt(input);
                if(n!=-1)
                {
                    if((n== 1) || (n==2) ||(n==3))
                    {
                        //prints result of rock paper scissors
                        System.out.println(stub.RPS(n));
                    }
                    else{
                        //print error message
                        System.out.println("Number out of range");
                    }
                }
                else{
                    //print error message
                    System.out.println("Value entered is not an integer");
                }
                System.out.println("Next command:");
            }
            else if(input.equals("stats"))
            {
                //user requests the stats command
                System.out.println(stub.stats());
                System.out.println("Next command:");
            }
            else if(input.equals("joke"))
            {
                //user requests the stats command
                System.out.println(stub.joke());
                System.out.println("Next command:");
            }
            else if(input.equals("help"))
            {
                //print list of commands
                System.out.println("List of commands are:\nfib\nprime\n3p1\ndice\nrps\nstats\njoke");
                System.out.println("Next command:");
            }
            else
            {
                //print that the command entered is not a part of the list of approved commands
                System.out.println("Command not recognized. Type 'help' for list of commands");
            }
        }
    } catch(Exception e) {
        System.err.println("FileServer exception: "+ e.getMessage());
        e.printStackTrace();
    }
   }
   public static Integer parseToInt(String value){
    try {
       return Integer.parseInt(value);
    } catch (NumberFormatException e) {
       return -1;
    }
 }
}