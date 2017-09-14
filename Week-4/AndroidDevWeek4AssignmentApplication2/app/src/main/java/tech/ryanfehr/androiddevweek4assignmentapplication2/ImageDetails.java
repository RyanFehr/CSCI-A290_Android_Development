package tech.ryanfehr.androiddevweek4assignmentapplication2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_details);

        Intent intent = getIntent();
        GalleryImage galleryImage = (GalleryImage) intent.getSerializableExtra("IMAGE");
        TextView titleTextView = (TextView) findViewById(R.id.titleTextView);
        titleTextView.setText("Title: " + galleryImage.getTitle());
        TextView descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        descriptionTextView.setText("Description: " + galleryImage.getDescription());
        TextView creationDateTextView = (TextView) findViewById(R.id.creationDateTextView);
        creationDateTextView.setText("Creation Date: " + galleryImage.getCreationDate().toString());
        TextView widthTextView = (TextView) findViewById(R.id.widthTextView);
        widthTextView.setText("Width: " + galleryImage.getWidth()+"px");
        TextView heightTextView = (TextView) findViewById(R.id.heightTextView);
        heightTextView.setText("Height: " + galleryImage.getHeight()+"px");
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(galleryImage.getImageID());
    }
}
