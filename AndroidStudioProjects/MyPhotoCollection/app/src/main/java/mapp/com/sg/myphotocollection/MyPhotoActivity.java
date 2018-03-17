package mapp.com.sg.myphotocollection;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class MyPhotoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_photo);

        GridView gridView = (GridView) findViewById(R.id.gridview);
        ImageAdapter imageAdapter = new ImageAdapter(this, imageItems);
        gridView.setAdapter(imageAdapter);
    }

    private ImageItem[] imageItems = {
            new ImageItem(R.drawable.image_1, "pic1"),
            new ImageItem(R.drawable.image_2, "pic2"),
            new ImageItem(R.drawable.image_3, "pic3"),
            new ImageItem(R.drawable.image_4, "pic4"),
            new ImageItem(R.drawable.image_5, "pic5"),
            new ImageItem(R.drawable.image_6, "pic6"),
            new ImageItem(R.drawable.image_7, "pic7"),
            new ImageItem(R.drawable.image_8, "pic8"),
            new ImageItem(R.drawable.image_9, "pic9"),
    };
    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Toast.makeText(getApplicationContext(), "Image position " + position,
                    Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), DisplayIndImage.class);

            //Get the selected Item id and description
            int imgID = imageItems[position].getImageResource();
            String txtDesc = imageItems[position].getDesc();

            //Toast.makeText(getApplicationContext(), "Image resources " + imgID,
            //        Toast.LENGTH_LONG).show();
            // Pass image index to another Intent

            i.putExtra("imgResc",imgID);
            i.putExtra("txtDesc", txtDesc);
            startActivity(i);


        }
    });

}


