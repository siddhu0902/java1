import java.util.ArrayList;
import java.util.List;

public class J17 {
    public static void main(String[] args){
        System.out.println("The prime numbers are: ");
        System.out.println("2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97");
        List<Integer> primes=new ArrayList<>();
        primes.add(2);
        primes.add(3);
        int num1=0;
        boolean isPrime;

        for (int j=4;j<100;j++){
            num1=j;
            isPrime=true;
            for (int i=2;i<j;i++){
                if (num1%i==0){
                    isPrime=false;
                }
            }
            if (isPrime==true){
                primes.add(num1);
            }
        }
        System.out.println(primes);
    }
}
