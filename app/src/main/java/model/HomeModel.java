package model;

/**
 * Created by wuyongmin on 16/6/17.
 */
public class HomeModel {

    private String ivUserImg;
    private String tvUserName;
    private String tvDepartment;
    private String tvJob;
    private String tvProfile;


    public String getTvJob() {
        return tvJob;
    }

    public void setTvJob(String tvJob) {
        this.tvJob = tvJob;
    }

    public String getIvUserImg() {
        return ivUserImg;
    }

    public void setIvUserImg(String ivUserImg) {
        this.ivUserImg = ivUserImg;
    }

    public String getTvUserName() {
        return tvUserName;
    }

    public void setTvUserName(String tvUserName) {
        this.tvUserName = tvUserName;
    }

    public String getTvDepartment() {
        return tvDepartment;
    }

    public void setTvDepartment(String tvDepartment) {
        this.tvDepartment = tvDepartment;
    }

    public String getTvProfile() {
        return tvProfile;
    }

    public void setTvProfile(String tvProfile) {
        this.tvProfile = tvProfile;
    }


    public HomeModel (String ivUserImg,String tvUserName,String tvDepartment,String tvJob,String tvProfile) {

        setIvUserImg(ivUserImg);
        setTvUserName(tvUserName);
        setTvDepartment(tvDepartment);
        setTvJob(tvJob);
        setTvProfile(tvProfile);

    }

    public HomeModel (String tvUserName,String tvDepartment,String tvJob,String tvProfile) {

        setTvUserName(tvUserName);
        setTvDepartment(tvDepartment);
        setTvJob(tvJob);
        setTvProfile(tvProfile);
    }


}
