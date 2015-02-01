package amacrazy.com.angel.util;

import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by choi on 2015. 1. 31..
 */
public class FontUtil {

    public static Typeface extraBold;

    public static void setGlobalFont(ViewGroup root) {
        for (int i = 0; i < root.getChildCount(); i++) {
            View child = root.getChildAt(i);
            if (child instanceof TextView) {
                ((TextView) child).setTypeface(extraBold);
            }
            else if (child instanceof ViewGroup)
                setGlobalFont((ViewGroup)child);
        }
    }
}
