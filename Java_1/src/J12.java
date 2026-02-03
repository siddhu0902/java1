import java.util.ArrayList;
import java.util.List;

public class J12 {
    public static void main(String[] args){
        System.out.println("Andaman Prisoner2");
        List<Character> prisons=new ArrayList<>();
        List<Integer> lucky=new ArrayList<>();
        for(int i=0;i<10;i++){
            prisons.add(i,'C');
        }
        System.out.println("Open: "+prisons);
        for (int i=0;i<10;i++){
            prisons.set(i,'O');
        }
        System.out.println("R1  : "+prisons);

        for(int i=1;i<10;i=i+2){
            prisons.set(i,'C');
        }
        System.out.println("R2  : "+prisons);
        for (int j=2;j<10;j++){
            for(int i=j;i<10;i=i+j+1){
                if (prisons.get(i)=='C'){
                    prisons.set(i,'O');
                }else{
                    prisons.set(i,'C');
                }
            }
            System.out.println("R"+(j+1)+"  : "+prisons);
        }
        for (int i=0;i<10;i++){
            if (prisons.get(i)=='O'){
                lucky.add(i+1);
            }
        }
        System.out.println(lucky+" are the lucky prisoners");
    }
}
