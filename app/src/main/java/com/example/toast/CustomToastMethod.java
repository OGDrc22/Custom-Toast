package com.example.toast;


import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import android.os.Handler;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

public class CustomToastMethod {

    private Activity activity;
    private ViewGroup rootView;
    private View layoutNotification;

    private CardView cardView;
    private TextView textView, textViewDesc;
    private ImageView imageView;

    public CustomToastMethod(Activity activity) {
        this.activity = activity;
        this.rootView = activity.findViewById(R.id.main);
    }

    public void showNotification(int notificationLayout, String message, String description, int imgResID, Class<?> toActivity, Integer duration_default_3000_3s) {
        LayoutInflater inflater = activity.getLayoutInflater();
        layoutNotification = inflater.inflate(notificationLayout, rootView,false);


        Animation slideIn = AnimationUtils.loadAnimation(activity, R.anim.slide_in);
        Animation slideOut = AnimationUtils.loadAnimation(activity, R.anim.slide_out);

        cardView = layoutNotification.findViewById(R.id.cardView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toActivity != null) {
                    Intent intent = new Intent(activity, toActivity);
                    activity.startActivity(intent);
                }
            }
        });

        textView = layoutNotification.findViewById(R.id.notificationTextView);
        textView.setText(message);

        textViewDesc = layoutNotification.findViewById(R.id.notificationTextViewDesc);
        textViewDesc.setText(description);
        if (description != null) {
            textViewDesc.setVisibility(View.VISIBLE);
        } else {
            textViewDesc.setVisibility(View.GONE);
        }

        imageView = layoutNotification.findViewById(R.id.imageView);
        imageView.setImageResource(imgResID);

        layoutNotification.setAnimation(slideIn);

        rootView.addView(layoutNotification);

        int delay = (duration_default_3000_3s != null) ? duration_default_3000_3s : 3000;
        new Handler().postDelayed(() -> {
            layoutNotification.startAnimation(slideOut);
            // Remove the view after the slide-out animation duration
            new Handler().postDelayed(() -> rootView.removeView(layoutNotification), slideOut.getDuration());
        }, delay - slideOut.getDuration());
    }

    public void hideNotification() {
        rootView.removeView(layoutNotification);
    }
}