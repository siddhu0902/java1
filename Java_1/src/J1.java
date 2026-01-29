public class J1 {
    public static void calc1(int num1,int num2){
        String s1 = "";
        int sum1 = num1+num2;
        int dif1 = num1-num2;
        int mul1 = num1*num2;
        int div1 = num1/num2;
        int rem1 = num1%num2;
        double exp1 = Math.pow(num1,num2);
        s1 = sum1+","+dif1+","+mul1+","+div1+","+rem1+","+exp1;
        System.out.println(s1);
    }
    public static void main(String[] args){
        calc1(8,4);
        calc1(8,5);
        calc1(10,2);

    }

}
