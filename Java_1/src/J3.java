public class J3 {
    public static void main(String[] args){
        System.out.println("Reversing a string");
        String s1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String s2 = "";
        int len1=s1.length();
        for(int i=1;i<len1+1;i++){
            s2 = s2+s1.substring(len1-i,len1+1-i);
        }
        System.out.println(s2);
    }
}
