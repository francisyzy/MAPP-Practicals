package mapp.com.sg.picgallery;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;


public class ImageGalleryActivity extends AppCompatActivity {
    private ImageSwitcher sw;
    private Button moveleft, moveright;

    // Place images into an array
    int imgIds[] ={R.drawable.p1,
            R.drawable.p2,
            R.drawable.p3,
            R.drawable.p4,
            R.drawable.p5,
            R.drawable.p6,
            R.drawable.p7};
    int imgCount = imgIds.length;
    int currentIndex = -1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_gallery);

        moveleft = (Button) findViewById(R.id.btnLeft);
        moveright = (Button) findViewById(R.id.btnRight);

        sw = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        sw.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView myView = new ImageView(getApplicationContext());
                myView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                myView.setLayoutParams(new
                        ImageSwitcher.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT,
                        ActionBar.LayoutParams.WRAP_CONTENT));
                return myView;
            }
        });

        moveleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex--;
                if (currentIndex == 0){
                    // Disable moveleft button when it is first pictures
                    moveleft.setEnabled(false) ;
                } else{
                    moveright.setEnabled(true) ;
                }

                sw.setImageResource(imgIds[currentIndex]);
                Toast.makeText(getApplicationContext(), "Previous Image " + currentIndex,
                        Toast.LENGTH_LONG).show();

            }
        });

        moveright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentIndex++;
                if (currentIndex == imgCount -1){
                    // Disable right button when it reach last image
                    moveright.setEnabled(false) ;
                } else{
                    moveleft.setEnabled(true) ;
                }
                Toast.makeText(getApplicationContext(), "Next Image " + currentIndex,
                        Toast.LENGTH_LONG).show();
                sw.setImageResource(imgIds[currentIndex]);

            }
        });


    }
}

