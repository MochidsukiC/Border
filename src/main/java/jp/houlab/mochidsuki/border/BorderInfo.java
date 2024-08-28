package jp.houlab.mochidsuki.border;

import org.bukkit.entity.BlockDisplay;

public class BorderInfo {
    static private double nowPX;
    static private double nowMX;
    static private double nowPZ;
    static private double nowMZ;
    static private double nowCenterX;
    static private double nowCenterZ;
    static private double targetPX;
    static private double targetMX;
    static private double targetPZ;
    static private double targetMZ;
    static private double nowRadius; //リアルタイムな半径m

    static public double getNowPX(){return nowPX;}
    static public double getNowMX(){return nowMX;}
    static public double getNowPZ(){return nowPZ;}
    static public double getNowMZ(){return nowMZ;}

    static public double getTargetPX(){return targetPX;}
    static public double getTargetMX(){return targetMX;}
    static public double getTargetPZ(){return targetPZ;}
    static public double getTargetMZ(){return targetMZ;}

    static public double getNowCenterX(){return nowCenterX;}
    static public double getNowCenterZ(){return nowCenterZ;}

    static public double getNowRadius(){return nowRadius;}


    static public void setNowPX(double nowPX){BorderInfo.nowPX = nowPX;}

    static public void setNowMX(double nowMX){BorderInfo.nowMX = nowMX;}

    static public void setNowPZ(double nowPZ){BorderInfo.nowPZ = nowPZ;}

    static public void setNowMZ(double nowMZ){BorderInfo.nowMZ = nowMZ;}

    static public void setTargetPX(double targetPX) {BorderInfo.targetPX = targetPX;}
    static public void setTargetMX(double targetMX) {BorderInfo.targetMX = targetMX;}
    public static void setTargetPZ(double targetPZ) {BorderInfo.targetPZ = targetPZ;}
    static public void setTargetMZ(double targetMZ) {BorderInfo.targetMZ = targetMZ;}

    static public void setNowCenterX(double centerX) {BorderInfo.nowCenterX = centerX;}
    static public void setNowCenterZ(double centerZ) {BorderInfo.nowCenterZ = centerZ;}

    static public void setNowRadius(double radius) {BorderInfo.nowRadius = radius;}



}
