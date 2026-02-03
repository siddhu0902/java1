public class J10 {
    public static void main(String[] args)
    {
        for (int hour=9;hour<=20;hour++) {
            for (int minute=0;minute<60;minute+=5) {
                if (hour==20 && minute>55)           // till 8:55
                    break;
                double minuteAngle = minute*6;       //(360/60)=6
                double hourAngle = (hour%12)*30+minute*0.5;
                double angle = Math.abs(hourAngle-minuteAngle);
                if (angle>180) {           // Dividing the clock into two equal halves
                    angle=360-angle;       // not letting the angle surpass 180
                }
                String time;
                time=hour+":"+String.format("%02d",minute);  //print as 9:05 instead of 9:5
                System.out.println(time+" - "+angle+" degrees");
            }
            System.out.println();
        }
    }
}

