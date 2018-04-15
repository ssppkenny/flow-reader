package com.veve.flowreader;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.veve.flowreader.dao.sqlite.BookStorageHelper;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Test
    public void useAppContext() throws Exception {
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.veve.flowreader", appContext.getPackageName());
    }

    @Test
    public void databaseCheck() {
        Context context = InstrumentationRegistry.getTargetContext();
        Context dbcontext = context.getApplicationContext();
        SQLiteDatabase database = new SQLiteOpenHelper(dbcontext, "TestDB", null, 1){
            @Override
            public void onCreate(SQLiteDatabase db) {}
            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
        }.getWritableDatabase();
        database.execSQL("CREATE TABLE IF NOT EXISTS A (id integer primary key autoincrement, col text)");
        database.beginTransaction();
        ContentValues values = new ContentValues();
        values.put("col", "test");
        database.insert("A", null, values);
        database.endTransaction();
        Cursor c = database.query("A",null, null, null, null, null, null);
        assert(!c.isAfterLast());
        c.moveToNext();
        assert(!c.isAfterLast());
        assertEquals(c.getString(1), "test");
    }

}
