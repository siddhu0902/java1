import java.util.Random;

class J28 {
    public static void passGen1(){
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
        System.out.println("Generated Password: " + password);
    }
    public static void main(String[] args) {
        passGen1();
    }
}
