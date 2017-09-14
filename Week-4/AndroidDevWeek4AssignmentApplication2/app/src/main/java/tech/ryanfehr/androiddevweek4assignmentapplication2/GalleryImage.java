package tech.ryanfehr.androiddevweek4assignmentapplication2;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Ryan on 9/13/2017.
 */

class GalleryImage implements Serializable {
    private int imageID;
    private int imageTileID;
    private String title;
    private String description;
    private Date creationDate;
    private int height;
    private int width;

    public GalleryImage(Context context, int imageID, String title, String description, Date creationDate) {
        this.imageID = imageID;
        this.setImageTileID(imageID);
        this.title = title;
        this.description = description;
        this.creationDate = creationDate;
        BitmapDrawable bd = (BitmapDrawable) context.getDrawable(imageID);
        this.height = bd.getBitmap().getHeight();
        this.width = bd.getBitmap().getWidth();
    }

    public static float pxFromDp(final Context context, final float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public int getImageTileID() {
        return imageTileID;
    }

    public void setImageTileID(int imageTileID) {
        switch (imageTileID) {
            case R.drawable.great_wave_off_kanagawa:
                this.imageTileID = R.drawable.great_wave_off_kanagawa_tile;
                break;
            case R.drawable.mona_lisa:
                this.imageTileID = R.drawable.mona_lisa_tile;
                break;
            case R.drawable.starry_night:
                this.imageTileID = R.drawable.starry_night_tile;
                break;
            case R.drawable.the_last_supper:
                this.imageTileID = R.drawable.the_last_supper_tile;
                break;
            case R.drawable.the_scream:
                this.imageTileID = R.drawable.the_scream_tile;
                break;
            default:
                this.imageTileID = R.drawable.starry_night_tile;
                break;
        }

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
