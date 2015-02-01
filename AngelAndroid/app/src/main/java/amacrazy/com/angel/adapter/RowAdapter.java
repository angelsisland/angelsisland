package amacrazy.com.angel.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import amacrazy.com.angel.R;
import amacrazy.com.angel.model.Writing;

/**
 * Created by choi on 2015. 1. 26..
 */
public class RowAdapter extends ArrayAdapter<Writing> {

    private Context context;
    private int resource;
    private List<Writing> list;
    private List<Integer> countList = null;
    private String category;

    public RowAdapter(Context context, int resource, List<Writing> objects, String category) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.list = objects;
        this.category = category;
    }

    public RowAdapter(Context context, int resource, List<Writing> objects, String category, List<Integer> countList) {
        this(context,resource,objects,category);
        this.countList = countList;
    }

    @Override
    public View getView(int position, View row, ViewGroup parent) {

        if(row == null) {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(resource, parent, false);
        }

        ImageView iconView = (ImageView)row.findViewById(R.id.row_right_icon);
        TextView countView = (TextView)row.findViewById(R.id.row_right_text);
        TextView textView = (TextView)row.findViewById(R.id.list_received_writing_title);

        Bitmap icon;
        String count = null;

        if(category.equals("mine")) {
            icon = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.number_box);
            count = countList.get(position).toString();
            countView.setText(count);
        }
        else {
            icon = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.arrow);
        }
        iconView.setImageBitmap(icon);
        textView.setText(list.get(position).getTitle());

        return row;
    }
}
