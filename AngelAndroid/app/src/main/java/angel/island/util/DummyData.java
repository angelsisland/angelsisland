package angel.island.util;

import java.util.ArrayList;

import angel.island.model.Comment;
import angel.island.model.Writing;
import angel.island.model.Letter;

/**
 * Created by choi on 2015. 2. 1..
 */
public class DummyData {

    public static ArrayList<Writing> dummyPraiseWriting = new ArrayList<>();
    public static ArrayList<Writing> dummyWorryWriting = new ArrayList<>();
    public static ArrayList<Integer> integers = new ArrayList<>();
    public static ArrayList<Comment> comments = new ArrayList<>();
    public static ArrayList<Letter> letters = new ArrayList<>();

    static {
        /*
        dummyPraiseWriting.add(new Writing("praise", "오늘 착한 일!!dummy", "dummy data", null));
        dummyPraiseWriting.add(new Writing("praise", "오늘 dummy 일!!dummy", "dummy data", null));
        dummyPraiseWriting.add(new Writing("praise", "dummy 착한 일!!dummy", "dummy data", null));
        dummyPraiseWriting.add(new Writing("praise", "오늘 착한 dummy!!dummy", "dummy data", null));

        dummyWorryWriting.add(new Writing("worry", "걱정 걱정이에요...", "dummy body 걱정입니다", null));
        dummyWorryWriting.add(new Writing("worry", "걱정 걱정이에요...", "dummy body 걱정입니다", null));
        dummyWorryWriting.add(new Writing("worry", "걱정 걱정이에요...", "dummy body 걱정입니다", null));
        dummyWorryWriting.add(new Writing("worry", "걱정 걱정이에요...", "dummy body 걱정입니다", null));
        */

        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);

        comments.add(new Comment(1, "정말 칭찬칭찬 합니다.", "철수"));
        comments.add(new Comment(1, "정말 칭찬칭찬2 합니다.", "영미"));
        comments.add(new Comment(1, "정말 칭찬칭찬3 합니다.", "감자"));
        comments.add(new Comment(1, "정말 칭찬칭찬4 합니다.", "감자천사"));

        letters.add(new Letter("titledummy1", "contentsdummy1"));
        letters.add(new Letter("titledummy1", "contentsdummy2"));
        letters.add(new Letter("titledummy1", "contentsdummy3"));
        letters.add(new Letter("titledummy1", "contentsdummy4"));
        letters.add(new Letter("titledummy1", "contentsdummy5"));
    }

}
