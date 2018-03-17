package com.pixelate.mappassignmentv1_1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import static com.pixelate.mappassignmentv1_1.R.id.setDisplayNameBtn;
import static com.pixelate.mappassignmentv1_1.R.id.setDisplayNameInput;

/**
 * Created by user on 15/6/2017.
 */

public class SetDisplayName extends Activity {

    private EditText dn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Launch the layout -> splashx.xml
        setContentView(R.layout.setdisplayname);


        Button confirm =
                (Button)findViewById(setDisplayNameBtn);
        dn = (EditText)findViewById(setDisplayNameInput);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null){
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(dn.getText().toString()).build();
                    user.updateProfile(profileUpdates);
                }
                Intent i = new Intent(SetDisplayName.this, ChatRoom.class);
                startActivity(i);
            }
        });
    }
}
