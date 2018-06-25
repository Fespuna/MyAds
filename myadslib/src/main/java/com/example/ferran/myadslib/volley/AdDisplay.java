package com.example.ferran.myadslib.volley;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.example.ferran.myadslib.R;

import java.util.Calendar;

public class AdDisplay extends Activity {

     String adname,adpackage,adicon,addescription,apppackage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
   //     requestWindowFeature(Window.FEATURE_NO_TITLE);
   //     this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_ad_display);

        Intent intent = getIntent();

        TextView aadname = (TextView)findViewById(R.id.adname);
        TextView aaddescription = (TextView)findViewById(R.id.addescription);
        final ImageView adimage = (ImageView)findViewById(R.id.adimage);
        ImageView cross = (ImageView)findViewById(R.id.cross);
        LinearLayout adclick = (LinearLayout)findViewById(R.id.adclick);



        if(intent.hasExtra("ad_name") && intent.hasExtra("ad_package")){

            adname = intent.getStringExtra("ad_name");
            adpackage = intent.getStringExtra("ad_package");
            adicon = intent.getStringExtra("ad_icon");
            addescription = intent.getStringExtra("ad_description");
            apppackage = intent.getStringExtra("app_package");

            Glide.with(this).load(Urls.URL_AD_IMAGE + adicon).asBitmap().centerCrop().into(new BitmapImageViewTarget(adimage) {
                @Override
                protected void setResource(Bitmap resource) {
                    RoundedBitmapDrawable circularBitmapDrawable =
                            RoundedBitmapDrawableFactory.create(AdDisplay.this.getResources(), resource);

                    //   circularBitmapDrawable.setCircular(true);
                    adimage.setImageDrawable(circularBitmapDrawable);
                }
            });


        }else{

            adname = Shared.get(this,"ad_name");
            adpackage = Shared.get(this,"ad_package");
            adicon = Shared.get(this,"ad_icon");
            addescription = Shared.get(this,"ad_description");
            apppackage = Shared.get(this,"app_package");

            Glide.with(AdDisplay.this)
                    .load( Urls.URL_AD_IMAGE + adicon )
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(adimage);



        }





        aadname.setText(adname);
        aaddescription.setText(addescription);

        Log.e("xixixi","ratatattat");
        DataUpdates.UpdateImpressions(AdDisplay.this);

       adclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("xixi","aaaa");

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, 300);

                //Create a new PendingIntent and add it to the AlarmManager
                Intent intent = new Intent(getApplicationContext(), ReceiveInstall.class);

                //  intent.putExtra(TelephonyManager.EXTRA_STATE, "AQUI AGREGA EL VALOR");
                intent.putExtra("packa",adpackage);
                intent.putExtra("apppacka",apppackage);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),331,intent,PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                        pendingIntent);



                DataUpdates.UpdateClicks(AdDisplay.this);
                gomarkt(adpackage);

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
       // finish();
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
