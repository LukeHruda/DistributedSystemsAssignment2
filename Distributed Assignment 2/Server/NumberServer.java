import java.io.*;
import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.server.RemoteObject;
import java.util.concurrent.ThreadLocalRandom;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class NumberServer implements NumberInterface{

   public NumberServer() {}
   private static int uses;
   private static int fibuses;
   private static int primeuses;
   private static int tpouses;
   private static int diceuses;
   private static int rpsuses;
   

   public static void main(String argv[]) {
      try {
         uses = 0;
         fibuses = 0;
         primeuses = 0;
         tpouses = 0;
         diceuses = 0;
         rpsuses = 0;
        NumberServer obj = new NumberServer();
        NumberInterface numInt = (NumberInterface) UnicastRemoteObject.exportObject(obj, 0);
         Registry registry = LocateRegistry.getRegistry();
         registry.bind("NumberInterface", numInt);
         System.out.println("Server Ready. Service is running..");
         
      } catch(Exception e) {
         System.out.println("FileServer: "+e.getMessage());
         e.printStackTrace();
      }
   }

   public int fibonacci(int n)
   {
      uses++;
      fibuses++;
      if (n <= 1)
         return n;
      return fibonacci(n-1) + fibonacci(n-2);
   }

   public String primeNum(int n)
   {
      uses++;
      primeuses++;
      //create initial variables
      int limit = n;
      String reponse = "";
      int i = 0;
      for (i = 1; i <= limit; i++)         
      { 		  	  
         int countDiv=0; 	  
         for(n =i; n>=1; n--)
         {
            if(i%n==0)
            {
               //count the number of factors that i has
               countDiv = countDiv + 1;
            }
	      }
         //if the number only has 2 factors 1 and itself, it is a prime number
         if (countDiv ==2)
         {
            //add that number onto the repsonse string
            reponse = reponse + i + " ";
	      }	
      }	
      return reponse;
   }

   //prints the sequence of the Collatz conjecture, otherwise known as 3n+1
   public String threeNPO(int n){
      uses++;
      tpouses++;
      String response = n + " ";
      while(n!=1){
         if(n%2 == 0)
         {
            n=n/2;
            response = response + n + " ";
         }
         else
         {
            n=n*3+1;
            response = response + n + " ";
         }
      }
      return response;
   }

   public String nSidedDie(int n, int c){
      uses++;
      diceuses++;
      String response = "An "+n+" sided die rolled "+c+" times gives\n";
      DecimalFormat df = new DecimalFormat("0.00");

      int[] count = new int[n];
      Double[] perc = new Double[n];
      for(int i = 0; i < n; i++)
      {
         count[i] = 0;
      }

      for(int i = 0; i < c; i++)
      {
         int randomNum = ThreadLocalRandom.current().nextInt(0, n);
         count[randomNum]++;
      }
      for(int i = 0; i < n; i++)
      {
         perc[i] = (count[i]/(double)c)*100;
         response = response + "Side "+(i+1)+" appeared "+count[i]+" times, for "+df.format(perc[i])+"% of the total.\n";
      }

      return response;
   }

   //1 for rock, 2 for paper, 3 for scissors
   public String RPS(int n){
      uses++;
      rpsuses++;
      int randomNum = ThreadLocalRandom.current().nextInt(1, 4);
      String response = "";
      if(n ==1){
         if(randomNum == 1)
         {
            response = "You both chose Rock! Tie Game!";
         }
         else if(randomNum ==2)
         {
            response = "The AI chose paper! You lose!";
         }
         else{
            response = "The AI chose scissors! You Win!";
         }
      }
      else if (n==2){
         if(randomNum == 1)
         {
            response = "The AI chose rock! You Win!";
         }
         else if(randomNum ==2)
         {
            response = "The AI chose paper! Tie Game!";
         }
         else{
            response = "The AI chose scissors! You lose!";
         }
      }
      else{
         if(randomNum == 1)
         {
            response = "The AI chose rock! You lose!";
         }
         else if(randomNum ==2)
         {
            response = "The AI chose paper! You Win!";
         }
         else{
            response = "The AI chose scissors! Tie Game!";
         }

      }
      
      return response;
   }

   public String stats()
   {
      String response = "The server has completed "+uses+" operations. Brokendown by:\n Fibonacci: "+fibuses+"\n Prime Number: "+primeuses+"\n Three N p One uses: "+tpouses+"\n Rock Paper Scissors: "+rpsuses;
      return response;
   }

   public String joke()
   {
      //populate the jokes array and pick a random one
      ArrayList<String> jokes = new ArrayList<String>();
      String joke ="How many programmers does it take to change a lightbulb?\nOnly one. But then the whole house falls down.";
      jokes.add(joke);
      joke = "How many programmers does it take to change a lightbulb?\nNone. That is a hardware problem.";
      jokes.add(joke);
      joke = "How many programmers does it take to change a lightbulb?\nThis is a known issue. When we installed the lightbulb we knew it had a finite TTL.";
      jokes.add(joke);
      joke = "How many programmers does it take to change a lightbulb?\nTwo. One to establish that the lightbulb is no longer functioning as intended and another to call Building Maintenance about the burnt out bulb.";
      jokes.add(joke);
      joke = "*Knock knock*\nWho's there?\n[long pause]\nJava!";
      jokes.add(joke);
      joke = "Three Haskell programmers walk into a bar. The bartender asks 'do you all want a beer?\nThe first one says 'no.'\nThe other two turn out to never have walked into the bar at all.";
      jokes.add(joke);
      joke = "A QA Engineer walks into a bar.\nThe QA engineer orders a beer. Orders 0 beers. Orders 999999999 beers. Orders a lizard. Orders -1 beers. Orders a sfdeljknesv.\nA customer comes in and asks where the bathroom is.\n The bar bursts into flames.";
      jokes.add(joke);

      return jokes.get(ThreadLocalRandom.current().nextInt(0, 7));
   }

}