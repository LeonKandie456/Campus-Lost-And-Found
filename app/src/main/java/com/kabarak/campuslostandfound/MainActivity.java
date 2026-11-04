package com.kabarak.campuslostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

// TASK 1: Home screen
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button lost = findViewById(R.id.btnLost);
        Button found = findViewById(R.id.btnFound);
        Button view = findViewById(R.id.btnView);

        lost.setOnClickListener(v -> openReport(true));
        found.setOnClickListener(v -> openReport(false));
        view.setOnClickListener(v -> startActivity(new Intent(this, ViewItemsActivity.class)));
    }

    private void openReport(boolean isLost) {
        Intent i = new Intent(this, ReportActivity.class);
        i.putExtra("isLost", isLost);
        startActivity(i);
    }
}
