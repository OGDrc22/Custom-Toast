package com.example.toast;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button button, buttonWithOnclick, button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastMethod toastMethod = new ToastMethod(MainActivity.this);
                toastMethod.showNotification(R.layout.in_app_notification, "Custom Toast", R.drawable.ic_launcher_foreground, null, null);
            }
        });

        buttonWithOnclick = findViewById(R.id.buttonWithOnClick);
        buttonWithOnclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastMethod toastMethod = new ToastMethod(MainActivity.this);
                toastMethod.showNotification(R.layout.in_app_notification, "Click here", R.drawable.baseline_account_circle_24, YourSecondActivity.class, 6000 );
            }
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Regular Toast", Toast.LENGTH_SHORT).show();
            }
        });
    }
}