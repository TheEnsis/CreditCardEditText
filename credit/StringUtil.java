package net.pirsquare.haventmet.view.credit;

/**
 * Created by Nut on 8/23/17.
 */

public class StringUtil {
    private static final int INDEX_NOT_FOUND = -1;

    public StringUtil() {
    }

    public static String difference(String str1, String str2) {
        if (str1 == null) {
            return str2;
        } else if (str2 == null) {
            return str1;
        } else {
            int at = indexOfDifference(str1, str2);
            return at == -1 ? "" : str2.substring(at);
        }
    }

    public static int indexOfDifference(CharSequence cs1, CharSequence cs2) {
        if (cs1 == cs2) {
            return -1;
        } else if (cs1 != null && cs2 != null) {
            int i;
            for (i = 0; i < cs1.length() && i < cs2.length() && cs1.charAt(i) == cs2.charAt(i); ++i) {
                ;
            }

            return i >= cs2.length() && i >= cs1.length() ? -1 : i;
        } else {
            return 0;
        }
    }
}
