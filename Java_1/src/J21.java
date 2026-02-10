import java.util.ArrayList;
import java.util.Random;
import java.util.List;

public class J21 {
    public static void genOTP(int size){
        List<Integer> rd2=new ArrayList<>();
        Random rd=new Random();
        int min=1;
        for(int i=1;i<size;i++){
            min=min*10;
        }
        int max=min*10;
        for (int i=0;i<20;i++){
            rd2.add(rd.nextInt(min,max));
        }
        System.out.println(rd2);
    }
    public static void main(String[] args){
        System.out.println("OTP generator");
        genOTP(2);
        genOTP(4);
        genOTP(6);
    }
}
