package jonestech.simpleserviceassistant;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Josman on 2/25/2016.
 */
public interface MigrationTask {
    public void onUpgrade(SQLiteDatabase Db);
    public void onDowngrade(SQLiteDatabase Db);
}
