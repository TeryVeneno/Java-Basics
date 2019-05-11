import java.util.*;
import java.io.*;


public class ArmySolver {
 public static void main(String[] args) throws InterruptedException, IOException {
   int x = 13;
   int y = 17;
   int z = 23;
   for (int i = 5000; i > 0; i--) {
     if (i%x == 8 && i%y == 7 && i%z == 6) {
       new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
       System.out.println("Army Problem");
       System.out.println("Army: "+i);
       System.out.println("X rows: "+(i-8)/x);
       System.out.println("Y rows: "+(i-7)/y);
       System.out.println("Z rows: "+(i-6)/z);
     }
   }
 }
}
