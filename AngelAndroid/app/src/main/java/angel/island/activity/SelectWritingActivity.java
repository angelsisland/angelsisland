package angel.island.activity;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import angel.island.R;
import angel.island.util.FontActionbarActivity;

/**
 * Created by choi on 2015. 2. 2..
 */
public class SelectWritingActivity extends FontActionbarActivity implements View.OnClickListener {

    ImageView selectIcon1;
    ImageView selectIcon2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_writing);

        query.id(R.id.actionbar_home_button).image(BitmapFactory.decodeResource(getResources(), R.drawable.home_icon_black));
        query.id(R.id.blue_blue_back).backgroundColor(Color.WHITE);
        query.id(R.id.actionbar_title_text).textColor(getResources().getColor(R.color.actionbar_title_color2));
        query.id(R.id.select_praise_img).clicked(this);
        query.id(R.id.select_proud_img).clicked(this);
        renameActionBarTitle("글쓰기 선택");
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        Intent intent = new Intent(this, WritingActivity.class);
        switch (viewId) {
            case R.id.select_praise_img:
                intent.putExtra("category", "praise");
                break;
            case R.id.select_proud_img:
                intent.putExtra("category", "worry");
                break;
        }
        startActivity(intent);
    }
}
