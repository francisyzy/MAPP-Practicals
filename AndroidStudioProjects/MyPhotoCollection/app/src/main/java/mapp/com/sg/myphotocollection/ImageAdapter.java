package mapp.com.sg.myphotocollection;

/**
 * Created by francisyzy on 16/8/17.
 */


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {

    private final Context mContext;
    private ImageItem[] imageItems;

    // Constructor
    public ImageAdapter(Context context) {
        this.mContext = context;
    }

    // Constructor
    public ImageAdapter(Context context, ImageItem[] imageItems) {
        this.mContext = context;
        this.imageItems = imageItems;
    }

    // Count the number of image items
    @Override
    public int getCount() {
        return imageItems.length;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    // Display the image in the GridView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Display all the images
        final ImageItem imgdisplay = imageItems[position];

        // Place the iamage into the respective grid
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.imagedisplay, null);
        }

        final ImageView imageviewdisp = (ImageView)convertView.findViewById(R.id.imageviewdisplay);
        final TextView textviewdesc = (TextView)convertView.findViewById(R.id.txtviewdesc);

        // Place the image and description of the image
        imageviewdisp.setImageResource(imgdisplay.getImageResource());
        textviewdesc.setText(imgdisplay.getDesc());

        return convertView;
    }


}
