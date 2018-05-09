package com.example.ferran.myadslib.volley;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.ferran.myadslib.R;

public class AdDisplay extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   //     requestWindowFeature(Window.FEATURE_NO_TITLE);
   //     this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ad_display);

        Intent intent = getIntent();
        final String adpackagei = intent.getStringExtra("adpackage");
        final String adnamei = intent.getStringExtra("adname");
        final String addescriptioni = intent.getStringExtra("addescription");
        final String adimagei = intent.getStringExtra("adimage");


        TextView adname = (TextView)findViewById(R.id.adname);
        TextView addescription = (TextView)findViewById(R.id.addescription);
        final ImageView adimage = (ImageView)findViewById(R.id.adimage);
        ImageView cross = (ImageView)findViewById(R.id.cross);
        LinearLayout adclick = (LinearLayout)findViewById(R.id.adclick);

        adname.setText(adnamei);
        addescription.setText(addescriptioni);

        Button btninstall = (Button)findViewById(R.id.btninstall);
        btninstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gomarkt(adpackagei);
            }
        });

        Glide.with(this).load(Urls.URL_AD_IMAGE + adimagei).asBitmap().centerCrop().into(new BitmapImageViewTarget(adimage) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(AdDisplay.this.getResources(), resource);

             //   circularBitmapDrawable.setCircular(true);
                adimage.setImageDrawable(circularBitmapDrawable);
            }
        });


        adclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             gomarkt(adpackagei);
            }
        });


        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    public void gomarkt(String adpackagei){
        Uri uri = Uri.parse("market://details?id=" + adpackagei);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        // To count with Play market backstack, After pressing back button,
        // to taken back to our application, we need to add following flags to intent.
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY |
                Intent.FLAG_ACTIVITY_NEW_DOCUMENT |
                Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        finish();
        try {
            startActivity(goToMarket);
            finish();
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://play.google.com/store/apps/details?id=" + adpackagei)));

        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
