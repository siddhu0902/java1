import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class J30 {
    public static List<String> passGen2(int qty){    //Method parameters
        List<String> pass1=new ArrayList<>();
        for (int i=0;i<qty;i++){
            pass1.add(passGen1());
        }
        return pass1;
    }
    public static String passGen1(){
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String nums  = "0123456789";
        String all = upper + lower + nums;
        Random rand = new Random();
        String password = "";
        password += upper.charAt(rand.nextInt(upper.length()));
        password += lower.charAt(rand.nextInt(lower.length()));
        password += nums.charAt(rand.nextInt(nums.length()));
        for (int i = 0; i < 5; i++) {
            password += all.charAt(rand.nextInt(all.length()));
        }
        return password;
    }
    public static void main(String[] args) {
        List<String> result=new ArrayList<>();
        result=passGen2(20);
        for (int i=0;i<result.size();i++){
            System.out.println(result.get(i));
        }
        System.out.println(result);   //Method Argument
    }
}
