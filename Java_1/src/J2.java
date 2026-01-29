public class J2 {
    public static void main(String[] args){
        System.out.println("Mathematical tables");
        String s1 = "";
        for(int j=3;j<9;j++){
            for(int i=1;i<11;i++){
                s1 = s1+j+"*"+i+"="+(j*i)+"\n";
            }
            s1=s1+"\n";
        }

        System.out.println(s1);
    }
}
