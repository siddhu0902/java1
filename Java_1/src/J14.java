import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class J14 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Gold medal");
        File f1=new File("Students.txt");
        Scanner sc1 = new Scanner(f1);
        List<String> names=new ArrayList<>();
        String s1="";
        String[] arr1;
        while(sc1.hasNext()){
            s1=sc1.nextLine();
            arr1 =s1.split(",");
            names.add(arr1[0]);
        }
        System.out.println(names);
    }
}
