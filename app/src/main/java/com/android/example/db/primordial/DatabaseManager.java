package com.android.example.db.primordial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.android.example.log.Logger;


/**
 * @author:無忌
 * @date:2020/10/28
 * @description:
 */
public class DatabaseManager {
    private static final String TAG = "DatabaseManager";
    private static volatile DatabaseManager ourInstance;
    private SQLiteDatabase database;

    public static DatabaseManager getInstance(Context context) {
        if (ourInstance == null) {
            synchronized (DatabaseManager.class) {
                if (ourInstance == null) {
                    ourInstance = new DatabaseManager(context);
                }
            }
        }
        return ourInstance;
    }

    private DatabaseManager(Context context) {
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        database = databaseHelper.getWritableDatabase();
    }

    public void insertStudentAndCourse() {
        ContentValues course = new ContentValues();
        course.put("name", "语文");
        course.put("score", 100);
        long courseId = database.insert("course", null, course);
        ContentValues student = new ContentValues();
        student.put("name", "张三");
        student.put("course_id", courseId);
        database.insert("student", null, student);
    }

    public void updateStudent() {
        ContentValues student = new ContentValues();
        student.put("name", "李四");
        int result = database.update("student", student, "id=?", new String[]{"1"});
        Logger.get().d(TAG, "updateStudent result:" + result);
    }

    public void querStudentAndCourse() {
        Cursor cursor = database.rawQuery("select * from student left join course on student.course_id = course.id", null);
        if (cursor != null) {
            try {
                while (cursor.moveToNext()) {
                    int studentIdIndex = cursor.getColumnIndex("id");
                    int studentNameIndex = cursor.getColumnIndex("name");
                    int studentCourseIdIndex = cursor.getColumnIndex("course_id");
                    int courseIdIndex = cursor.getColumnIndex("id");
                    int courseNameIndex = cursor.getColumnIndex("name");
                    int scoreIndex = cursor.getColumnIndex("score");
                    int studentId = cursor.getInt(studentIdIndex);
                    String studentName = cursor.getString(studentNameIndex);
                    int studentCourseId = cursor.getInt(studentCourseIdIndex);
                    int courseId = cursor.getInt(courseIdIndex);
                    String courseName = cursor.getString(courseNameIndex);
                    int score = cursor.getInt(scoreIndex);
                    Logger.get().d(TAG, String.format("querStudentAndCourse studentId:%s,studentName:%s,studentCourseId:%s,courseId:%s,courseName:%s,score:%s",
                            studentId, studentName, studentCourseId, courseId, courseName, score));

                }
            } finally {
                cursor.close();
            }
        }
    }

    public void deleteCourse() {
        database.delete("course", "id=?", new String[]{"3"});
    }
}
