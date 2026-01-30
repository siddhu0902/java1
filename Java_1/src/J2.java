public class J2 {
    public static void main(String[] args){
        System.out.println("Mathematical tables");
        String s1 = "";
        for(int j=3;j<6;j++){
            for(int i=1;i<11;i++){
                s1 = s1+j+"*"+i+"="+(j*i)+"\n";
            }
            s1=s1+"\n";
        }

        for(int i=1;i<11;i++){
            s1 = s1+4+"*"+i+"="+(4*i)+"\n";
        }
        s1=s1+"\n";
        for(int i=1;i<11;i++){
            s1 = s1+5+"*"+i+"="+(5*i)+"\n";
        }
        s1=s1+"\n";
        System.out.println(s1);
    }
}
