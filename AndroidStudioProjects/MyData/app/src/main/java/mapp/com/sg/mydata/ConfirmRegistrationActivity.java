package mapp.com.sg.mydata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmRegistrationActivity extends AppCompatActivity {

    private String dRow1, dRow2, dRow3, dRow4, dRow5, dRow6;
    TextView rtxt1,rtxt2,rtxt3,rtxt4,rtxt5,rtxt6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_registration);

        TextView rtxt1 = (TextView)findViewById(R.id.txtRow1);
        TextView rtxt2 = (TextView)findViewById(R.id.txtRow2);
        TextView rtxt3 = (TextView)findViewById(R.id.txtRow3);
        TextView rtxt4 = (TextView)findViewById(R.id.txtRow4);
        TextView rtxt5 = (TextView)findViewById(R.id.txtRow5);
        TextView rtxt6 = (TextView)findViewById(R.id.txtRow6);

        Bundle getData = getIntent().getExtras();

        if (getData != null){
            dRow1 = getData.getString("strFName");
            dRow2 = getData.getString("strLName");
            dRow3 = getData.getString("strEmail");
            dRow4 = getData.getString("strMobileNo");
            dRow5 = getData.getString("strID");
            dRow6 = getData.getString("strP");

            rtxt1.setText("First Name : " + dRow1 );
            rtxt2.setText("Last Name : " + dRow2 );
            rtxt3.setText("Email : " + dRow3 );
            rtxt4.setText("Mobile Number : " + dRow4 );
            rtxt5.setText("User ID : " + dRow5 );
            rtxt6.setText("Password : " + dRow6 );

        }else{
            Toast.makeText(getApplicationContext(),"Failed to get Data",Toast.LENGTH_LONG).show();
        }
    }
}

