import java.util.*;
import java.text.SimpleDateFormat;
public class hotel
{
   final public static int MAX_SIZE=100;
   public  static reservation [] rList= new reservation[MAX_SIZE];

   static Scanner input= new Scanner (System.in);        
   public static void main (String [] args){
   
     String typeOfUser;
     
     do{
      System.out.println("Welcome to Hotel system, Please select your option (guest/clerk) or END to exit");
      typeOfUser=input.next();
      logIn(typeOfUser.toUpperCase());
      }
      while(!typeOfUser.toUpperCase().equals("END"));
       
   }//end main

   public static void logIn (String typeOfUser){
   
    String resNum;
            String status;
            int count;
     switch(typeOfUser) {
    
      case "CLERK":{
         System.out.println("Clerk Menu\n"+"You have the following options\n"+"1-Find reservation\n"+"2-Display all Confirmed reservations\n"+"3-Modify reservation\n"+"4-Cancel reservation\n"+"5-Count the number of reservation\n"+"6-Display all due reservations\n"+"7-Exit\n"+"Enter your option");
         int choiceClerk=input.nextInt();
      
         switch (choiceClerk){
        
           case 1: {System.out.println("Enter reservation number: ");
            String resNumber = input.next();
           int n = Find(resNumber);
           if(n==-1)
           System.out.println("No reservations found.");
           else System.out.println("Reservation exists at index of " + n );
              } break;
              
            case 2: Display();
               break;
              
            case 3:{System.out.println("Enter reservation number");
               resNum=input.next();
               boolean modify = Modify(resNum);
               if(modify)
               System.out.println("Reservation has been modified.");
               else System.out.println("Failed to modify the reservation.");
               }
               break;
              
            case 4: {System.out.println("Enter reservation number");
               resNum=input.next();
               boolean cancel = Cancel(resNum);
               if(cancel)
               System.out.println("Reservation has been canceled.");
               else System.out.println("Failed to cancel the reservation.");
               }
               break;
              
            case 5: {System.out.println("Enter reservations status");
               status=input.next();
               count=CountReservations(status);
               if(count>=0) System.out.println("There are " + count +" "+ status + " reservations.");
               else  System.out.println("No reservations of this status yet.");
               }
               break;
              
            case 6: DisplayDueReservations();
               break;
            case 7: System.out.println("Exit");
               break;
            default: System.out.println("invalid input");
         }
        }
        break;
         
              
      case "GUEST":{
         System.out.println("Guest Menu");
         System.out.println("You have the following options");
         System.out.println("Add Reservation");
         boolean added=AddReservation();
         if (added==false)
            System.out.println("No available rooms");
         else
         {int choiceGuest;
        
            System.out.println("You can perform the following operations on your reservation:\n"+"1-Cancel reservation\n"+"2-Display reservation\n"+"3-Exit\n"+"Enter your option");
            choiceGuest =input.nextInt();
        
            switch (choiceGuest)
            {
               case 1: CancelMyReservation();
                  break;
               case 2: DisplayMyReservation();
                  break;
               case 3: System.out.println("Exit");
                  break;
               default: System.out.println("invalid input");
            }}
            }break;
   
    case "END":
    break;
    default: System.out.println("Invalid user input, please try again.");}
    }



   public static boolean AddReservation()
   {
   
      if (reservation.resCount>=MAX_SIZE)
         return false;
   
      System.out.println("Enter reservation information");
      System.out.println("Enter room type (S for Single - T for Twin - F for Family)");
      char rType=input.next().charAt(0);
      System.out.println("Enter Credit Card number");
      long card=input.nextLong();
      System.out.println("Enter Check in month");
      int inMonth=input.nextInt();
      System.out.println("Enter Check in day");
      int inDay=input.nextInt();
      System.out.println("Enter Check out day");
      int outDay=input.nextInt();
   
      String inmonth;
      String inday;
      String outday;
   
      if(inMonth<10)
         inmonth="0"+inMonth;
      else inmonth=""+inMonth;
   
      if(inDay<10)
         inday="0"+inDay;
      else inday=""+inDay;
   
      if(outDay<10)
         outday="0"+outDay;
      else outday=""+outDay;
   
   
      String indate=(inday+"-"+inmonth);
      String outdate=(outday+"-"+inmonth);
   
   
      reservation r=new reservation (rType, indate, outdate, card);
      if (reservation.resCount==0)
      {rList [reservation.resCount]= r;
         return true;
      }
      else{
         rList [reservation.resCount-1]= r;
         return true;}
   }
   
   
   public static boolean CancelMyReservation()
   {
      boolean canceled;
      if (reservation.resCount==0)
      { canceled=rList[reservation.resCount].CancelReservation();
         return canceled;}
      else
      { canceled=rList[reservation.resCount -1].CancelReservation();
         return canceled;}
   }
   
   
   public static void DisplayMyReservation(){
   
      if (reservation.resCount==0)
      
         rList[reservation.resCount].Print();
      else
         rList[reservation.resCount-1].Print();
   
   }



   public static int Find(String resNumber)
   {
      int location=-1;
      for (int i=0; i<rList.length;i++){
         if (rList[i]!=null)
         {
            if (rList[i].getresNum().equals(resNumber))
            {location=i;}}}
      return location;}
 

 
   public static void Display(){
   
      if (reservation.resCount > 0 ){
      
         for (int i = 0; i < reservation.resCount; i++){
            String status = rList[i].getState();
            if (status.toUpperCase().equals("CONFIRMED")){
               rList[i].Print();
            }
         }
      }
      else {
         System.out.println("No confirmed reservations found.");
      }
   }
 
 
   public static boolean Modify(String resNumber){
      for (int i = 0; i<reservation.resCount; i++){
         if(rList[i].getresNum().equals(resNumber)){
            if (rList[i].getState().toUpperCase().equals("CONFIRMED")){
               System.out.println("Enter room type (S for Single - T for Twin - F for Family)");
               char roomType= input.next().toUpperCase().charAt(0);
               rList[i].SetRoom(roomType);
               double newPrice = rList[i].CalculatePrice();
               rList[i].SetPrice(newPrice);
               return true;
            }
         }
      }
      return false;
   }


 
   public static boolean Cancel( String resNumber){
      for (int i=0; i<reservation.resCount; i++){
         if (rList[i].getresNum().toUpperCase().equals(resNumber.toUpperCase())){
            return rList[i].CancelReservation();
         }
      }
      return false;
   }


   public static int CountReservations(String status){
      int counter = 0;
      for (int i=0; i< reservation.resCount; i++) {
         if(rList[i].getState().equalsIgnoreCase(status)){
            counter++;
         }
      }
      return counter;
   }


   public static void DisplayDueReservations(){
      String date = new SimpleDateFormat("dd-MM").format(new Date());
      int day = Integer.parseInt(date.substring(0, date.indexOf('-')));
      int month = Integer.parseInt(date.substring(date.indexOf('-')+1));
      int counter = 0;
      for (int i=0; i<reservation.resCount; i++) {
         String inDate = rList[i].getCheckIn();
         int inDay = Integer.parseInt( inDate.substring(0, inDate.indexOf('-')));
         int inMonth = Integer.parseInt(inDate.substring(inDate.indexOf('-')+1));
      
         if (inMonth == month){
            if (inDay == day+1) {
               rList[i].Print();
               counter ++;
            }
         }
      
      }
      if (counter == 0) {
         System.out.println("No reservations due tomorrow");
      }
      else System.out.println("There are "+counter+" reservations tommorrw");
   }}


