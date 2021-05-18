class reservation {


   private char RoomType;
   private String inDate, outDate, resNum, resState;
   private double price;
   public static int resCount;

   public reservation (char rType,String indate, String outdate, long card)
   {
      RoomType= rType;
      inDate= indate;
      outDate= outdate;
      resCount++;
      boolean valid = verfyCard(card);
   
      if (!valid) {
         price = CalculatePrice();
         resNum = GenerateReservationNumber();
      }

      else {
     resNum = "#####";
     }
   }

   public boolean verfyCard (long card){
   
      String s=card + " ";
      
      int numofdigits=s.length();
      
      int sum=0;
      boolean second=false;
      
      for(int i=numofdigits-1 ; i>=0 ; i--){
         int d=s.charAt(i)-'0'; 
      
           if(second == true)
            d = d * 2;
            
            sum +=(int) d / 10;
            sum += (int)d % 10;
            second = !second;
        }//end loop
                  
         if(sum % 10 == 0){
            resState = "UnConfirmed";
            return true;
      }else {
            resState = "confirmed";
            return false;
   }
  }   



   public String GenerateReservationNumber() {
      int random1 = (int) (26.0*Math.random());
      int random2 = (int) (26.0*Math.random());
      int digit=1;
      
      char capLetter = (char) (random1 + 'A' );
      char smallLetter = (char) (random2 + 'a' );
      
    String twoDigit="";
    
      for (int i=1; i<=2; i++){
         digit=(int)(10.0*Math.random());
      twoDigit+=""+digit;
      }
      
      String str= ""+ resCount + smallLetter + twoDigit + capLetter;
      return str;
   
   }

   public double CalculatePrice() {
   
      int checkInDay = Integer.parseInt(inDate.substring(0, inDate.indexOf('-')));
      int checkOutDay = Integer.parseInt(outDate.substring(0, outDate.indexOf('-')));
   
      int duration=1;
      if (checkOutDay == checkInDay) {
         duration = 1;
      } else if(checkInDay<checkOutDay) {
         duration = checkOutDay - checkInDay;
      }
      double roomPrice;
      switch (Character.toUpperCase(RoomType)) {
         case 'S':
            roomPrice = 150;
            break;
         case 'T':
            roomPrice = 220;
            break;
         case 'F':
            roomPrice = 400;
            break;
         default:
            roomPrice = 0;
      }
      return (duration * roomPrice);
   }

 

   public boolean CancelReservation() {
   
      if (resState.equals("Canceled"))
      {System.out.println("Reservation is already canceled");
         return false;}
      else {resState="Canceled";
         return true;}
   }

   public void Print() {
   
      System.out.printf("Reservation number : %-10s Reservation Status : %5s %n Room Type : %-19s Check in Date : %6s %n Price : %2.2f%n", resNum, resState, RoomType, inDate, price);
   }

   public void SetRoom(char room ) {
      RoomType=room;
   }
   
   public void SetPrice(double newPrice){
   price=newPrice;}

   public String getState() {
      return resState  ;
   }

   public String getresNum() {
   
      return resNum; }

   public String getCheckIn() {
      return inDate;}

   public double getprice () {
      return price;}

   public char getRoomType(){
      return RoomType;}

}
