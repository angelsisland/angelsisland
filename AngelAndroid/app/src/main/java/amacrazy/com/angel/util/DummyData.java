package amacrazy.com.angel.util;

import java.util.ArrayList;

import amacrazy.com.angel.model.Writing;

/**
 * Created by choi on 2015. 2. 1..
 */
public class DummyData {

    public static ArrayList<Writing> dummyPraiseWriting = new ArrayList<>();
    public static ArrayList<Writing> dummyWorryWriting = new ArrayList<>();
    public static ArrayList<Integer> integers = new ArrayList<>();

    static {
        dummyPraiseWriting.add(new Writing("praise", "오늘 착한 일!!dummy", "dummy data", null));
        dummyPraiseWriting.add(new Writing("praise", "오늘 dummy 일!!dummy", "dummy data", null));
        dummyPraiseWriting.add(new Writing("praise", "dummy 착한 일!!dummy", "dummy data", null));
        dummyPraiseWriting.add(new Writing("praise", "오늘 착한 dummy!!dummy", "dummy data", null));

        dummyWorryWriting.add(new Writing("worry", "걱정 걱정이에요...", "dummy body 걱정입니다", null));
        dummyWorryWriting.add(new Writing("worry", "걱정 걱정이에요...", "dummy body 걱정입니다", null));
        dummyWorryWriting.add(new Writing("worry", "걱정 걱정이에요...", "dummy body 걱정입니다", null));
        dummyWorryWriting.add(new Writing("worry", "걱정 걱정이에요...", "dummy body 걱정입니다", null));

        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
    }

}
