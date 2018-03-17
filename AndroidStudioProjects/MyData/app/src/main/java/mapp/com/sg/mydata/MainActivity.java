package mapp.com.sg.mydata;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPreferences prefs;
    EditText edUName, edPw;
    Button bLogin;
    public static final String MyPREFERNCES = "MyPrefs";
    public static final String UName = "unameKey";
    public static final String Upw = "upwKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edUName = (EditText)findViewById(R.id.txtUserID);
        edPw = (EditText)findViewById(R.id.txtPw);
        bLogin = (Button)findViewById(R.id.btnLogin);

        // Load the preferences
        prefs = getSharedPreferences(MyPREFERNCES, MODE_PRIVATE);
        String gUID = prefs.getString(UName,"");
        String gPw = prefs.getString(Upw,"");

        // Set the EditText view to the various saved values
        // Place the data into the respective EditText
        edUName.setText(gUID);
        edPw.setText(gPw);
    }

    @Override
    public void onPause(){
        super.onPause();

        edUName = (EditText)findViewById(R.id.txtUserID);
        edPw = (EditText)findViewById(R.id.txtPw);
        bLogin = (Button)findViewById(R.id.btnLogin);

        prefs = getSharedPreferences(MyPREFERNCES, MODE_PRIVATE);

        // Get the text from the EditText field
        String uID = edUName.getText().toString();
        String uPw = edPw.getText().toString();

        SharedPreferences.Editor editor = prefs.edit();

        // Store into the SharedPreferences
        editor.putString(UName,uID);
        editor.putString(Upw,uPw);
        editor.commit();

    }

    @Override
    public void onClick(View view) {
        edUName = (EditText)findViewById(R.id.txtUserID);
        edPw = (EditText)findViewById(R.id.txtPw);
        bLogin = (Button)findViewById(R.id.btnLogin);

        prefs = getSharedPreferences(MyPREFERNCES, MODE_PRIVATE);

        // Get the text from the EditText field
        String uID = edUName.getText().toString();
        String uPw = edPw.getText().toString();

        SharedPreferences.Editor editor = prefs.edit();

        // Store into the SharedPreferences
        editor.putString(UName,uID);
        editor.putString(Upw,uPw);
        editor.commit();

        // Button Click AddOn
        switch (view.getId()){
            case R.id.btnNewUser:
                Intent i = new Intent(this, NewAccountActivity.class);
                startActivity(i);
                break;
        }
        // End of Button Click

    }
}

