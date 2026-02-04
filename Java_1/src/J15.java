import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class J15 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Gold medal");
        File f1=new File("Students.txt");
        Scanner sc1 = new Scanner(f1);
        List<String> names=new ArrayList<>();
        List<String> subjects=new ArrayList<>();

        List<Integer> engMarks=new ArrayList<>();
        List<Integer> matMarks=new ArrayList<>();
        List<Integer> phyMarks=new ArrayList<>();
        List<Integer> cheMarks=new ArrayList<>();
        List<Integer> bioMarks=new ArrayList<>();

        List<String> engToppers=new ArrayList<>();
        List<String> matToppers=new ArrayList<>();
        List<String> phyToppers=new ArrayList<>();
        List<String> cheToppers=new ArrayList<>();
        List<String> bioToppers=new ArrayList<>();

        String s1="";
        String[] arr1;
        String[] arr2;

        int engMax=0;
        int matMax=0;
        int phyMax=0;
        int cheMax=0;
        int bioMax=0;

        int len1=0;
        while(sc1.hasNext()){
            s1=sc1.nextLine();
            arr1 =s1.split(",");
            names.add(arr1[0]);

            arr2=arr1[3].split(":");
            subjects.add(arr2[0]);
            engMarks.add(Integer.parseInt(arr2[1]));

            arr2=arr1[4].split(":");
            subjects.add(arr2[0]);
            matMarks.add(Integer.parseInt(arr2[1]));

            arr2=arr1[5].split(":");
            subjects.add(arr2[0]);
            phyMarks.add(Integer.parseInt(arr2[1]));

            arr2=arr1[6].split(":");
            subjects.add(arr2[0]);
            cheMarks.add(Integer.parseInt(arr2[1]));

            arr2=arr1[7].split(":");
            subjects.add(arr2[0]);
            bioMarks.add(Integer.parseInt(arr2[1]));
        }
        engMax= Collections.max(engMarks);
        matMax= Collections.max(matMarks);
        phyMax= Collections.max(phyMarks);
        cheMax= Collections.max(cheMarks);
        bioMax= Collections.max(bioMarks);

        len1=engMarks.size();
        for (int i=0;i<len1;i++){
            if (engMarks.get(i)==engMax){
                engToppers.add(names.get(i));
            }
            if (matMarks.get(i)==matMax){
                matToppers.add(names.get(i));
            }
            if (phyMarks.get(i)==phyMax){
                phyToppers.add(names.get(i));
            }
            if (cheMarks.get(i)==cheMax){
                cheToppers.add(names.get(i));
            }
            if (bioMarks.get(i)==bioMax){
                bioToppers.add(names.get(i));
            }
        }
        System.out.println(engToppers+" are the toppers in "+subjects.get(0)+" with marks: "+engMax);
        System.out.println(matToppers+" are the toppers in "+subjects.get(1)+" with marks: "+matMax);
        System.out.println(phyToppers+" are the toppers in "+subjects.get(2)+" with marks: "+phyMax);
        System.out.println(cheToppers+" are the toppers in "+subjects.get(3)+" with marks: "+cheMax);
        System.out.println(bioToppers+" are the toppers in "+subjects.get(4)+" with marks: "+bioMax);
    }
}
