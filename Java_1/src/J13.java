import java.util.ArrayList;
import java.util.List;

public class J13 {
    public static void main(String[] args){
        System.out.println("Andaman Prisoner3");
        int count=100;
        List<Character> prisons=new ArrayList<>();
        List<Integer> lucky=new ArrayList<>();
        for(int i=0;i<count;i++){
            prisons.add(i,'C');
        }
        for (int i=0;i<count;i++){
            prisons.set(i,'O');
        }

        for(int i=1;i<count;i=i+2){
            prisons.set(i,'C');
        }
        for (int j=2;j<count;j++){
            for(int i=j;i<count;i=i+j+1){
                if (prisons.get(i)=='C'){
                    prisons.set(i,'O');
                }else{
                    prisons.set(i,'C');
                }
            }
        }
        for (int i=0;i<count;i++){
            if (prisons.get(i)=='O'){
                lucky.add(i+1);
            }
        }
        System.out.println(lucky+" are the lucky prisoners");
    }
}
