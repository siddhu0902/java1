import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class J15 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Gold medal");
        File f1=new File("Students.txt");
        Scanner sc1 = new Scanner(f1);
        List<String> names=new ArrayList<>();
        List<Integer> engMarks=new ArrayList<>();
        List<String> engToppers=new ArrayList<>();
        List<Integer> mathMarks=new ArrayList<>();
        List<String> mathToppers=new ArrayList<>();
        String s1="";
        String[] arr1;
        String[] arr2;
        int mathMax=0;
        int engMax=0;
        int len1=0;
        while(sc1.hasNext()){
            s1=sc1.nextLine();
            arr1 =s1.split(",");
            names.add(arr1[0]);
            arr2=arr1[3].split(":");
            engMarks.add(Integer.parseInt(arr2[1]));
        }
        engMax= Collections.max(engMarks);
        len1=engMarks.size();
        for (int i=0;i<len1;i++){
            if (engMarks.get(i)==engMax){
                engToppers.add(names.get(i));
            }
        }
        System.out.println(engToppers+" are the toppers in English with marks: "+engMax);
    }
}
