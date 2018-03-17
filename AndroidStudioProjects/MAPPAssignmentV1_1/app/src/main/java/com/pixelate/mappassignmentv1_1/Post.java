package com.pixelate.mappassignmentv1_1;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by user on 15/6/2017.
 */

public class Post implements Serializable {
    public String owner;
    public String title;
    //private int type;
    //private String[] tags;
    public String text;
    //private Location location;
    //public LatLng location;
    public double latitude;
    public double longitude;
    public long posttime;
    //private Comment[] comments;

    public Post(){

    }

    public Post(String tl,String te, LatLng lo , String o){
        this.title = tl;
        //this.type = ty;
        //this.tags = tag;
        this.text = te;
        //this.lat = lo.latitude;
        //this.lng = lo.longitude;
        this.latitude = lo.latitude;
        this.longitude = lo.longitude;
        this.owner = o;

        this.posttime = new Date().getTime();
    }

@Override
    public String toString(){return title;}


}

/*class Location{
    private double lat;
    private double lng;

    Location(LatLng l){
        lat = l.latitude;
        lng = l.longitude;
    }

}*/
/*class Comment{
    private User owner;
    private Date date;
    private String comment;
    private Comment[] subcomments;
}*/

