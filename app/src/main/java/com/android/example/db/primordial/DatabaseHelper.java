package com.android.example.db.primordial;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.example.common.log.Logger;


/**
 * @author:無忌
 * @date:2020/10/28
 * @description:
 */
class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DBHelper";
    private static final String DB_NAME = "db_school";
    private static final Integer DB_VERSION = 5;
    //=====VERSION 1.0=====
    //String createStudentSql = "create table student(id integer primary key autoincrement, name varchar(64), score_id integer);";
    //String createScoreSql = "create table score(id integer primary key autoincrement, name varchar(64), score integer);";
    //=====VERSION 2.0=====
    //String alterScoreSql = "alter table score rename to score_old";
    //String createCourseSql = "create table course(id integer primary key autoincrement, name varchar(64), score integer);";
    //String dropScoreSql = "drop table score_old";
    //=====VERSION 3.0=====
    //String alterStudentSql = "alter table student rename to student_old";
    //String createNewStudentSql = "create table student(id integer primary key autoincrement, name varchar(64), course_id integer);";
    //String dropStudentSql = "drop table student_old";
    //=====VERSION 4.0=====
    //String alterStudentSql = "alter table student add column 'addr' vachar(100) default '北京'";
    //=====VERSION 5.0=====
    //String alterStudentSql = "alter table student rename to student_backup";
    //String createNewStudentSql = "create table student(id integer primary key autoincrement, name varchar(64), course_id integer)";
    //String moveData = "insert into student select id, name, course_id from student_backup";
    //String dropStudentSql = "drop table student_backup";


    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        Logger.get().i(TAG, String.format("数据库初始化(name=%s,version=%s)", DB_NAME, DB_VERSION));
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String studentSql = "create table student(id integer primary key autoincrement, name varchar(64), score_id integer);";
        db.execSQL(studentSql);
        String scoreSql = "create table score(id integer primary key autoincrement, name varchar(64), score integer)";
        db.execSQL(scoreSql);
        Logger.get().i(TAG, String.format("创建数据库表：\n%s\n%s", studentSql, scoreSql));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Logger.get().i(TAG, String.format("数据库升级(oldVersion=%s,newVersion=%s)", oldVersion, newVersion));
        if (DB_VERSION == newVersion) {
            switch (oldVersion) {
                case 1:
                    //step1
                    String alterScoreSql = "alter table score rename to score_old";
                    db.execSQL(alterScoreSql);
                    //step2
                    String createCourseSql = "create table course(id integer primary key autoincrement, name varchar(64), score integer);";
                    db.execSQL(createCourseSql);
                    //step3
                    String dropScoreSql = "drop table score_old";
                    db.execSQL(dropScoreSql);
                    break;
                case 2:
                    //step1
                    String alterStudentSql = "alter table student rename to student_old";
                    db.execSQL(alterStudentSql);
                    //step2
                    String createNewStudentSql = "create table student(id integer primary key autoincrement, name varchar(64), course_id integer);";
                    db.execSQL(createNewStudentSql);
                    //step3
                    String dropStudentSql = "drop table student_old";
                    db.execSQL(dropStudentSql);
                    break;
                case 3:
                    db.execSQL("alter table student add column 'addr' vachar(100) default '北京'");
                    break;
                case 4:
                    db.beginTransaction();
                    db.execSQL("alter table student rename to student_backup");
                    db.execSQL("create table student(id integer primary key autoincrement, name varchar(64), course_id integer)");
                    db.execSQL("insert into student select id, name, course_id from student_backup");
                    db.execSQL("drop table student_backup");
                    db.setTransactionSuccessful();
                    db.endTransaction();
                    break;
                default:
                    Logger.get().i(TAG, String.format("online db unexist version(%s)", oldVersion));
                    break;
            }
        }
    }
}
