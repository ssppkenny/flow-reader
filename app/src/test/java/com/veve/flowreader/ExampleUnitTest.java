package com.veve.flowreader;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.test.InstrumentationRegistry;

import com.veve.flowreader.dao.sqlite.BookStorageHelper;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void databaseCheck() {
        Context context = InstrumentationRegistry.getTargetContext();
        Context dbcontext = context.getApplicationContext();
        SQLiteDatabase database = new BookStorageHelper(dbcontext).getWritableDatabase();
        database.execSQL("create table A (id integer primary key autoincrement, col text)");
    }

}