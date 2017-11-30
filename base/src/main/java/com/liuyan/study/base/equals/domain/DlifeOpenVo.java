package com.liuyan.study.base.equals.domain;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by liuyan on 2017/11/17.
 */
public class DlifeOpenVo {
    private String vin;    //车辆VIN码
    private long aid;//	车主账号aid
    private String customerName;         //车主姓名
    private String sex;      //性别 : F/女，M/男，UN/未知
    private String idNumber;      //证件号
    private String idType;      //证件类型
    private String mobile;//车主手机号
    private String engineNo; //车辆引擎号
    private String vehNum;       //车牌号
    private String vehType;   //车型
    private String pinCode;  //pin码
    private String dealerCode;    //经销商code
    private String dealerName;     //经销商名称
    private String orderNum;   //订单号
    private String packageId;     //套餐id
    private String packageName;     //套餐名
    private String linkMan;     //紧急联系人
    private String linkManMobile;      //紧急联系人电话
    private String iccid;     //sim卡ICCID
    private String simPhoneNumber;     //sim卡号
    private String imsi;       //sim卡imsi
    private String tboxSn;       //tbox的SN
    private String imei;     //tbox的IMEI
    private String avnSn;   //avn（车机）的SN

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public long getAid() {
        return aid;
    }

    public void setAid(long aid) {
        this.aid = aid;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getVehNum() {
        return vehNum;
    }

    public void setVehNum(String vehNum) {
        this.vehNum = vehNum;
    }

    public String getVehType() {
        return vehType;
    }

    public void setVehType(String vehType) {
        this.vehType = vehType;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getLinkMan() {
        return linkMan;
    }

    public void setLinkMan(String linkMan) {
        this.linkMan = linkMan;
    }

    public String getLinkManMobile() {
        return linkManMobile;
    }

    public void setLinkManMobile(String linkManMobile) {
        this.linkManMobile = linkManMobile;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getSimPhoneNumber() {
        return simPhoneNumber;
    }

    public void setSimPhoneNumber(String simPhoneNumber) {
        this.simPhoneNumber = simPhoneNumber;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public String getTboxSn() {
        return tboxSn;
    }

    public void setTboxSn(String tboxSn) {
        this.tboxSn = tboxSn;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getAvnSn() {
        return avnSn;
    }

    public void setAvnSn(String avnSn) {
        this.avnSn = avnSn;
    }

    public static void main(String[] args) {
        System.out.println(JSONObject.toJSONString(new DlifeOpenVo()));
    }
}
