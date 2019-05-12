package kmitl.it.project.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "User_Data";
    private static final String COL1 = "User_ID";
    private static final String COL2 = "Username";
    private static final String COL3 = "Teacher_Name";
    private static final String COL4 = "Superuser";
    private static final String COL5 = "Staff";

    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + TABLE_NAME +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL1 + " TEXT, "  + COL2 + " TEXT, " +
                COL3 + " TEXT, " + COL4 + " INTEGER, " + COL5 + " INTEGER)";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String id, String username, String teacherName, int superUser, int staff){
//    public void addData(int id, String username, String teacherName, int superUser, int staff){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1 , id);
        contentValues.put(COL2 , username);
        contentValues.put(COL3 , teacherName);
        contentValues.put(COL4 , superUser);
        contentValues.put(COL5 , staff);
        Log.d("db", "addData : Adding " + id + " to " + TABLE_NAME);
        Log.d("db", "addData : Adding " + username + " to " + TABLE_NAME);
        Log.d("db", "addData : Adding " + teacherName + " to " + TABLE_NAME);
        Log.d("db", "addData : Adding " + superUser + " to " + TABLE_NAME);
        Log.d("db", "addData : Adding " + staff + " to " + TABLE_NAME);
        db.insert(TABLE_NAME, null, contentValues);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == 1){
            return true;
        }
        else
        {
            return false;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    public void delete(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
    }
}
