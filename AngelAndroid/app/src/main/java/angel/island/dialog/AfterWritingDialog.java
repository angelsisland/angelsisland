package angel.island.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

import angel.island.R;
import angel.island.util.FontUtil;

/**
 * Created by choi on 2015. 2. 4..
 */
public class AfterWritingDialog extends Dialog implements View.OnClickListener{

    RelativeLayout button;

    public AfterWritingDialog(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_after_writing);

        button = (RelativeLayout)findViewById(R.id.dialog_after_close_button);
        button.setOnClickListener(this);

        String random;
        String[] random_pool = {"어느 생인들 아프지 않으며,\n어느 생인들 아름답지 않으리",
                                "가장 큰 영광은\n한 번도 실패하지 않음이 아니라\n실패할 때마다 다시 일어서는 데에 있다.\n-공자",
                                "흔들리지 않고 피는 꽃이 어디 있으랴\n - 도종환, 흔들리며 피는 꽃",
                                "밤이 깊으면 아침이 가까운 거고,\n겨울이 혹독하면 봄이 성큼 온 거니\n–나는 인생의 고비마다 한 뼘씩 자란다 中",
                                "수고했어, 오늘도!"};

        random = random_pool[new Random().nextInt(5)];
                ((TextView) findViewById(R.id.good_words)).setTypeface(FontUtil.extraBold);
        ((TextView)findViewById(R.id.good_words)).setText(random);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        dismiss();
        getOwnerActivity().finish();
    }
}
