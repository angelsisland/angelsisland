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
import amacrazy.com.angel.model.Writing;

/**
 * Created by choi on 2015. 1. 26..
 */
public class WritingAdapter extends ArrayAdapter<Writing> {

    private Context context;
    private int resource;
    private List<Writing> list;

    public WritingAdapter(Context context, int resource, List<Writing> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.list = objects;
    }

    @Override
    public View getView(int position, View row, ViewGroup parent) {
        //이름을 convert view 에서 바꾸지 않아야 하나.
        if(row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(resource, parent, false);
        }

        TextView textView = (TextView)row.findViewById(R.id.list_received_writing_title);
        textView.setText(list.get(position).getTitle());
        return row;
    }
}
