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
    private View notificationLayout;

    private CardView cardView;
    private TextView textView;
    private ImageView imageView;

    public ToastMethod(Activity activity) {
        this.activity = activity;
        this.rootView = activity.findViewById(R.id.main);
    }

    public void showNotification(String message, int imgResID, Class<?> toActivity, Integer duration) {
        LayoutInflater inflater = activity.getLayoutInflater();
        notificationLayout = inflater.inflate(R.layout.in_app_notification, null);

        cardView = notificationLayout.findViewById(R.id.cardView);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toActivity != null) {
                    Intent intent = new Intent(activity, toActivity);
                    activity.startActivity(intent);
                }
            }
        });

        textView = notificationLayout.findViewById(R.id.notificationTextView);
        textView.setText(message);

        imageView = notificationLayout.findViewById(R.id.imageView);
        imageView.setImageResource(imgResID);

        // Setting Position
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
        );

        notificationLayout.setLayoutParams(params);

        rootView.addView(notificationLayout);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone((ConstraintLayout) rootView);

        constraintSet.connect(notificationLayout.getId(), ConstraintSet.TOP, rootView.getId(), ConstraintSet.TOP, 4);
        constraintSet.connect(notificationLayout.getId(), ConstraintSet.LEFT, rootView.getId(), ConstraintSet.LEFT, 0);
        constraintSet.connect(notificationLayout.getId(), ConstraintSet.RIGHT, rootView.getId(), ConstraintSet.RIGHT, 0);

        constraintSet.applyTo((ConstraintLayout) rootView);

        int delay = (duration != null) ? duration : 3000;
        new Handler().postDelayed(() -> rootView.removeView(notificationLayout), delay);
    }

    public void hideNotification() {
        rootView.removeView(notificationLayout);
    }

    public void updateImageResource(int newImageResId) {
        if (imageView != null) {
            imageView.setImageResource(newImageResId);
        }
    }
}


// For FrameLayout
// Define layout parameters for centering the view
    //FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
    //        FrameLayout.LayoutParams.WRAP_CONTENT,
    //        FrameLayout.LayoutParams.WRAP_CONTENT
    //);
    //params.gravity = Gravity.CENTER; // This will center the view