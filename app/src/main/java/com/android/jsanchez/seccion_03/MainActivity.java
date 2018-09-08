package com.android.jsanchez.seccion_03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Movie> movies;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movies = getAllMovies();

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        adapter = new MyAdapter(movies, R.layout.recycler_view_item, new MyAdapter.onItemClickListener() {
            @Override
            public void onItemClick(Movie movie, int position) {

                removeMovie(position);
//                Toast.makeText(MainActivity.this, name + " - " + position, Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.add_name:
                // Agregar al final de la lista
                this.addMovie(movies.size());
                // Agregar al inicio de la lista
//                this.addMovie(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void addMovie(int position) {
        movies.add(position, new Movie("New image " + (++counter), R.drawable.newmovie));
        adapter.notifyItemInserted(position);
        layoutManager.scrollToPosition(position);
    }

    private void removeMovie(int position) {
        movies.remove(position);
        adapter.notifyItemRemoved(position);
    }

    private List<Movie> getAllMovies() {
        return new ArrayList<Movie>() {{
            add(new Movie("Ben Hur", R.drawable.benhur));
            add(new Movie("DeadPool", R.drawable.deadpool));
            add(new Movie("Guardians of Galaxy", R.drawable.guardians));
            add(new Movie("Warcratf", R.drawable.warcraft));
        }};
    }
}
