package com.pixelate.mappassignmentv1_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by caeden on 3/8/17.
 */

public class ReadPost extends AppCompatActivity {

    private ImageView profilepic;
    private TextView title,content,ownername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_post_page);

        profilepic = (ImageView) findViewById(R.id.readPost_OwnerPicture);
        title = (TextView) findViewById(R.id.readPost_PostTitle);
        content = (TextView) findViewById(R.id.readPost_PostText);
        ownername = (TextView) findViewById(R.id.readPost_OwnerName);

        Post p = (Post) getIntent().getSerializableExtra("Post");

        //Post p = (Post)k;
        title.setText(p.title);
        content.setText(p.text);
        ownername.setText(p.owner);
    }
}
