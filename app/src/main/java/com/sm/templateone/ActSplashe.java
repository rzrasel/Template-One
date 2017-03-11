package com.sm.templateone;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ActSplashe extends AppCompatActivity {
    private final int CONST_REDIRECT_WINDOW = 1;
    private final int CONST_REDIRECT_TIME = 1000 * 2;
    private TextView sysId;
    private ImageView sysIvTest;
    private static int counter = 0;

    private AnimatedVectorDrawableCompat playToResetAnim;
    private AnimatedVectorDrawableCompat resetToPlayAnim;
    private boolean isShowingPlay = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_splashe);
        sysId = (TextView) findViewById(R.id.sysId);
        sysIvTest = (ImageView) findViewById(R.id.sysIvTest);
        if (handlerRedirectWindow.hasMessages(CONST_REDIRECT_WINDOW))
            handlerRedirectWindow.removeMessages(CONST_REDIRECT_WINDOW);
        Message message = handlerRedirectWindow.obtainMessage(CONST_REDIRECT_WINDOW);
        handlerRedirectWindow.sendMessageDelayed(message, CONST_REDIRECT_TIME);

        //isShowingPlay = false;
        playToResetAnim = AnimatedVectorDrawableCompat.create(this, R.drawable.fab_play_to_reset_animated_vector);
        resetToPlayAnim = AnimatedVectorDrawableCompat.create(this, R.drawable.fab_reset_to_play_animated_vector);
    }

    private Handler handlerRedirectWindow = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message argMessage) {
            //super.handleMessage(msg);
            if (argMessage.what == CONST_REDIRECT_WINDOW) {
                counter++;
                sysId.setText("Time(s): " + counter);
                Message message = handlerRedirectWindow.obtainMessage(CONST_REDIRECT_WINDOW);
                handlerRedirectWindow.sendMessageDelayed(message, 2000);
                //onRedirectWindow(ActDashboard.class);
                changeButtonIcon();
            }
        }
    };

    private void onRedirectWindow(Class argClass) {
        Intent intent = new Intent(getApplicationContext(), argClass);
        //Intent intent = new Intent(getApplicationContext(), ActOTPCode.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = new Bundle();
        //bundle.putSerializable(APPConstants.SESSION.KEY, userSession);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    private void changeButtonIcon() {
        AnimatedVectorDrawableCompat currentDrawable = isShowingPlay ? playToResetAnim : resetToPlayAnim;
        sysIvTest.setImageDrawable(currentDrawable);
        currentDrawable.start();
        isShowingPlay = !isShowingPlay;
    }
}
/*
http://www.vogella.com/tutorials/AndroidBackgroundProcessing/article.html
http://stackoverflow.com/questions/13954611/android-when-should-i-use-a-handler-and-when-should-i-use-a-thread
http://stackoverflow.com/questions/18276060/android-handler-send-8-different-messages-handler-finds-8-messages-but-only-us
https://github.com/moagrius/MapView/issues/32


PathMorphing
https://github.com/andremion/Android-Animated-Icons
http://www.androiddesignpatterns.com/2016/11/introduction-to-icon-animation-techniques.html
https://github.com/alexjlockwood/adp-delightful-details/tree/master/app/src/main/res/drawable
https://github.com/lewismcgeary/AndroidtoAppleVectorLogo

https://android.jlelse.eu/animatedvectordrawablecompat-3d9568727c53#.5lu8i444d
https://github.com/lewismcgeary/AnimatedVectorDrawableCompat-play-to-reset-button
http://blog.passos.me/android-drawable-animation/
*/