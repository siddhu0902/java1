import java.util.ArrayList;
import java.util.List;

public class J18 {
    public static void getPrime1(int range){
        List<Integer> primes=new ArrayList<>();
        primes.add(2);
        primes.add(3);
        int num1=0;
        boolean isPrime;

        for (int j=4;j<range;j++){
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
    public static List<Integer> getPrime2(int range){
        List<Integer> primes=new ArrayList<>();
        primes.add(2);
        primes.add(3);
        int num1=0;
        boolean isPrime;

        for (int j=4;j<range;j++){
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
        return primes;
    }
    public static void main(String[] args){

        System.out.println("The prime numbers are: ");
        System.out.println("2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97");
        getPrime1(20);
        getPrime1(50);
        getPrime1(100);
        List<Integer> result=new ArrayList<>();
        result = getPrime2(20);
        System.out.println(result);
    }
}
