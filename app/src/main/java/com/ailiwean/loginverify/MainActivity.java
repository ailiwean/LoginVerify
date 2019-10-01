package com.ailiwean.loginverify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ailiwean.lib.AnLoginVerify;
import com.ailiwean.lib.LoginChunk;
import com.ailiwean.lib.LoginVerify;
import com.ailiwean.lib.annotations.LoginDunk;


public class MainActivity extends AppCompatActivity {


    @LoginDunk
    View centerView;
    @LoginDunk
    View centerView2;
    @LoginDunk
    View centerView3;
    @LoginDunk
    View centerView4;
    @LoginDunk
    View centerView5;
    @LoginDunk
    View centerView6;
    @LoginDunk
    View centerView7;

    private long startTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        centerView = findViewById(R.id.centerText);
        centerView2 = findViewById(R.id.centerText2);
        centerView3 = findViewById(R.id.centerText3);
        centerView4 = findViewById(R.id.centerText4);
        centerView5 = findViewById(R.id.centerText5);
        centerView6 = findViewById(R.id.centerText6);
        centerView7 = findViewById(R.id.centerText7);

        centerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        centerView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        centerView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        centerView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        centerView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        centerView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        centerView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        startTime = System.currentTimeMillis();

        AnLoginVerify.bind(new LoginChunk() {
            @Override
            public boolean verifyLogin() {
                return false;
            }

            @Override
            public void goLogin() {
                Toast.makeText(MainActivity.this, "我要去登陆", Toast.LENGTH_SHORT).show();

            }
        }, this);

//        centerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//

        LoginVerify.get(new LoginChunk() {
            @Override
            public boolean verifyLogin() {
                return false;
            }

            @Override
            public void goLogin() {
                Toast.makeText(MainActivity.this, "我要去登陆", Toast.LENGTH_SHORT).show();
            }
        }).registerView(centerView, centerView2, centerView3, centerView4, centerView5, centerView6, centerView7);


    }

    @Override
    protected void onStart() {
        super.onStart();
        long enTime = System.currentTimeMillis();
        Log.e("Time:", (enTime - startTime) + "");
    }
}
