package tech.ryanfehr.androiddevclass8application;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements FragmentPortrait.OnColorChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Add Fragment Manager
        FragmentManager fragmentManager = getFragmentManager();

        // Setup Transaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Display chosen fragment
        Configuration configuration = getResources().getConfiguration();
        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            FragmentPortrait fragmentPortrait = new FragmentPortrait();
            fragmentTransaction.replace(R.id.myLinearLayout, fragmentPortrait);
        } else {
            FragmentLandscape fragmentLandscape = new FragmentLandscape();
            fragmentTransaction.replace(R.id.myLinearLayout, fragmentLandscape);
        }

        // Commit transaction
        fragmentTransaction.commit();

        Button mainButton = (Button) findViewById(R.id.mainButton);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add Fragment Manager
                FragmentManager fragmentManager = getFragmentManager();

                // Setup Transaction
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                // Display chosen fragment
                FragmentPortraitBig fragmentPortraitBig = new FragmentPortraitBig();
                fragmentTransaction.replace(R.id.myLinearLayout, fragmentPortraitBig);

                // Commit transaction
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public void colorChanged(String colorName) {
        TextView mainTextView = (TextView) findViewById(R.id.mainTextView);
        mainTextView.setText(colorName);
    }
}
