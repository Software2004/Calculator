package com.example.calculator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.HandlerCompat;

import java.util.logging.LogRecord;


@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashfile);
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        },1000);
        /// yr wo aik method hota hai jiss mn ap ko bar bar onclick listener nhi likhna prta blky ap sary btns ky liay aik hi dfa define krty hain wo kia hota hai? wo implement krty hain Main activity mn
        /// abhi bs onclicklistener hi lgaia hai...
        ///aap kuch design krty ho,, to aap ko sochna ye hai k kia kroon to user yahan ruka rahay...
        /// pehly kuch seekh lyn phir user ko bhi rook lyn gy ....ahci baat hai...
        //thek hai chal gai hai
        /// ab kya kia jy
        ///        whan bhi ruk gai hai
        /// acha mn mobile connect krta hoon
        //aap apni dea kr dekho... emulator me msla hai...
        //ab ye aik secnd k liay rky gi screen...
        /// agay nhi gai... phly jatee thi? y/n nhi maloom
        // mn ny to handler ky zriay kia tha wo bhi ruk jati thi
        /// ab kia kryn
    }

}
