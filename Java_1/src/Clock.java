public class Clock {
        public static void main(String[] args) {
            for (int hour=9;hour<=20;hour++) {
                for (int minute=0;minute<60;minute+=5) {
                    if (hour==20 && minute>55)
                        break;
                    double minuteAngle = minute*6;
                    double hourAngle = (hour%12)*30+minute*0.5;
                    double angle = Math.abs(hourAngle-minuteAngle);
                    if (angle>180) {
                        angle=360-angle;
                    }
                    String time;

                    if (minute < 10)
                        time = hour+":0"+minute;
                    else
                        time = hour+":"+minute;

                    System.out.println(time+" - "+angle+" degrees");
                }
                System.out.println();
            }
        }
}
