package angel.island.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import angel.island.R;
import angel.island.dialog.AfterWritingDialog;
import angel.island.model.Writing;
import angel.island.net.Proxy;
import angel.island.util.FontActionbarActivity;

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

        if (category.equals("worry")) {
            ImageView imgView = (ImageView) findViewById(R.id.writing_praise_profile_image);
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

                if (category.equals("praise"))
                    new Proxy().sendWriting(new Writing(titleEdit.getText().toString(), bodyEdit.getText().toString(), "praise"));
                else
                    new Proxy().sendWriting(new Writing(titleEdit.getText().toString(), bodyEdit.getText().toString(), "solace"));

                AfterWritingDialog dialog = new AfterWritingDialog(this);
                dialog.setOwnerActivity(this);
                dialog.getWindow().setBackgroundDrawable(getResources().getDrawable(R.drawable.white_box));
                dialog.setCancelable(false);
                dialog.show();
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
                Log.e("picture", "error" + e.getMessage());
            }
        }
    }


    private void commit() {
        String category = "praise";
        String title = titleEdit.getText().toString();
        String contents = bodyEdit.getText().toString();
        Proxy proxy = new Proxy();
        //byte[] bytes = proxy.convertBitmapToBytes(attachImg);
        //String photo = Base64.encodeToString(bytes, Base64.DEFAULT);
        Writing writing = new Writing(category, title, contents, 1);
        proxy.connect("/api/write", writing, null);
        //사진 전송하기
    }

    private void setActionBarTitle() {
        if (category.equals("praise"))
            renameActionBarTitle("자랑 글쓰기");
        else
            renameActionBarTitle("고민 글쓰기");
    }
}
