package mapp.com.sg.mydata;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.app.AlertDialog;

public class NewAccountActivity extends AppCompatActivity implements View.OnClickListener {

    private String pFName, pLName, pEmail, pUId, pPW,pMobileNo;
    private EditText edFName, edLName, edEmail, edUID, edPW, edMobileNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        edFName = (EditText) findViewById(R.id.txtFName);
        edLName = (EditText) findViewById(R.id.txtLName);
        edEmail = (EditText) findViewById(R.id.txtEmail);
        edMobileNo = (EditText) findViewById(R.id.txtTelNo);
        edUID = (EditText) findViewById(R.id.txtUsrName);
        edPW = (EditText) findViewById(R.id.txtPassword);

    }
    @Override
    public void onClick(View view) {
        // Collect all data from the EditTextBox
        pFName = edFName.getText().toString();
        pLName = edLName.getText().toString();
        pEmail = edEmail.getText().toString();
        pMobileNo = edMobileNo.getText().toString();
        pUId = edUID.getText().toString();
        pPW = edPW.getText().toString();

        switch (view.getId()){
            case R.id.btnRegister:

                // Check for empty data
                if (pFName.length() == 0){
                    // Alert user
                    // Creating Alert Dialogue
                    AlertDialog.Builder alertUser = new AlertDialog.Builder(this);
                    alertUser.setMessage("User Name and Password cannot be empty").setTitle("Alert");
                    AlertDialog alertDialog = alertUser.create();
                    alertDialog.setButton(DialogInterface.BUTTON_POSITIVE,"OK", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface alertDialog, int which){
                        }
                    });
                    alertDialog.show();
                    // End of Create Dialog
                }else{
                    Intent confirmPage = new Intent (this, ConfirmRegistrationActivity.class);
                    confirmPage.putExtra("strFName",pFName);
                    confirmPage.putExtra("strLName",pLName);
                    confirmPage.putExtra("strEmail",pEmail);
                    confirmPage.putExtra("strMobileNo",pMobileNo);
                    confirmPage.putExtra("strID",pUId);
                    confirmPage.putExtra("strP",pPW);
                    startActivity(confirmPage);
                }
                break;
        }
    }
}

