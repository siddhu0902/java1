import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class J23 {
    public static void genOTP2(int size, int qty){
        List<Integer> rd2=new ArrayList<>();
        Random rd=new Random();
        int min=1;
        for(int i=1;i<size;i++){
            min=min*10;
        }
        int max=min*10;
        for (int i=0;i<qty;i++){
            rd2.add(rd.nextInt(min,max));
        }
        System.out.println(rd2);
    }
    public static void main(String[] args){
        System.out.println("OTP generator");
        genOTP2(2,10);
        genOTP2(4,10);
        genOTP2(6,5);
        J21.genOTP(8);   //use file. call to bring other file info.
    }
}
