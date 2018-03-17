package mapp.com.sg.myphotocollection;

/**
 * Created by francisyzy on 16/8/17.
 */

//package mapp.com.sg.myphotocollection;

public class ImageItem {

    private int imageID;
    private String desc;

    public ImageItem(int imageID, String desc) {
        this.imageID = imageID;
        this.desc = desc;
    }

    public int getImageResource() {
        return imageID;
    }

    public String getDesc() {
        return desc;
    }

    public void setImageResource(int imageID){
        this.imageID = imageID;
    }

    public void setDesc(String desc){
        this.desc = desc;
    }

}
