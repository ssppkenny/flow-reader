package com.veve.flowreader.views;

import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.veve.flowreader.R;
import com.veve.flowreader.model.Book;
import com.veve.flowreader.model.BooksCollection;
import com.veve.flowreader.model.impl.mocksimple.MockBook;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    BookListAdapter bookListAdapter = new BookListAdapter();
    BookGridAdapter bookGridAdapter = new BookGridAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Log.i("MainActivity", "Is toolbar visible" + getSupportActionBar().isShowing());

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
        gridView.setAdapter(bookListAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i(getClass().getName(), String.format("Clicked view id %d position %d id %d",
                        view.getId(), position, id));
                Intent intentTwo = new Intent(MainActivity.this, PageActivity.class);
                intentTwo.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intentTwo);
            }
        });


        ImageButton listBooksButton = findViewById(R.id.books_list);
        listBooksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(getClass().getName(), "List View");
                gridView.setNumColumns(1);
                gridView.setAdapter(bookListAdapter);
            }
        });

        ImageButton gridBooksButton = findViewById(R.id.books_grid);
        gridBooksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(getClass().getName(), "Grid View");
                gridView.setNumColumns(5);
                gridView.setAdapter(bookGridAdapter);
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

    public class BookListAdapter extends BaseAdapter {

        private BookListAdapter instance;
        private List<Book> booksList;

        private BookListAdapter() {
            Log.i(this.getClass().getName(), "Constructing BookListAdapter");
//            booksList = BooksCollection.getInstance().getBooks();
            booksList = new ArrayList<Book>();
            booksList.add(new MockBook());
            booksList.add(new MockBook());
            booksList.add(new MockBook());
            booksList.add(new MockBook());
            booksList.add(new MockBook());
            booksList.add(new MockBook());
            booksList.add(new MockBook());
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

    public class BookGridAdapter extends BaseAdapter {

        private BookListAdapter instance;
        private List<Book> booksList;

        private BookGridAdapter() {
            Log.i(this.getClass().getName(), "Constructing BookListAdapter");
//            booksList = BooksCollection.getInstance().getBooks();
            booksList = new ArrayList<Book>();
            booksList.add(new MockBook());
            booksList.add(new MockBook());
            booksList.add(new MockBook());
            booksList.add(new MockBook());
            booksList.add(new MockBook());
            booksList.add(new MockBook());
            booksList.add(new MockBook());
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
            textView.setWidth(200);
            textView.setHeight(300);
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

}
