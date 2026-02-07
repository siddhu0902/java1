public class J20 {
    public static void main(String[] args){
        String word = "ABCDEFGHIJK";
        int s=word.length();
        String part1="";
        String part2="";
        int len1=word.length();
        for (int j=1;j<len1+1;j++){
            part1="";
            for (int i=0;i<len1-j;i++){
                part1=part1+" ";
            }
            part2=word.substring(0,j);
            System.out.println(part1+part2);
        }
        for (int j=len1;j>=0;j--){
            part1="";
            for (int i=0;i<len1-j;i++){
                part1=part1+" ";
            }
            part2=word.substring(0,j);
            System.out.println(part1+part2);
        }
    }
}
