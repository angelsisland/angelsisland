package angel.island.activity;

import android.os.Bundle;
import android.view.View;

import angel.island.R;
import angel.island.util.FontActivity;

/**
 * Created by choi on 2015. 2. 3..
 */
public class LevelActivity extends FontActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        int[] question_marks = {
                R.id.level_1,
                R.id.level_2,
                R.id.level_3,
                R.id.level_4,
                R.id.level_5
        };

        int[] character_marks = {
                R.id.skin1,
                R.id.skin2,
                R.id.skin3,
                R.id.skin4,
                R.id.skin5
        };

        int count = getIntent().getIntExtra("level", 0);
        for(int i = 0; i < count; i++) {
            findViewById(question_marks[i]).setVisibility(View.INVISIBLE);
            findViewById(character_marks[i]).setVisibility(View.VISIBLE);
        }
    }
}
