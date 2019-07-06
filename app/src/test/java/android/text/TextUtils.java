package android.text;

import android.support.annotation.Nullable;

/**
 * Created by WuHao028 on 2019-07-06
 */
public class TextUtils {
    public static boolean isEmpty(@Nullable CharSequence str) {
        return str == null || str.length() == 0;
    }
}
