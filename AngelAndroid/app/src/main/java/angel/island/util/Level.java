package angel.island.util;

import angel.island.model.User;

/**
 * Created by choi on 2015. 2. 4..
 */
public class Level {

    private int[] level_exp = {1, 10, 20, 30, 40};

    public int calRemainExp(User user) {
        int exp = user.getExp();
        int level = calLevel(user);

        if(level >= 5)
            return 0;

        int remainder = level_exp[level] - exp;

        return remainder;

    }

    public int calLevel(User user) {
        int exp = user.getExp();

        int level;

        if(exp < level_exp[0])
            level = 0;
        else if(exp < level_exp[1])
            level = 1;
        else if(exp < level_exp[2])
            level = 2;
        else if(exp < level_exp[3])
            level = 3;
        else if(exp < level_exp[4])
            level = 4;
        else
            level = 5;

        return level;
    }

}
