package mapp.com.sg.myphotocollection;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by francisyzy on 16/8/17.
 */

public class DisplayIndImage extends AppCompatActivity {
    private ImageItem[] imageItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_individual_image);

        // Get intent data
        Intent i = getIntent();

        // Selected image id
        int imgResource = i.getExtras().getInt("imgResc");
        String txtDesc = i.getExtras().getString("txtDesc");

        Toast.makeText(getApplicationContext(), "Image resc " + imgResource ,
                Toast.LENGTH_LONG).show();

        // Display the Image
        ImageView imageView = (ImageView) findViewById(R.id.individualview);
        imageView.setImageResource(imgResource);

        // Display the Text
        TextView txtView = (TextView) findViewById(R.id.textViewDesc);
        txtView.setText(txtDesc.toString());


    }
}
