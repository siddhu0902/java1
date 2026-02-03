import java.util.ArrayList;
import java.util.List;

public class J11 {
    public static void main(String[] args){
        System.out.println("Andaman Prisoner");
        List<Character> prisons=new ArrayList<>();
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
        for(int i=2;i<10;i=i+3){
            if (prisons.get(i)=='C'){
                prisons.set(i,'O');
            }else{
                prisons.set(i,'C');
            }
        }
        System.out.println("R3  : "+prisons);
        for (int i=3;i<10;i=i+4){
            if (prisons.get(i)=='C'){
                prisons.set(i,'O');
            }else{
                prisons.set(i,'C');
            }
        }
        System.out.println("R4  : "+prisons);
        for (int i=4;i<10;i=i+5){
            if (prisons.get(i)=='C'){
                prisons.set(i,'O');
            }else{
                prisons.set(i,'C');
            }
        }
        System.out.println("R5  : "+prisons);
    }
}
