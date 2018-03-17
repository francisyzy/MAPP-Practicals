package mapp.com.sg.mymobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainImageActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_image);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.about:
                // Do Something
                Toast.makeText(getApplicationContext(),"About...",
                        Toast.LENGTH_SHORT).show();
                return true;
            case R.id.help:
                // Do Something
                Toast.makeText(getApplicationContext(),"Help...",
                        Toast.LENGTH_SHORT).show();

                return true;
            case R.id.chkupd:
                // Do Something
                Toast.makeText(getApplicationContext(),"Check for Update...",
                        Toast.LENGTH_SHORT).show();

                return true;

            case R.id.pref:
                // Do Something
                startActivity(new Intent(this, Prefs.class));
                return true;
        }
        return false;

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.photoAlbum:
                Intent i = new Intent(this, PhotoAlbum.class);
                startActivity(i);
                break;
        }

    }
}
