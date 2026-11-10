package com.kabarak.campuslostandfound;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

// TASK 3 + TASK 4: View, search and filter
public class ViewItemsActivity extends AppCompatActivity {
    private final ArrayList<Item> shown = new ArrayList<>();
    private ItemAdapter adapter;
    private EditText etSearch;
    private Spinner spCategory, spStatus;
    private TextView tvEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_items);
        setTitle("Reported Items");

        etSearch = findViewById(R.id.etSearch);
        spCategory = findViewById(R.id.spFilterCategory);
        spStatus = findViewById(R.id.spFilterStatus);
        tvEmpty = findViewById(R.id.tvEmpty);
        ListView list = findViewById(R.id.listItems);

        spCategory.setAdapter(ArrayAdapter.createFromResource(this,
                R.array.filter_categories, android.R.layout.simple_spinner_dropdown_item));
        spStatus.setAdapter(ArrayAdapter.createFromResource(this,
                R.array.filter_status, android.R.layout.simple_spinner_dropdown_item));

        adapter = new ItemAdapter(this, shown);
        list.setAdapter(adapter);

        etSearch.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence s, int a, int b, int c) {}
            public void onTextChanged(CharSequence s, int a, int b, int c) { applyFilters(); }
            public void afterTextChanged(Editable s) {}
        });
        AdapterView.OnItemSelectedListener l = new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> p, View v, int pos, long id) { applyFilters(); }
            public void onNothingSelected(AdapterView<?> p) {}
        };
        spCategory.setOnItemSelectedListener(l);
        spStatus.setOnItemSelectedListener(l);

        // Optional challenge: mark as Claimed/Returned
        list.setOnItemClickListener((p, v, pos, id) -> {
            Item it = shown.get(pos);
            if (it.claimed) return;
            new AlertDialog.Builder(this)
                    .setTitle("Mark as claimed?")
                    .setMessage("Mark \"" + it.name + "\" as Claimed/Returned?")
                    .setPositiveButton("Yes", (d, w) -> { it.claimed = true; applyFilters(); })
                    .setNegativeButton("No", null)
                    .show();
        });
    }

    @Override
    protected void onResume() { super.onResume(); applyFilters(); }

    private void applyFilters() {
        String q = etSearch.getText().toString().trim().toLowerCase();
        String cat = spCategory.getSelectedItem().toString();
        String status = spStatus.getSelectedItem().toString();

        shown.clear();
        for (Item it : ItemRepository.getAll()) {
            boolean matchName = it.name.toLowerCase().contains(q);
            boolean matchCat = cat.equals("All") || it.category.equals(cat);
            boolean matchStatus = status.equals("All")
                    || (status.equals("Lost") && it.isLost)
                    || (status.equals("Found") && !it.isLost);
            if (matchName && matchCat && matchStatus) shown.add(it);
        }
        adapter.notifyDataSetChanged();
        tvEmpty.setVisibility(shown.isEmpty() ? View.VISIBLE : View.GONE);
    }
}
