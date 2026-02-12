import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class J31 {
    public static void add1(int num1,int num2){
        int sum1=num1+num2;
        System.out.println(sum1);
    }
    public static void add1(int num1, int num2 , int num3){
        int sum2=num1+num2+num3;
        System.out.println(sum2);
    }
    public static void add1(double num1, double num2){
        double sum2=num1+num2;
        System.out.println(sum2);
    }

    public static void main(String[] args){
        add1(8,4);
        add1(8,4,2);
        add1(8.5,4.5);
    }
}
