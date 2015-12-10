package jonestech.simpleserviceassistant;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by Josiah on 12/4/2014.
 */
public class DatabaseHandler extends SQLiteOpenHelper {
    //Database version and name
    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "simpleServiceAssistant.db";
    //Database table name
    public static final String TABLE_REPORT = "report";
    //table column names
    public static final String KEY_ID = "_id";
    public static final String KEY_DATE = "date";
    public static final String KEY_MONTH = "month";
    public static final String KEY_YEAR = "year";
    public static final String KEY_H = "hours";
    public static final String KEY_M = "magazines";
    public static final String KEY_BRCH = "brochures";
    public static final String KEY_BOOK = "books";
    public static final String KEY_T = "tracts";
    public static final String KEY_RV = "return_visits";
    public static final String KEY_S = "bible_studies";
    public static final String KEY_D = "details";
    public static final String KEY_PC = "pioneer_credits";
    String CREATE_TABLE_REPORT = "CREATE TABLE " + TABLE_REPORT + " ("
            +KEY_ID+" integer primary key autoincrement, "+KEY_DATE+" STRING not null, "+KEY_MONTH+" STRING not null, "
            +KEY_YEAR+" STRING not null, "+KEY_H+" Integer not null, "+KEY_M+" INTEGER not null, "
            +KEY_BRCH+" INTEGER not null, "+KEY_BOOK+" INTEGER not null, "+KEY_T+" INTEGER not null, "+KEY_RV+
            " INTEGER not null, "+KEY_S+" INTEGER not null, "+KEY_D+" STRING not null, "+KEY_PC+" INTEGER not null);";
    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){super(context, DATABASE_NAME, factory, DATABASE_VERSION);}
    //Create Table
    @Override
    public void onCreate(SQLiteDatabase db){db.execSQL(CREATE_TABLE_REPORT);}
    //Upgrade Table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE " + TABLE_REPORT);onCreate(db);
    }
}
