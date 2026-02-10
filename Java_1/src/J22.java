import java.util.Random;
import java.util.Scanner;

public class J22 {
    public static void main(String[] args){
        System.out.println("OTP generator");
        Random rd=new Random();
        for (int i=0;i<20;i++){
            System.out.println(rd.nextInt(9,11));
        }

    }
}
