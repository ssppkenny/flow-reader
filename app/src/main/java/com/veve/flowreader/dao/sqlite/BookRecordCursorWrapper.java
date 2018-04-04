package com.veve.flowreader.dao.sqlite;

import android.database.Cursor;
import android.database.CursorWrapper;

/**
 * Created by ddreval on 4/4/2018.
 */
public class BookRecordCursorWrapper extends CursorWrapper {
    public BookRecordCursorWrapper(Cursor cursor) {
        super(cursor);
    }
}