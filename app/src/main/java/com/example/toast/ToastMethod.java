package com.example.toast;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import android.os.Handler;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

public class ToastMethod {

    private Activity activity;
    private ViewGroup rootView;
    private View notificationLayout;

    private TextView textView;
    private ImageView imageView;

    public ToastMethod(Activity activity) {
        this.activity = activity;
        this.rootView = activity.findViewById(R.id.main);
    }

    public void showNotification(String message, int imgResID) {
        LayoutInflater inflater = activity.getLayoutInflater();
        notificationLayout = inflater.inflate(R.layout.in_app_notification, null);

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

        constraintSet.connect(notificationLayout.getId(), ConstraintSet.TOP, rootView.getId(), ConstraintSet.TOP, 0);
        constraintSet.connect(notificationLayout.getId(), ConstraintSet.LEFT, rootView.getId(), ConstraintSet.LEFT, 0);
        constraintSet.connect(notificationLayout.getId(), ConstraintSet.RIGHT, rootView.getId(), ConstraintSet.RIGHT, 0);

        constraintSet.applyTo((ConstraintLayout) rootView);

        new Handler().postDelayed(() -> rootView.removeView(notificationLayout), 6000);
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