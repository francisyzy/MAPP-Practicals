package com.pixelate.mappassignmentv1_1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;

/**
 * Created by caeden on 2/8/17.
 */

public class WritePost extends AppCompatActivity implements Serializable{

    private FirebaseAuth auth;
    private ImageView profilepic;
    private EditText title,content;
    private Button post;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() == null) {
            startActivity(new Intent(WritePost.this, Login.class));
            finish();
        }

        setContentView(R.layout.write_post_page);

        FirebaseDatabase database = FirebaseDatabase.getInstance();



        profilepic = (ImageView)findViewById(R.id.writepost_profilepic);

        post = (Button)findViewById(R.id.writepost_post_btn);




        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = (EditText)findViewById(R.id.writepost_edit_title);
                content = (EditText)findViewById(R.id.writepost_edit_content);

                // Read the input field and push a new instance
                // of ChatMessage to the Firebase database
                Bundle bu = getIntent().getBundleExtra("bundle");
                LatLng location = bu.getParcelable("currentlocation");

                Post post = new Post(title.getText().toString(),
                        content.getText().toString(),
                        location,
                        FirebaseAuth.getInstance().getCurrentUser().getDisplayName());



                FirebaseDatabase.getInstance()
                        .getReference().child("Post")
                        .push()
                        .setValue(post);



                Intent p = new Intent(WritePost.this, MapDrawer.class);
                startActivity(p);

            }
        });


    }
}
