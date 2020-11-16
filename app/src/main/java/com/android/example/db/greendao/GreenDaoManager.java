package com.android.example.db.greendao;

import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;

import com.android.example.R;
import com.android.example.common.log.Logger;

import org.greenrobot.greendao.database.Database;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author:無忌
 * @date:2020/10/29
 * @description:
 */
class GreenDaoManager {
    private static final String TAG = "GreenDaoManager";
    private static final String DB_NAME = "notes_db";
    public static final boolean CHANGE_DB_PATH = false;
    private static volatile GreenDaoManager ourInstance;
    private DaoSession daoSession;

    static GreenDaoManager getInstance(Context context) {
        if (ourInstance == null) {
            synchronized (GreenDaoManager.class) {
                if (ourInstance == null) {
                    ourInstance = new GreenDaoManager(context);
                }
            }
        }
        return ourInstance;
    }

    private GreenDaoManager(Context context) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(contextWrapper(context), DB_NAME) {
            @Override
            public void onUpgrade(Database db, int oldVersion, int newVersion) {
                //super.onUpgrade(db, oldVersion, newVersion);
                //数据库升级操作
            }
        };
        SQLiteDatabase database = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }

    private Context contextWrapper(Context context) {
        if (CHANGE_DB_PATH) {
            String dbpath = context.getExternalFilesDir(null).getAbsolutePath() + File.separator + "database";
            File dbDir = new File(dbpath);
            if (!dbDir.exists()) {
                dbDir.mkdir();
            }
            return new ContextWrapper(context) {
                @Override
                public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory) {
                    return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), null);
                }

                @Override
                public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory, DatabaseErrorHandler errorHandler) {
                    return SQLiteDatabase.openOrCreateDatabase(getDatabasePath(name), null);
                }

                @Override
                public File getDatabasePath(String name) {
                    if (dbDir != null && dbDir.exists()) {
                        boolean isFileCreateSuccess = false;
                        String filePath = dbDir.getAbsolutePath() + File.separator + name;
                        File dbFile = new File(filePath);
                        if (!dbFile.exists()) {
                            try {
                                isFileCreateSuccess = dbFile.createNewFile();// 创建文件
                            } catch (IOException e) {
                                Logger.get().e(TAG, "getDatabasePath create file error", e);
                            }
                        } else {
                            isFileCreateSuccess = true;
                        }
                        if (isFileCreateSuccess) {
                            return dbFile;
                        } else {
                            return null;
                        }
                    }
                    return super.getDatabasePath(name);
                }
            };
        }
        return context;
    }

    public void insertNote() {
        Note note = new Note();
        note.setText("抗美援朝");
        note.setComment("谁是最可爱的人");
        note.setDate(new Date());
        long id = daoSession.insert(note);
        Logger.get().d(TAG, "insertNote id:" + id);
    }

    public void updateNote() {
        Note note = new Note();
        note.setId(1L);
        note.setText("反法西斯战争");
        note.setComment("打到小日本");
        note.setDate(new Date());
        daoSession.update(note);
    }

    public void queryNote() {
        List<Note> noteList = daoSession.queryRaw(Note.class, "");
        for (Note note : noteList) {
            Logger.get().d(TAG, "queryNote " + note.toString());
        }

    }

    public void deleteNote() {
        daoSession.getNoteDao().deleteByKey(1L);
    }

    public void deleteDb() {
        //删除所有表
        NoteDao.dropTable(daoSession.getDatabase(), true);
    }

    public static void importDb(Context context) {
        String dbPath = "/data/data/" + context.getPackageName() + File.separator + DB_NAME + ".db";
        File dbFile = new File(dbPath);
        boolean exists = dbFile.exists();
        Logger.get().d(TAG, "导入数据库 dbPath:" + dbPath + ", exists:" + exists);
        FileOutputStream fos = null;
        InputStream is = null;
        if (!exists) {
            try {
                fos = new FileOutputStream(dbFile);
                is = context.getResources().openRawResource(R.raw.notes_db);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, length);
                }
            } catch (Exception e) {
                Logger.get().e(TAG, "导入数据库失败", e);
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e) {
                    Logger.get().e(TAG, "导入数据库关流失败", e);
                }
            }

            SQLiteDatabase database = SQLiteDatabase.openOrCreateDatabase(dbPath, null);
            testQuery(database);
            Logger.get().i(TAG, "导入数据库成功");
        }
    }

    private static void testQuery(SQLiteDatabase database) {
        if (database != null) {
            Cursor cursor = null;
            try {
                cursor = database.rawQuery("select * from note", null);
                while (cursor.moveToNext()) {
                    long id = cursor.getLong(0);
                    String text = cursor.getString(1);
                    String comment = cursor.getString(2);
                    Date date = new Date(cursor.getLong(3));
                    Logger.get().d(TAG, String.format("Note(id=%s,text=%s,comment=%s,date=%s)", String.valueOf(id), text, comment, date.toString()));
                }
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }
    }
}