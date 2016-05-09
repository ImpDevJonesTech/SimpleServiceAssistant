package jonestech.ministry_report;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Josman on 2/25/2016.
 */
public interface MigrationTask {
    void onUpgrade(SQLiteDatabase Db);
    void onDowngrade(SQLiteDatabase Db);
}
