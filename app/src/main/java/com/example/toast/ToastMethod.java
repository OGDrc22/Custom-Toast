package com.example.toast;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import android.os.Handler;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

public class ToastMethod {

    private Activity activity;
    private ViewGroup rootView;
    private View layoutNotification;

    private CardView cardView;
    private TextView textView;
    private ImageView imageView;

    public ToastMethod(Activity activity) {
        this.activity = activity;
        this.rootView = activity.findViewById(R.id.main);
    }

    public void showNotification(int notificationLayout, String message, int imgResID, Class<?> toActivity, Integer duration_default_3000_3s) {
        LayoutInflater inflater = activity.getLayoutInflater();
        layoutNotification = inflater.inflate(notificationLayout, rootView,false);

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

        imageView = layoutNotification.findViewById(R.id.imageView);
        imageView.setImageResource(imgResID);

        // Setting Position
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );

        layoutNotification.setLayoutParams(params);

        rootView.addView(layoutNotification);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone((ConstraintLayout) rootView);

        constraintSet.connect(layoutNotification.getId(), ConstraintSet.TOP, rootView.getId(), ConstraintSet.TOP, 4);
        constraintSet.connect(layoutNotification.getId(), ConstraintSet.LEFT, rootView.getId(), ConstraintSet.LEFT, 0);
        constraintSet.connect(layoutNotification.getId(), ConstraintSet.RIGHT, rootView.getId(), ConstraintSet.RIGHT, 0);

        constraintSet.applyTo((ConstraintLayout) rootView);

        int delay = (duration_default_3000_3s != null) ? duration_default_3000_3s : 3000;
        new Handler().postDelayed(() -> rootView.removeView(layoutNotification), delay);
    }

    public void hideNotification() {
        rootView.removeView(layoutNotification);
    }
}


// For FrameLayout
// Define layout parameters for centering the view
    //FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
    //        FrameLayout.LayoutParams.WRAP_CONTENT,
    //        FrameLayout.LayoutParams.WRAP_CONTENT
    //);
    //params.gravity = Gravity.CENTER; // This will center the view