import java.util.Scanner;
public class J19 {
    public static void main(String[] args){
        String word = "funwithprogramming";
        int s=word.length();
        for (int i=1;i<=s;i++){
            System.out.println(word.substring(0,i));
        }
        for (int i=s-1;i>=1;i--){
            System.out.println(word.substring(0,i));
        }
    }
}
