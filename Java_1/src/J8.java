import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
public class J8 {
    public static void reverse4(String fname)throws FileNotFoundException,IOException{
        File f1=new File(fname+"_in.txt");
        FileWriter fw1=new FileWriter(fname+"_out.txt");
        Scanner sc1=new Scanner(f1);
        String name1="";
        String name2="";
        String result="";
        while(sc1.hasNext()){
            name1=sc1.nextLine();
            name2=reverse3(name1);
            result=name1+"-"+name2;
            System.out.println(result);
            fw1.write(result);
            fw1.write("\n");
        }
        fw1.close();
    }
    public static String reverse3(String s1){
        String s2="";
        int len1=s1.length();
        for(int i=1;i<len1+1;i++){
            s2=s2+s1.charAt(len1-i);
        }
        return s2;
    }
    public static void main(String[] args) throws FileNotFoundException,IOException {
        reverse4("Cricket");
        reverse4("Films");
    }
}
