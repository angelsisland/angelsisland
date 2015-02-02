package amacrazy.com.angel.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import amacrazy.com.angel.R;
import amacrazy.com.angel.model.Letter;

/**
 * Created by choi on 2015. 2. 3..
 */
public class LetterAdapter extends ArrayAdapter<Letter>{

    Context context;
    int resource;
    List<Letter> list;

    public LetterAdapter(Context context, int resource, List<Letter> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.list = objects;
    }

    @Override
    public View getView(int position, View row, ViewGroup parent) {
        if(row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(resource, parent, false);
        }
        ((TextView)row.findViewById(R.id.list_letter_title)).setText(list.get(position).getTitle());

        return row;
    }
}
