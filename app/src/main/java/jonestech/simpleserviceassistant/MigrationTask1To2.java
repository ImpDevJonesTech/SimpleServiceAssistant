package jonestech.simpleserviceassistant;
import android.database.sqlite.SQLiteDatabase;
/**
 * Created by Josman on 2/25/2016.
 */
public class MigrationTask1To2 implements MigrationTask {
    @Override
    public void onUpgrade(SQLiteDatabase Db){
        String query =
                "ALTER TABLE report ADD pioneer_credits";
        Db.execSQL(query);
    }
    @Override
    public void onDowngrade(SQLiteDatabase Db){
        //nothing yet
    }
}
