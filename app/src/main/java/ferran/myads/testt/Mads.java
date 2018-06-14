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





        MyAds.Show(Mads.this);
// modificacio
       Button btn = (Button)findViewById(R.id.button);

       btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               MyAds.Show(Mads.this);

           }
       });


    }

    @Override
    public void onBackPressed() {
        MyAds.Show(this);
        super.onBackPressed();
    }
}
