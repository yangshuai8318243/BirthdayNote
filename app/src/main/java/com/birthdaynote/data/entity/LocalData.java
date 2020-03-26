package com.birthdaynote.data.entity;

public class LocalData {
    /**
     * 国家名称
     */
    String countryName;
    /**
     * 省
     */
    String adminArea;
    /**
     * 城市名称
     */
    String locality;
    /**
     *
     */
    String addressLine;
    /**
     * 周边信息
     */
    String featureName;

    /**
     * 获取时间戳
     */
    long getTime = -1;

    public long getGetTime() {
        return getTime;
    }

    public void setGetTime(long getTime) {
        this.getTime = getTime;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getAdminArea() {
        return adminArea;
    }

    public void setAdminArea(String adminArea) {
        this.adminArea = adminArea;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    @Override
    public String toString() {
        return "LocalData{" +
                "countryName='" + countryName + '\'' +
                ", adminArea='" + adminArea + '\'' +
                ", locality='" + locality + '\'' +
                ", addressLine='" + addressLine + '\'' +
                ", featureName='" + featureName + '\'' +
                '}';
    }
}
