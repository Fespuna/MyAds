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

        MyAds.Load(this);

        Button btn = (Button)findViewById(R.id.button);

       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               MyAds.ShowInterstitial(Mads.this);

           }
       });

    //    MyAds.LoadAndShowSplash(this);


    }

    @Override
    public void onBackPressed() {
        MyAds.ShowInterstitial(this);
        super.onBackPressed();
    }
}
