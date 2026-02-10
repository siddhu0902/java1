import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class J25 {
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
}
