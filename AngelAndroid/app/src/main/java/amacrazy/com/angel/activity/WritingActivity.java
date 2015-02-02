package amacrazy.com.angel.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import amacrazy.com.angel.R;
import amacrazy.com.angel.model.Writing;
import amacrazy.com.angel.net.HttpHelper;
import amacrazy.com.angel.util.FontActionbarActivity;

/**
 * Created by choi on 2015. 1. 25..
 */
public class WritingActivity extends FontActionbarActivity implements View.OnClickListener {

    ImageView button1;
    ImageView button2;

    EditText titleEdit;
    EditText bodyEdit;

    Bitmap attachImg;

    String category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_writing);

        category = getIntent().getStringExtra("category");
        button1 = (ImageView) findViewById(R.id.writing_attach_icon);
        button2 = (ImageView) findViewById(R.id.writing_commit);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

        setActionBarTitle();

        titleEdit = (EditText) findViewById(R.id.writing_praise_title_edit);
        bodyEdit = (EditText) findViewById(R.id.writing_praise_body_edit);

        if(category.equals("worry")){
            ImageView imgView = (ImageView)findViewById(R.id.writing_praise_profile_image);
            imgView.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.cheerup_icon));
        }
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId) {
            case R.id.writing_attach_icon:
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 100);
                break;
            case R.id.writing_commit:
                Toast.makeText(getApplicationContext(), "오늘도 수고했어^^", Toast.LENGTH_SHORT).show();
                //commit();
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK) {
            try {
                attachImg = MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                ImageView image = (ImageView) findViewById(R.id.writing_attach_back);
                ImageView image2 = (ImageView) findViewById(R.id.writing_attach_icon);
                image2.setVisibility(View.GONE);
                Bitmap display = Bitmap.createScaledBitmap(attachImg, image.getWidth(), image.getHeight(), true);
                image.setImageBitmap(display);
            } catch (Exception e) {
                e.printStackTrace();
                Log.e("picture", "error" +e.getMessage());
            }
        }
    }


    private void commit() {
        String category = "praise";
        String title = titleEdit.getText().toString();
        String contents = bodyEdit.getText().toString();
        HttpHelper httpHelper = new HttpHelper();
        byte[] bytes = httpHelper.convertBitmapToBytes(attachImg);
        String photo = Base64.encodeToString(bytes, Base64.DEFAULT);
        Writing writing = new Writing(category, title, contents, photo);
        httpHelper.connect("/api/write", "POST", writing, null);
        //사진 전송하기
    }

    private void setActionBarTitle() {
        if(category.equals("praise"))
            getSupportActionBar().setTitle("자랑 글쓰기");
        else
            getSupportActionBar().setTitle("고민 글쓰기");
    }
}
