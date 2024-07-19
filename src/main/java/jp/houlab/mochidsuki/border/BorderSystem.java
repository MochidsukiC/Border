package jp.houlab.mochidsuki.border;

import java.util.Random;


import static jp.houlab.mochidsuki.border.V.*;

public class BorderSystem {
    public static void run(){

        /*
        double[] speed = new double[4];
        double radius=0;
        double radiusk=0;
        center[1] = (int)(now[1] + (now[0]- now[1])/2);
        center[2] = (int)(now[3] + (now[2]- now[3])/2);
        int rnd = 9;
        switch (gameround){
            case 1:
                radius = mr;
                radiusk = 0.5;
                center[1] = mcx;
                center[2] = mcz;
                rtime = roundrtime[1];
                stime = roundstime[1];
                now[0] = mcx+ mr;
                now[1] = mcx- mr;
                now[2] = mcz+ mr;
                now[3] = mcz- mr;
                break;
            case 2:
                radius = (int)(mr * 0.5);
                radiusk = 0.65;
                rtime = roundrtime[2];
                stime = roundstime[2];
                break;
            case 3:
                radius = (int)(mr * 0.5*0.65);
                radiusk = 0.6;
                rtime = roundrtime[3];
                stime = roundstime[3];
                rnd = 5;
                break;
            case 4:
                radius = (int)(mr * 0.5*0.65*0.6);
                radiusk = 0.3;
                rtime = roundrtime[4];
                stime = roundstime[4];
                rnd = 5;
                break;
            case 5:
                radius = (int)(mr * 0.5*0.65*0.6*0.5);
                radiusk = 0.3;
                rtime = roundrtime[5];
                stime = roundstime[5];
                rnd = 5;
                break;
            case 6:
                radius = (int)(mr * 0.5*0.65*0.6*0.5*0.5);
                radiusk = 0;
                rtime = roundrtime[6];
                stime = roundstime[6];
                rnd = 5;
                break;
        }
        Random random = new Random();
        center[0] = random.nextInt(rnd);
        switch ((int)center[0]){
            case 0:
                break;
            case 1:
                center[1] = (int)(center[1] - radius*(1-radiusk));
                break;
            case 2:

                center[2] = (int)(center[2] + radius*(1-radiusk));
                break;
            case 3:
                center[1] = (int)(center[1] + radius*(1-radiusk));
                break;
            case 4:
                center[2] = (int)(center[2] - radius*(1-radiusk));
                break;
            case 5:
                center[1] = (int)(center[1] - radius*(1-radiusk));
                center[2] = (int)(center[2] + radius*(1-radiusk));
                break;
            case 6:
                center[1] = (int)(center[1] + radius*(1-radiusk));
                center[2] = (int)(center[2] + radius*(1-radiusk));

                break;
            case 7:
                center[1] = (int)(center[1] + radius*(1-radiusk));
                center[2] = (int)(center[2] - radius*(1-radiusk));
                break;
            case 8:
                center[1] = (int)(center[1] - radius*(1-radiusk));
                center[2] = (int)(center[2] - radius*(1-radiusk));
                break;
        }
        B.target[0] = (int)(center[1] + radius*radiusk);
        B.target[1] = (int)(center[1] - radius*radiusk);
        B.target[2] = (int)(center[2] + radius*radiusk);
        B.target[3] = (int)(center[2] - radius*radiusk);

        speed[0] = (B.target[0] - now[0])/ rtime/20;
        speed[1] = (B.target[1] - now[1])/rtime/20;
        speed[2] = (B.target[2] - now[2])/rtime/20;
        speed[3] = (B.target[3] - now[3])/rtime/20;

         */
    }
}
