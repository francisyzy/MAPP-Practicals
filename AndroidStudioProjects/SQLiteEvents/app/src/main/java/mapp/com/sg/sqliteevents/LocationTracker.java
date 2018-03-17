package mapp.com.sg.sqliteevents;

import android.provider.BaseColumns;

/**
 * Created by francisyzy on 16/8/17.
 */

public interface Constants extends BaseColumns {
    public static final String TABLE_NAME = "events";

    // columns
    public static final String TIME = "time";
    public static final String TITLE = "title";

}