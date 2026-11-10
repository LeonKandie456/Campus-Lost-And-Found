package com.kabarak.campuslostandfound;

import android.content.Context;
import android.graphics.Color;
import android.view.*;
import android.widget.*;
import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {
    public ItemAdapter(Context ctx, List<Item> items) { super(ctx, 0, items); }

    @Override
    public View getView(int pos, View convert, ViewGroup parent) {
        if (convert == null)
            convert = LayoutInflater.from(getContext()).inflate(R.layout.item_row, parent, false);
        Item it = getItem(pos);

        ((TextView) convert.findViewById(R.id.tvName)).setText(it.name);
        TextView status = convert.findViewById(R.id.tvStatus);
        String s = (it.isLost ? "LOST" : "FOUND") + (it.claimed ? " • CLAIMED" : "");
        status.setText(s);
        status.setTextColor(it.claimed ? Color.GRAY : (it.isLost ? Color.parseColor("#C62828") : Color.parseColor("#2E7D32")));
        ((TextView) convert.findViewById(R.id.tvCategory)).setText("Category: " + it.category);
        ((TextView) convert.findViewById(R.id.tvDesc)).setText(it.description);
        ((TextView) convert.findViewById(R.id.tvLocation)).setText("Location: " + it.location + "  |  Date: " + it.date);
        ((TextView) convert.findViewById(R.id.tvContact)).setText("Contact: " + it.contact);
        return convert;
    }
}
