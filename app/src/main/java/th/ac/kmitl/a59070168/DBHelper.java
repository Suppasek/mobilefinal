package th.ac.kmitl.a59070168;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private final String TAG = getClass().getSimpleName();

    private SQLiteDatabase sqLiteDatabase;

    public DBHelper(Context context) {
        super(context, "user.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE user ( userId TEXT PRIMARY KEY, " +
                "name TEXT, age TEXT, password TEXT)";
        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addUser(String userId, String name, String age, String password) {
        sqLiteDatabase = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(Friend.Column.ID, friend.getId());
        values.put("userId", userId);
        values.put("name", name);
        values.put("age", age);
        values.put("password", password);

        sqLiteDatabase.insert("user", null, values);

        sqLiteDatabase.close();
    }

    public boolean valueExist(String value) {
        String query = "Select * From user where userId = '"+value+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if(cursor.getCount() != 0){
            return true;
        }else {
            return false;
        }
    }
}
