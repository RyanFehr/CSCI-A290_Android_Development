package tech.ryanfehr.androiddevweek4assignmentapplication2;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<GalleryImage> imageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        populateImageList();
        populateImageGalleryListView();
    }

    private void populateImageList() {
        imageList.add(new GalleryImage(this, R.drawable.mona_lisa, "Mona Lisa", "World's most famous painting", new Date()));
        imageList.add(new GalleryImage(this, R.drawable.starry_night, "Starry Night", "World's second most famous painting", new Date()));
        imageList.add(new GalleryImage(this, R.drawable.the_last_supper, "The Last Supper", "World's third most famous painting", new Date()));
        imageList.add(new GalleryImage(this, R.drawable.the_scream, "The Scream", "Terrifyingly shocking", new Date()));
        imageList.add(new GalleryImage(this, R.drawable.great_wave_off_kanagawa, "Great Wave off Kanagawa", "One of my personal favorites", new Date()));
    }

    private void populateImageGalleryListView() {
        ArrayAdapter<GalleryImage> contactArrayAdapter = new GalleryImageAdaptor(imageList);
        ListView imageGalleryListView = (ListView) findViewById(R.id.imageGalleryListView);
        imageGalleryListView.setAdapter(contactArrayAdapter);

        // Setup onClick listener for imageGalleryListView items
        imageGalleryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Build an intent
                Intent intent = new Intent(MainActivity.this, ImageDetails.class);
                intent.putExtra("IMAGE", imageList.get(position));
                // Call the Image Details activity with the newly created intent
                startActivityForResult(intent, 40);
            }
        });
    }

    private class GalleryImageAdaptor extends ArrayAdapter<GalleryImage> {

        public GalleryImageAdaptor(List<GalleryImage> imageList) {
            super(MainActivity.this, R.layout.gallery_image_layout, imageList);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View viewItem = convertView;
            if(viewItem == null) {
                viewItem = getLayoutInflater().inflate(R.layout.gallery_image_layout, parent, false);
            }
            // get the current view
            GalleryImage currentGalleryImage = imageList.get(position);

            ImageView imageView = (ImageView) viewItem.findViewById(R.id.imageView);
            imageView.setImageResource(currentGalleryImage.getImageTileID());
            TextView titleTextView = (TextView) viewItem.findViewById(R.id.titleTextView);
            titleTextView.setText(currentGalleryImage.getTitle());

            return viewItem;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_CANCELED) {
            return;
        }

        switch(requestCode) {
            case 40:
                break;
        }
    }
}
