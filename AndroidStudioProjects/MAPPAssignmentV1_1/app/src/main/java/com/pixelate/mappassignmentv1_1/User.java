package com.pixelate.mappassignmentv1_1;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

/**
 * Created by user on 15/6/2017.
 */

public class User {
    public String userID;
    public String name;
    public String email;
    public String address;
    public String contactNo;
    public String workphone;
    //public LatLng location;
    public Date lastlogin;
    public Post[] posts;
    public User[] friends;
    public String profilePictureUrl;

}

/*class Friend extends User{
    public boolean sharedContact;
}*/
