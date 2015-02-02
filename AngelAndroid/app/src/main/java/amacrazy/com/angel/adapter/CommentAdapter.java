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
import amacrazy.com.angel.model.Comment;

/**
 * Created by choi on 2015. 2. 2..
 */
public class CommentAdapter extends ArrayAdapter<Comment> {

    private Context context;
    private int resource;
    private List<Comment> list;

    public CommentAdapter(Context context, int resource, List<Comment> objects) {
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

        ((TextView)row.findViewById(R.id.row_comment_nick)).setText(list.get(position).getWriter());
        ((TextView)row.findViewById(R.id.row_comment_contents)).setText(list.get(position).getContents());

        return row;
    }
}
