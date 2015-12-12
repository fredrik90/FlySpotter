package com.example.fredrik.flyspotter;
// the database is followed from a tutorial: http://www.techotopia.com/index.php/An_Android_Studio_SQLite_Database_Tutorial

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;

public class AchievementDBHandler extends SQLiteOpenHelper {


    //Set variables
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "AchievementDB.db";
    private static final String TABLE_ACHIEVEMENT = "achievements";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ACHIEVEMENTNAME = "name";

    //Constructor
    public AchievementDBHandler(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    //When the database is first initialized!
    public void onCreate(SQLiteDatabase db) {

        String CREATE_PRODUCTS_TABLE = "CREATE TABLE " +
                TABLE_ACHIEVEMENT + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_ACHIEVEMENTNAME
                + " TEXT,"  + " BOOLEAN" + ")";
        db.execSQL(CREATE_PRODUCTS_TABLE);

    }


    @Override
    //The onUpgrade() method is called when the handler is invoked with a greater database version number from the one previously used.
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ACHIEVEMENT);
        onCreate(db);

    }


    //Add handler
    public void addAchievement(AchievementDB achievement) {

        ContentValues values = new ContentValues();
        values.put(COLUMN_ACHIEVEMENTNAME, achievement.getName());

        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(TABLE_ACHIEVEMENT, null, values);
        db.close();
    }


    //Query handler method
    public AchievementDB findachievement(String name) {
        String query = "Select * FROM " + TABLE_ACHIEVEMENT + " WHERE " + COLUMN_ACHIEVEMENTNAME + " =  \"" + name + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        AchievementDB achievement = new AchievementDB();

        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            achievement.setID(Integer.parseInt(cursor.getString(0)));
            achievement.setName(cursor.getString(1));
            cursor.close();
        } else {
            achievement = null;
        }
        db.close();
        return achievement;
    }


    //Delete
    public boolean deleteAchievement(String name) {

        boolean result = false;

        String query = "Select * FROM " + TABLE_ACHIEVEMENT + " WHERE " + COLUMN_ACHIEVEMENTNAME + " =  \"" + name + "\"";

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        AchievementDB achievement = new AchievementDB();

        if (cursor.moveToFirst()) {
            achievement.setID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_ACHIEVEMENT, COLUMN_ID + " = ?",
                    new String[] { String.valueOf(achievement.getID()) });
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }


}
