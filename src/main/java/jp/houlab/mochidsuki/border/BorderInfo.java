package jp.houlab.mochidsuki.border;

import org.bukkit.entity.BlockDisplay;

/**
 * ボーダーの情報を格納するクラス
 * @author Mochidsuki
 */
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

    /**
     * 現在のXプラス方向の壁の現在位置を取得
     * @return x座標
     */
    static public double getNowPX(){return nowPX;}

    /**
     * 現在のXマイナス方向の壁の現在位置を取得
     * @return x座標
     */
    static public double getNowMX(){return nowMX;}

    /**
     * 現在のZプラス方向の壁の現在位置を取得
     * @return z座標
     */
    static public double getNowPZ(){return nowPZ;}

    /**
     * 現在のZマイナス方向の壁の現在位置を取得
     * @return z座標
     */
    static public double getNowMZ(){return nowMZ;}

    /**
     * ボーダー予測線のXプラス方向の線の位置を取得
     * @return x座標
     */
    static public double getTargetPX(){return targetPX;}

    /**
     * ボーダー予測線のXマイナス方向の線の位置を取得
     * @return x座標
     */
    static public double getTargetMX(){return targetMX;}

    /**
     * ボーダー予測線のZプラス方向の線の位置を取得
     * @return z座標
     */
    static public double getTargetPZ(){return targetPZ;}

    /**
     * ボーダー予測線のZマイナス方向の線の位置を取得
     * @return z座標
     */
    static public double getTargetMZ(){return targetMZ;}

    /**
     * 現在のボーダーの中心座標のx座標を取得
     * @return x座標
     */
    static public double getNowCenterX(){return nowCenterX;}

    /**
     * 現在のボーダーの中心座標のz座標を取得
     * @return z座標
     */
    static public double getNowCenterZ(){return nowCenterZ;}

    /**
     * 現在のボーダーの半径を取得
     * @return 半径
     */
    static public double getNowRadius(){return nowRadius;}

    /**
     * 現在のXプラス方向の壁の現在位置を設定
     * @param nowPX x座標
     */
    static public void setNowPX(double nowPX){BorderInfo.nowPX = nowPX;}

    /**
     * 現在のXマイナス方向の壁の現在位置を設定
     * @param nowMX x座標
     */
    static public void setNowMX(double nowMX){BorderInfo.nowMX = nowMX;}

    /**
     * 現在のZプラス方向の壁の現在位置を設定
     * @param nowPZ z座標
     */
    static public void setNowPZ(double nowPZ){BorderInfo.nowPZ = nowPZ;}

    /**
     * 現在のZマイナス方向の壁の現在位置を設定
     * @param nowMZ z座標
     */
    static public void setNowMZ(double nowMZ){BorderInfo.nowMZ = nowMZ;}

    /**
     * ボーダー予測線のXプラス方向の線の位置を設定
     * @param targetPX x座標
     */
    static public void setTargetPX(double targetPX) {BorderInfo.targetPX = targetPX;}

    /**
     * ボーダーの予測線のXマイナス方向の位置を設定
     * @param targetMX x座標
     */
    static public void setTargetMX(double targetMX) {BorderInfo.targetMX = targetMX;}

    /**
     * ボーダーの予測線のZプラス方向の位置を設定
     * @param targetPZ z座標
     */
    public static void setTargetPZ(double targetPZ) {BorderInfo.targetPZ = targetPZ;}

    /**
     * ボーダーの予測線のZマイナス方向の位置を設定
     * @param targetMZ z座標
     */
    static public void setTargetMZ(double targetMZ) {BorderInfo.targetMZ = targetMZ;}

    /**
     * 現在のボーダーの中心座標のx座標を設定
     * @param centerX x座標
     */
    static public void setNowCenterX(double centerX) {BorderInfo.nowCenterX = centerX;}

    /**
     * 現在のボーダーの中心座標のz座標の設定
     * @param centerZ z座標
     */
    static public void setNowCenterZ(double centerZ) {BorderInfo.nowCenterZ = centerZ;}

    /**
     * 現在のボーダーの半径を設定
     * @param radius 半径
     */
    static public void setNowRadius(double radius) {BorderInfo.nowRadius = radius;}



}
