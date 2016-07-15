package cn.iherb.bean;

import java.util.Map;

/**
 * Created by Paul on 2016/7/13.
 */
public class ProvinceCityDistrict {

    /*For example, the following*/
    private String areaCode;   //010
    private String provinceCN;  //北京市
    private String provinceEN;  //Beijing City
    private String cityCN;  //北京市
    private String cityEN;  //Beijing City
    private String zipStart;   //100000
    private String zipEnd; //100099
    private String districtCN;  //朝阳区
    private String districtEN;  //Chaoyang District

    public ProvinceCityDistrict() {
    }

    public ProvinceCityDistrict(Map<String, String> map) {
        setAreaCode(map.get("areaCode"));
        setProvinceCN(map.get("provinceCN"));
        setProvinceEN(map.get("provinceEN"));
        setCityCN(map.get("cityCN"));
        setCityEN(map.get("cityEN"));
        setZipStart(map.get("zipStart"));
        setZipEnd(map.get("zipEnd"));
        setDistrictCN(map.get("districtCN"));
        setDistrictEN(map.get("districtEN"));
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getProvinceCN() {
        return provinceCN;
    }

    public void setProvinceCN(String provinceCN) {
        this.provinceCN = provinceCN;
    }

    public String getProvinceEN() {
        return provinceEN;
    }

    public void setProvinceEN(String provinceEN) {
        this.provinceEN = provinceEN;
    }

    public String getCityCN() {
        return cityCN;
    }

    public void setCityCN(String cityCN) {
        this.cityCN = cityCN;
    }

    public String getCityEN() {
        return cityEN;
    }

    public void setCityEN(String cityEN) {
        this.cityEN = cityEN;
    }

    public String getZipStart() {
        return zipStart;
    }

    public void setZipStart(String zipStart) {
        this.zipStart = zipStart;
    }

    public String getZipEnd() {
        return zipEnd;
    }

    public void setZipEnd(String zipEnd) {
        this.zipEnd = zipEnd;
    }

    public String getDistrictCN() {
        return districtCN;
    }

    public void setDistrictCN(String districtCN) {
        this.districtCN = districtCN;
    }

    public String getDistrictEN() {
        return districtEN;
    }

    public void setDistrictEN(String districtEN) {
        this.districtEN = districtEN;
    }
}
