package model;

/**
 * Created by wuyongmin on 16/6/17.
 */
public class HomeModel {


    private  String title;

    private  String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public HomeModel (String title) {

        setTitle(title);

    }

}
