import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class J5 {
    public static void reverse2(String fname) throws FileNotFoundException{
        File f1=new File(fname);
        Scanner sc1=new Scanner(f1);
        String name1="";
        String name1_rev="";
        while(sc1.hasNext()){
            name1=sc1.nextLine();
            name1_rev=reverse1(name1);
            System.out.println(name1+"-"+name1_rev);
        }
    }
    public static String reverse1(String s1){
        String s2 = "";
        int len1 = s1.length();
        for(int i=1;i<len1+1;i++){
            s2=s2+s1.substring(len1-i,len1+1-i);
        }
        return s2;
    }
}