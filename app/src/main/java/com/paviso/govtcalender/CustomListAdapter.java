package com.paviso.govtcalender;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomListAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final ArrayList itemIds;
    private final ArrayList itemTexts;

    public CustomListAdapter(Activity context1, ArrayList itemId, ArrayList itemText) {

        super(context1, R.layout.listitem, itemId);
        // TODO Auto-generated constructor stub

        context=context1;
        itemIds=itemId;
        itemTexts=itemText;
    }

    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.listitem, null,true);

        TextView txtId = (TextView) rowView.findViewById(R.id.txtId);
        TextView txtText = (TextView) rowView.findViewById(R.id.txtText);

        txtId.setText(itemIds.get(position).toString());
        txtText.setText(itemTexts.get(position).toString());
        return rowView;

    };
}
