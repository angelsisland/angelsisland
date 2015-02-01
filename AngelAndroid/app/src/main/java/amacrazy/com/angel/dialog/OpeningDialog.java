package amacrazy.com.angel.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import amacrazy.com.angel.R;
import amacrazy.com.angel.activity.MainActivity;
import amacrazy.com.angel.service.AngelBackgroundSong;
import amacrazy.com.angel.util.Util;

/**
 * Created by choi on 2015. 2. 1..
 */
public class OpeningDialog extends Dialog implements View.OnClickListener {

    RelativeLayout container;
    ImageView arrow;
    ImageView commit;
    TextView text;
    EditText edit;

    int count = 0;
    String[] message =
            {"천사의 섬에 오신 것을\n환영합니다!",
            "천사의 섬에는\n한가지 규칙이 있습니다.",
            "바로 칭찬과 위로만을\n할 수 있다는 것 입니다",
            "만약 이 규칙을 어긴다면\n천사의 섬에 머무를 수\n없습니다",
            "다시 한 번,\n세상에서 가장 따뜻한 마을에\n오신 것을 축하드립니다!"};

    public OpeningDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_opening);

        container = (RelativeLayout)findViewById(R.id.dialog_opening_edit_container);
        arrow = (ImageView)findViewById(R.id.dialog_opening_arrow);
        commit = (ImageView)findViewById(R.id.dialog_opening_commit);

        text = (TextView)findViewById(R.id.dialog_opening_text);
        edit = (EditText)findViewById(R.id.dialog_opening_name_edit);

        arrow.setOnClickListener(this);
        commit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        switch (viewId){
            case R.id.dialog_opening_commit:
                SharedPreferences user = getOwnerActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                user.edit().putString("nickname", edit.getText().toString()).commit();
                Util.hideSoftKeyboard(getOwnerActivity());
                InputMethodManager imm = (InputMethodManager)getOwnerActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                //network로 user 보내기
                container.setVisibility(View.GONE);
                arrow.setVisibility(View.VISIBLE);
                arrow.performClick();
                break;
            case R.id.dialog_opening_arrow:
                if(count == 4) {
                    getOwnerActivity().stopService(new Intent(getOwnerActivity(), AngelBackgroundSong.class));
                    Intent intent = new Intent(getOwnerActivity(), MainActivity.class);
                    getOwnerActivity().startActivity(intent);
                    getOwnerActivity().finish();
                    break;
                }
                count++;
                text.setText(message[count]);
                break;
        }
    }
}
