public class J3 {
    public static String reverse1(String s1){
        String s2 = "";
        int len1=s1.length();
        for(int i=1;i<len1+1;i++){
            s2 = s2+s1.substring(len1-i,len1+1-i);
        }
        return s2;
    }
    public static void main(String[] args){
        String name1="Siddharth";
        String name2="Chandrashekar";
        String name1_rev = reverse1(name1);
        String name2_rev = reverse1(name2);
        System.out.println(name1+"-"+name1_rev);
        System.out.println(name2+"-"+name2_rev);
    }
}
