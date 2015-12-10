package jonestech.simpleserviceassistant;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
/**
 * Created by Josiah on 12/4/2014.
 */
public class Report {
    SQLiteDatabase db;
    DatabaseHandler dh;
    SQLiteQueryBuilder qb;
    Context c;
    Integer i = 0;
    public Report(Context context){c = context;}
    public Report Read(){
        dh = new DatabaseHandler(c, dh.DATABASE_NAME, null, dh.DATABASE_VERSION);
        db = dh.getReadableDatabase();
        return this;
    }
    public Report Write(){
        dh = new DatabaseHandler(c, dh.DATABASE_NAME, null, dh.DATABASE_VERSION);
        db = dh.getWritableDatabase();
        return this;
    }
    public void Close(){db.close();}
    public long Insert(int id, String date, String month, String year, int h, int m, int brch, int book, int t, int rv, int s, String D, int PC){
        ContentValues v = new ContentValues();
        v.put(dh.KEY_ID, id);
        v.put(dh.KEY_DATE, date);
        v.put(dh.KEY_MONTH, month);
        v.put(dh.KEY_YEAR, year);
        v.put(dh.KEY_H, h);
        v.put(dh.KEY_M, m);
        v.put(dh.KEY_BRCH, brch);
        v.put(dh.KEY_BOOK, book);
        v.put(dh.KEY_T, t);
        v.put(dh.KEY_RV, rv);
        v.put(dh.KEY_S, s);
        v.put(dh.KEY_D, D);
        v.put(dh.KEY_PC, PC);
        Write();
        long value = db.insert(dh.TABLE_REPORT, null, v);
        Close();
        return value;
    }
    public Integer queryTotalmHours(String month){
        Write();
        try{
            String sql = "SELECT TOTAL(hours) From report where month="+month;
            return (int)(Double.parseDouble(DatabaseUtils.stringForQuery(db, sql, null)));
        }finally {
            db.close();
        }
    }
    public int queryTotalmMags(String month){
        Write();
        try{
            String sql = "SELECT TOTAL(magazines) From report where month="+month;
            return (int) DatabaseUtils.longForQuery(db, sql, null);
        }finally {
            db.close();
        }
    }
    public int queryTotalmBroch(String month){
        Write();
        try{
            String sql = "SELECT TOTAL(brochures) From report where month="+month;
            return (int) DatabaseUtils.longForQuery(db, sql, null);
        }finally {
            db.close();
        }
    }
    public int queryTotalmBook(String month){
        Write();
        try{
            String sql = "SELECT TOTAL(books) From report where month="+month;
            return (int) DatabaseUtils.longForQuery(db, sql, null);
        }finally {
            db.close();
        }
    }
    public int queryTotalmTract(String month){
        Write();
        try{
            String sql = "SELECT TOTAL(tracts) From report where month="+month;
            return (int) DatabaseUtils.longForQuery(db, sql, null);
        }finally {
            db.close();
        }
    }
    public int queryTotalmRV(String month){
        Write();
        try{
            String sql = "SELECT TOTAL(return_visits) From report where month="+month;
            return (int) DatabaseUtils.longForQuery(db, sql, null);
        }finally {
            db.close();
        }
    }
    public int queryTotalmStudies(String month){
        Write();
        try{
            String sql = "SELECT TOTAL(bible_studies) From report where month="+month;
            return (int) DatabaseUtils.longForQuery(db, sql, null);
        }finally {
            db.close();
        }
    }
    public int queryTotalmCredits(String month){
        Write();
        try{
            String sql = "SELECT TOTAL(pioneer_credits) From report where month="+month;
            return (int) DatabaseUtils.longForQuery(db, sql, null);
        }finally{
            db.close();
        }
    }
    public Integer queryTotalyHours(String year){
        Write();
        try{
            String sql = "SELECT TOTAL(hours) From report where year="+year;
            return (int)(Double.parseDouble(DatabaseUtils.stringForQuery(db, sql, null)));
        }finally {
            db.close();
        }
    }
    public int queryTotalyMags(String year){
        Write();
        try{
            String sql = "SELECT TOTAL(magazines) From report where year="+year;
            return (int) DatabaseUtils.longForQuery(db, sql, null);
        }finally {
            db.close();
        }
    }
    public int queryTotalyBroch(String year){
        Write();
        try{
            String sql = "SELECT TOTAL(brochures) From report where year="+year;
            return (int) DatabaseUtils.longForQuery(db, sql, null);
        }finally {
            db.close();
        }
    }
    public int queryTotalyBook(String year){
        Write();
        try{
            String sql = "SELECT TOTAL(books) From report where year="+year;
            return (int) DatabaseUtils.longForQuery(db, sql, null);
        }finally {
            db.close();
        }
    }
    public int queryTotalyTract(String year){
        Write();
        try{
            String sql = "SELECT TOTAL(tracts) From report where year="+year;
            return (int) DatabaseUtils.longForQuery(db, sql, null);
        }finally {
            db.close();
        }
    }
    public int queryTotalyRV(String year){
        Write();
        try{
            String sql = "SELECT TOTAL(return_visits) From report where year="+year;
            return (int) DatabaseUtils.longForQuery(db, sql, null);
        }finally {
            db.close();
        }
    }
    public int queryTotalyStudies(String year){
        Write();
        try{
            String sql = "SELECT TOTAL(bible_studies) From report where year="+year;
            return (int) DatabaseUtils.longForQuery(db, sql, null);
        }finally {
            db.close();
        }
    }
    public int queryTotalyCredits(String year){
        Write();
        try{
            String sql= "SELECT TOTAL(pioneer_credits) From report where year="+year;
            return (int) DatabaseUtils.longForQuery(db, sql, null);
        }finally {
            db.close();
        }
    }
    public String queryReport(int rowId){
        String report = "";
        String query = "SELECT date, month, year, hours, magazines, brochures, books, tracts, return_visits, bible_studies, details, pioneer_credits FROM report WHERE _id="+rowId;
        Write();
        Cursor c = db.rawQuery(query, null);
        if(c!=null&&c.getCount()>0){
            c.moveToFirst();
            do{
                report = (c.getString(0)+ " date, "+c.getString(1)+ " month, "+c.getString(2)+" year, "
                        +c.getString(3)+" hours, "+c.getString(4)+" magazines, "+c.getString(5)+" brochures, "
                        +c.getString(6)+" books, "+c.getString(7)+" tracts, "+c.getString(8)+" return visits, "
                        +c.getString(9)+" bible studies. Details are: "+c.getString(10)+". Pioneer credits are: "
                        +c.getString(11)+".");
            }while(c.moveToNext());
        }
        Close();
        return report;
    }
    public String queryDate(int rowId){
        String date = "";
        String query = "select date from report where _id="+rowId;
        Write();
        Cursor c = db.rawQuery(query, null);
        if(c!=null&&c.getCount()>0){
            c.moveToFirst();
            do{
                date = c.getString(0);
            }while (c.moveToNext());
        }
        Close();
        return date;
    }
    public String queryMonth(int rowId){
        String month = "";
        String query = "select month from report where _id="+rowId;
        Write();
        Cursor c = db.rawQuery(query, null);
        if(c!=null&&c.getCount()>0){
            c.moveToFirst();
            do{
                month = c.getString(0);
            }while(c.moveToNext());
        }
        Close();
        return month;
    }
    public String queryYear(int rowId){
        String year = "";
        String query = "select year from report where _id="+rowId;
        Write();
        Cursor c = db.rawQuery(query, null);
        if(c!=null&&c.getCount()>0){
            c.moveToFirst();
            do{
                year = c.getString(0);
            }while(c.moveToNext());
        }
        Close();
        return year;
    }
    public Integer queryHours(int rowId){
        Integer i = 0;
        String query = "select hours from report where _id="+rowId;
        Write();
        Cursor c = db.rawQuery(query, null);
        if(c!=null&&c.getCount()>0){
            c.moveToFirst();
            do{
                i = c.getInt(0);
            }while (c.moveToNext());
        }
        Close();
        return i;
    }
    public Integer queryMagazines(int rowId){
        String query = "select magazines from report where _id="+rowId;
        Write();
        Cursor c = db.rawQuery(query, null);
        if(c!=null&&c.getCount()>0){
            c.moveToFirst();
            do{
                i = c.getInt(0);
            }while (c.moveToNext());
        }
        Close();
        return i;
    }
    public Integer queryBrochures(int rowId){
        String query = "select brochures from report where _id="+rowId;
        Write();
        Cursor c = db.rawQuery(query, null);
        if(c!=null&&c.getCount()>0){
            c.moveToFirst();
            do{
                i = c.getInt(0);
            }while (c.moveToNext());
        }
        Close();
        return i;
    }
    public Integer queryBooks(int rowId){
        String query = "select books from report where _id="+rowId;
        Write();
        Cursor c = db.rawQuery(query, null);
        if(c!=null&&c.getCount()>0){
            c.moveToFirst();
            do{
                i = c.getInt(0);
            }while (c.moveToNext());
        }
        Close();
        return i;
    }
    public Integer queryTracts(int rowId){
        String query = "select tracts from report where _id="+rowId;
        Write();
        Cursor c = db.rawQuery(query, null);
        if(c!=null&&c.getCount()>0){
            c.moveToFirst();
            do{
                i = c.getInt(0);
            }while (c.moveToNext());
        }
        Close();
        return i;
    }
    public Integer queryRVs(int rowId){
        String query = "select return_visits from report where _id="+rowId;
        Write();
        Cursor c = db.rawQuery(query, null);
        if(c!=null&&c.getCount()>0){
            c.moveToFirst();
            do{
                i = c.getInt(0);
            }while (c.moveToNext());
        }
        Close();
        return i;
    }
    public Integer queryStudies(int rowId){
        String query = "select bible_studies from report where _id="+rowId;
        Write();
        Cursor c = db.rawQuery(query, null);
        if(c!=null&&c.getCount()>0){
            c.moveToFirst();
            do{
                i = c.getInt(0);
            }while (c.moveToNext());
        }
        Close();
        return i;
    }
    public String queryDetails(int rowId) {
        String s = "";
        String query = "select details from report where _id=" + rowId;
        Write();
        Cursor c = db.rawQuery(query, null);
        if (c != null && c.getCount() > 0) {
            c.moveToFirst();
            do {
                s = c.getString(0);
            } while (c.moveToNext());
        }
        Close();
        return s;
    }
    public Integer queryCredits(int rowId) {
        String query = "select pioneer_credits from report where _id="+rowId;
        Write();
        Cursor c = db.rawQuery(query, null);
        if(c!=null&&c.getCount()>0){
            c.moveToFirst();
            do{
                i = c.getInt(0);
            }while (c.moveToNext());
        }
        Close();
        return i;
    }
    public Cursor queryAll(){
        String[] all = {
                dh.KEY_ID, dh.KEY_DATE, dh.KEY_MONTH, dh.KEY_YEAR, dh.KEY_H, dh.KEY_M, dh.KEY_BRCH, dh.KEY_BOOK, dh.KEY_T, dh.KEY_RV, dh.KEY_S, dh.KEY_D, dh.KEY_PC
        };
        Write();
        Cursor c = db.query(dh.TABLE_REPORT, all, null, null, null, null, null, null);
        Close();
        return c;
    }
    public long updateDetail(int id, int h, int m, int brch, int book, int t, int rv, int s, String D, int PC){
        ContentValues v = new ContentValues();
        v.put(dh.KEY_H, h);
        v.put(dh.KEY_M, m);
        v.put(dh.KEY_BRCH, brch);
        v.put(dh.KEY_BOOK, book);
        v.put(dh.KEY_T, t);
        v.put(dh.KEY_RV, rv);
        v.put(dh.KEY_S, s);
        v.put(dh.KEY_D, D);
        v.put(dh.KEY_PC, PC);
        Write();
        long val = db.update(dh.TABLE_REPORT, v, dh.KEY_ID + "=" + id, null);
        Close();
        return val;
    }
    public int deleteOneReport(int rowId){
        Write();
        int value = db.delete(dh.TABLE_REPORT, dh.KEY_ID + "=" + rowId, null);
        Close();
        return value;
    }
}