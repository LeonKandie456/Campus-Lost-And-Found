package com.kabarak.campuslostandfound;

public class ReportActivity package com.kabarak.campuslostandfound;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.*;
        import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

// TASK 2: Report an item
public class ReportActivity extends AppCompatActivity {
    private EditText etName, etDesc, etLocation, etDate, etContact;
    private Spinner spCategory;
    private boolean isLost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        isLost = getIntent().getBooleanExtra("isLost", true);
        setTitle(isLost ? "Report Lost Item" : "Report Found Item");

        etName = findViewById(R.id.etName);
        etDesc = findViewById(R.id.etDescription);
        etLocation = findViewById(R.id.etLocation);
        etDate = findViewById(R.id.etDate);
        etContact = findViewById(R.id.etContact);
        spCategory = findViewById(R.id.spCategory);

        spCategory.setAdapter(ArrayAdapter.createFromResource(this,
                R.array.categories, android.R.layout.simple_spinner_dropdown_item));

        etDate.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            new DatePickerDialog(this, (view, y, m, d) ->
                    etDate.setText(d + "/" + (m + 1) + "/" + y),
                    c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)).show();
        });

        findViewById(R.id.btnSubmit).setOnClickListener(v -> confirmSubmit());
    }

    private void confirmSubmit() {
        if (isEmpty(etName) || isEmpty(etLocation) || isEmpty(etDate) || isEmpty(etContact)) {
            Toast.makeText(this, "Please fill in name, location, date and contact", Toast.LENGTH_LONG).show();
            return;
        }
        new AlertDialog.Builder(this)
                .setTitle("Submit report?")
                .setMessage("Do you want to submit this " + (isLost ? "lost" : "found") + " item report?")
                .setPositiveButton("Submit", (d, w) -> save())
                .setNegativeButton("Cancel", null)
                .show();
    }

    private void save() {
        ItemRepository.add(new Item(
                etName.getText().toString().trim(),
                etDesc.getText().toString().trim(),
                etLocation.getText().toString().trim(),
                etDate.getText().toString().trim(),
                etContact.getText().toString().trim(),
                spCategory.getSelectedItem().toString(),
                isLost));
        Toast.makeText(this, "Report submitted successfully!", Toast.LENGTH_LONG).show();
        finish();   // back to home screen
    }

    private boolean isEmpty(EditText e) { return e.getText().toString().trim().isEmpty(); }
}
{
}
