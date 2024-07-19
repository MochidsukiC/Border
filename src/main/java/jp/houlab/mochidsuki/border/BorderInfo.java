package jp.houlab.mochidsuki.border;

public class BorderInfo {
    static private double nowPX;
    static private double nowMX;
    static private double nowPZ;
    static private double nowMZ;
    static private double centerX;
    static private double centerZ;

    static public double getNowPX(){return nowPX;}
    static public double getNowMX(){return nowMX;}
    static public double getNowPZ(){return nowPZ;}
    static public double getNowMZ(){return nowMZ;}

    static public double getCenterX(){return centerX;}
    static public double getCenterZ(){return centerZ;}

    static public void setNowPX(double nowPX){BorderInfo.nowPX = nowPX;}

    static public void setNowMX(double nowMX){BorderInfo.nowMX = nowMX;}

    static public void setNowPZ(double nowPZ){BorderInfo.nowPZ = nowPZ;}

    static public void setNowMZ(double nowMZ){BorderInfo.nowMZ = nowMZ;}

    static public void setCenterX(double newCenterX){BorderInfo.centerX = newCenterX;}
    static public void setCenterZ(double newCenterZ){BorderInfo.centerZ = newCenterZ;}
}
