package com.example.rwh;
import java.lang.*;
public class Calculations extends UrheiluActivity {

    public static double sport(String sport, String time,double met) {
        int aika =0;
        if (sport.equals("Kävely Birdwatching")) {
            if (time.equals("10 min")) {
                aika = 10;
            } else if (time.equals("20 min")) {
                aika = 20;
            } else if (time.equals("30 min")) {
                aika = 30;
            } else if (time.equals("40 min")) {
                aika = 40;
            } else if (time.equals("50 min")) {
                aika = 50;
            } else if (time.equals("60 min")) {
                aika = 60;
            }
            //2.5 met	bird watching, slow walk
            return (((met * 2.5) / 6) * aika);

        } else if (sport.equals("Hölkkä")) {
            if (time.equals("10 min")) {
                aika = 10;
            } else if (time.equals("20 min")) {
                aika = 20;
            } else if (time.equals("30 min")) {
                aika = 30;
            } else if (time.equals("40 min")) {
                aika = 40;
            } else if (time.equals("50 min")) {
                aika = 50;
            } else if (time.equals("60 min")) {
                aika = 60;
            }
            //7.0 met	jogging, general
            return (((met * 7.0) / 6) * aika);
        } else if (sport.equals("Juoksu")) {
            if (time.equals("10 min")) {
                aika = 10;
            } else if (time.equals("20 min")) {
                aika = 20;
            } else if (time.equals("30 min")) {
                aika = 30;
            } else if (time.equals("40 min")) {
                aika = 40;
            } else if (time.equals("50 min")) {
                aika = 50;
            } else if (time.equals("60 min")) {
                aika = 60;
            }
            //9.8	running, 6 mph (10 min/mile)
            return (((met * 9.8) / 6) * aika);
        } else if (sport.equals("PikaJuoksu")) {
            if (time.equals("10 min")) {
                aika = 10;
            } else if (time.equals("20 min")) {
                aika = 20;
            } else if (time.equals("30 min")) {
                aika = 30;
            } else if (time.equals("40 min")) {
                aika = 40;
            } else if (time.equals("50 min")) {
                aika = 50;
            } else if (time.equals("60 min")) {
                aika = 60;
            }
            // 19.0
            //running, 12 mph (5 min/mile)
            return (((met * 19.0) / 6) * aika);
        } else
        {
            return 0;
        }


    }
}
