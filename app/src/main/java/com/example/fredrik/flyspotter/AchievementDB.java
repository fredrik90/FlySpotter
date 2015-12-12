package com.example.fredrik.flyspotter;
// the database is followed from a tutorial: http://www.techotopia.com/index.php/An_Android_Studio_SQLite_Database_Tutorial

//Data model
public class AchievementDB {

    private int _id;
    private String _name;



    public AchievementDB() {

    }

    public AchievementDB(int id, String name) {
        this._id = id;
        this._name = name;
    }

    public AchievementDB(String name) {
        this._name = name;
    }

    public void setID(int id) {
        this._id = id;
    }

    public int getID() {
        return this._id;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getName() {
        return this._name;
    }



}
