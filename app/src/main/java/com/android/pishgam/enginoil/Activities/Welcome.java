package com.android.pishgam.enginoil.Activities;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.pishgam.enginoil.R;
import com.android.pishgam.enginoil.Utilities.Setting;
import com.github.hujiaweibujidao.wava.Techniques;
import com.github.hujiaweibujidao.wava.YoYo;
import com.squareup.picasso.Picasso;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

//--------------Using Activity Instead Of AppCompatActivity To make FullScreen

public class Welcome extends Activity {


    Setting setting=null;
    Thread timer=null;
    TextView msg;
    ImageView image;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setting=new Setting(Welcome.this);
        setting.setLocale("fa");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_welcome);

        //----------------------------------------Set FullScreen Splash-----------------------------
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        msg=(TextView)findViewById(R.id.title);
        image=(ImageView)findViewById(R.id.image);

        timer=new Thread(){
            @Override
            public void run() {
                try{
                    sleep(7000);
                    setting.openActivity(Main.class,new View(Welcome.this));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        timer.start();
        Animate(msg);
        Fit(image);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    private void Animate(TextView txt){
        YoYo.with(Techniques.FadeInUp).duration(3000).playOn(txt);
    }

    private void Fit(ImageView img){
        Picasso.with(Welcome.this).load(R.drawable.welcome).fit().into(img);
    }
}
