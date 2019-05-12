package com.veve.flowreader.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.veve.flowreader.Constants;
import com.veve.flowreader.R;
import com.veve.flowreader.dao.BookRecord;
import com.veve.flowreader.model.BooksCollection;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    BookListAdapter bookListAdapter;

    BookGridAdapter bookGridAdapter;

    int columnsNumber;

    SharedPreferences preferences;

    @Override
    protected void onNewIntent(Intent intent) {
        long bookId = intent.getLongExtra(Constants.BOOK_ID, 0);
        if (bookId > 0) {
            bookGridAdapter.removeBook(bookId);
            bookListAdapter.removeBook(bookId);
        }
    }

        @Override
    protected void onPostResume() {
        super.onPostResume();
        bookListAdapter.notifyDataSetChanged();
        bookGridAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        preferences = getPreferences(MODE_PRIVATE);

        bookListAdapter = new BookListAdapter();

        bookGridAdapter = new BookGridAdapter();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.i("MainActivity", "Is toolbar visible" + getSupportActionBar().isShowing());


        ////////////    ADD BOOKS BUTTON     /////////////////////////////////////////////////////
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentOne = new Intent(MainActivity.this, BrowseFilesActivity.class);
                intentOne.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intentOne);
            }
        });

        final GridView gridView = findViewById(R.id.grid);

        if (!preferences.contains(Constants.VIEW_TYPE)) {
            Log.d(getClass().getName(), "View type preference missing. Setting list as default");
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(Constants.VIEW_TYPE, Constants.GRID_VIEW_TYPE);
            editor.apply();
        }

        if (preferences.getInt(Constants.VIEW_TYPE, Constants.LIST_VIEW_TYPE) == Constants.LIST_VIEW_TYPE) {
            Log.d(getClass().getName(), "Preference view is list");
            gridView.setNumColumns(1);
            gridView.setAdapter(bookListAdapter);
        } else {
            gridView.setAdapter(bookGridAdapter);
            Log.d(getClass().getName(),
                    String.format("Preference view is grid. Adapter class is %S",
                            gridView.getAdapter()));
        }

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent ii = new Intent(MainActivity.this, PageActivity.class);
                BookRecord selectedBook = (BookRecord)parent.getItemAtPosition(position);
                ii.putExtra("filename", selectedBook.getUrl());
                ii.putExtra("position", position);
                startActivity(ii);
            }
        });

        gridView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                columnsNumber = (int) (gridView.getWidth()/
                        (Constants.BOOK_THUMB_WIDTH + 2 * Constants.BOOK_THUMB_HOR_PADDING));
                if(preferences.getInt(Constants.VIEW_TYPE, Constants.LIST_VIEW_TYPE) == Constants.LIST_VIEW_TYPE){
                    gridView.setNumColumns(1);
                } else {
                    gridView.setNumColumns(columnsNumber);
                }
            }
        });


        /////    SWITCH TO LIST VIEW     ///////////////////////////////

        ImageButton listBooksButton = findViewById(R.id.books_list);
        listBooksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(getClass().getName(), "List View");
                gridView.setNumColumns(1);
                gridView.setAdapter(bookListAdapter);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt(Constants.VIEW_TYPE, Constants.LIST_VIEW_TYPE);
                editor.apply();
            }
        });


        //////   SWITCH TO GRID VIEW   ////////////////////////////////

        ImageButton gridBooksButton = findViewById(R.id.books_grid);
        gridBooksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(getClass().getName(), "Grid View");
                gridView.setNumColumns(columnsNumber);
                gridView.setAdapter(bookGridAdapter);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt(Constants.VIEW_TYPE, Constants.GRID_VIEW_TYPE);
                editor.apply();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



///////////////////////   ADAPTERS   ///////////////////////////////////////////////////////////////

    public class BookListAdapter extends BaseAdapter {

        private BookListAdapter instance;
        private List<BookRecord> booksList;

        private BookListAdapter() {
            Log.i(this.getClass().getName(), "Constructing BookListAdapter");
            booksList = BooksCollection.getInstance(getApplicationContext()).getBooks();
            notifyDataSetChanged();
        }

        public void removeBook(long bookId) {
            for (BookRecord record : booksList) {
                if (record.getId() == bookId) {
                    booksList.remove(record);
                }
            }
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            return booksList.size();
        }

        @Override
        public Object getItem(int position) {
            return booksList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return booksList.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.items_list, container, false);
            }
            TextView textView = (TextView)((ConstraintLayout)convertView).getChildAt(0);
                textView.setText(booksList.get(position).getName());
            return convertView;
        }

        @Override
        public int hashCode() {
            return 1;
        }

        @Override
        public boolean equals(Object object) {
            return true;
        }

    }

//////////////////////////////////////////////////////////////////////////////////////////////////

    public class BookGridAdapter extends BaseAdapter {

        private BookGridAdapter instance;
        private List<BookRecord> booksList;

        private BookGridAdapter() {
            Log.i(this.getClass().getName(), "Constructing BookListAdapter");
            booksList = BooksCollection.getInstance(getApplicationContext()).getBooks();
            notifyDataSetChanged();
        }

        public void removeBook(long bookId) {
            BookRecord recordToDelete = null;
            for (BookRecord record : booksList) {
                if (record.getId() == bookId) {
                    recordToDelete = record;
                    break;
                }
            }
            booksList.remove(recordToDelete);
            notifyDataSetChanged();
        }

        @Override
        public int getCount() {
            if( booksList == null) {
                return booksList.size();
            } else {
                return booksList.size();
            }
        }

        @Override
        public Object getItem(int position) {
            return booksList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return booksList.get(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.items_list, container, false);
            }
            //convertView = getLayoutInflater().inflate(R.layout.book_preview, container, false);
            //TextView textView = convertView.findViewById(R.id.book);
            TextView textView = new TextView(getApplicationContext());
            textView.setText(booksList.get(position).getName());
            textView.setTextSize(12);
            textView.setTextColor(Color.CYAN);
            textView.setBackgroundColor(Color.YELLOW);
            return textView;
        }

        @Override
        public int hashCode() {
            return 1;
        }

        @Override
        public boolean equals(Object object) {
            return true;
        }

    }

///////////////////////////////////////////////////////////////////////////////////////////////////

}
