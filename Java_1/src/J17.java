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

        num1=4;
        isPrime=true;
        for (int i=2;i<4;i++){
            if (num1%i==0){
                isPrime=false;
            }
        }
        if (isPrime==true){
            System.out.println(num1+" is a prime");
        }

        num1=5;
        isPrime=true;
        for (int i=2;i<5;i++){
            if (num1%i==0){
                isPrime=false;
            }
        }
        if (isPrime==true){
            System.out.println(num1+" is a prime");
        }

        num1=6;
        isPrime=true;
        for (int i=2;i<6;i++){
            if (num1%i==0){
                isPrime=false;
            }
        }
        if (isPrime==true){
            System.out.println(num1+" is a prime");
        }
    }
}
