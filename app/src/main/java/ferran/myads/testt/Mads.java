package ferran.myads.testt;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.ferran.myadslib.volley.MyAds;

public class Mads extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mads);

        //Load the ad
        MyAds.Load(this);


        Button btn = (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Show the ad
                MyAds.ShowInterstitial(Mads.this);

            }
        });


        // Load and show the ad at the same time
        // MyAds.LoadAndShowSplash(this);


    }

    @Override
    public void onBackPressed() {
        MyAds.ShowInterstitial(this);
        super.onBackPressed();
    }
}
