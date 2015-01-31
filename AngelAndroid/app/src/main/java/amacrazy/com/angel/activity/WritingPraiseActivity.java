package amacrazy.com.angel.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;

import amacrazy.com.angel.R;
import amacrazy.com.angel.model.Writing;
import amacrazy.com.angel.net.HttpHelper;

/**
 * Created by choi on 2015. 1. 25..
 */
public class WritingPraiseActivity extends ActionBarActivity implements View.OnClickListener {

    ImageView button1;
    ImageView button2;

    EditText titleEdit;
    EditText bodyEdit;

    Bitmap attachImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);
        button1 = (ImageView) findViewById(R.id.writing_praise_add_picture);
        button2 = (ImageView) findViewById(R.id.writing_praise_commit);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        titleEdit = (EditText) findViewById(R.id.writing_praise_title_edit);
        bodyEdit = (EditText) findViewById(R.id.writing_praise_body_edit);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.writing_praise_add_picture:
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 100);
                break;
            case R.id.writing_praise_commit:
                Toast.makeText(getApplicationContext(), "오늘도 수고했어^^", Toast.LENGTH_SHORT).show();
                //commit();
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode != 100)
            return;
        if (resultCode == Activity.RESULT_OK) {
            try {
                attachImg = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                ImageView image = (ImageView) findViewById(R.id.writing_attach_image);
                ImageView image2 = (ImageView) findViewById(R.id.writing_praise_add_picture);
                image2.setVisibility(View.GONE);
                image.setImageBitmap(attachImg);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void commit() {
        String category = "praise";
        String title = titleEdit.getText().toString();
        String contents = bodyEdit.getText().toString();
        String photo = null;
        HttpHelper httpHelper = new HttpHelper();
        byte[] bytes = httpHelper.convertBitmapToBytes(attachImg);
        Writing writing = new Writing(category, title, contents, bytes);
        httpHelper.connect("/api/write", "POST", writing, null);
        //사진 전송하기


    }
}
