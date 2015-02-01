package amacrazy.com.angel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import amacrazy.com.angel.R;
import amacrazy.com.angel.fragment.ExtraFragment;
import amacrazy.com.angel.fragment.HomeFragment;
import amacrazy.com.angel.fragment.MyPageFragment;
import amacrazy.com.angel.fragment.SelectWritingFragment;
import amacrazy.com.angel.util.FontActionbarActivity;


public class MainActivity extends FontActionbarActivity implements View.OnClickListener{

    int containerId = R.id.main_frame;

    ImageButton imgButton1;
    ImageButton imgButton2;
    ImageButton imgButton3;

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (viewId) {
            case R.id.img_button1:
                fragmentTransaction.replace(containerId, new MyPageFragment());
                fragmentTransaction.commit();
                break;
            case R.id.img_button2:
                fragmentTransaction.replace(containerId, new SelectWritingFragment());
                fragmentTransaction.commit();
                break;
            case R.id.img_button3:
                fragmentTransaction.replace(containerId, new ExtraFragment());
                fragmentTransaction.commit();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgButton1 = (ImageButton)findViewById(R.id.img_button1);
        imgButton2 = (ImageButton)findViewById(R.id.img_button2);
        imgButton3 = (ImageButton)findViewById(R.id.img_button3);
        imgButton1.setOnClickListener(this);
        imgButton2.setOnClickListener(this);
        imgButton3.setOnClickListener(this);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(containerId, new MyPageFragment());
        fragmentTransaction.add(containerId, new SelectWritingFragment());
        fragmentTransaction.add(containerId, new ExtraFragment());
        fragmentTransaction.add(containerId, new HomeFragment());
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.actionbar_home) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
