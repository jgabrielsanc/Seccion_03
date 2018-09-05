package com.android.jsanchez.seccion_03;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> names;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        names = getAllNames();

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        adapter = new MyAdapter(names, R.layout.recycler_view_item, new MyAdapter.onItemClickListener() {
            @Override
            public void onItemClick(String name, int position) {

                deleteName(position);
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
                this.addNames(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void addNames(int position) {
        names.add(position, "New name " + (++counter));
        adapter.notifyItemInserted(position);
        layoutManager.scrollToPosition(position);
    }

    private void deleteName(int position) {
        names.remove(position);
        adapter.notifyItemRemoved(position);
    }

    private List<String> getAllNames() {
        return new ArrayList<String>() {{
            add("Jesus");
            add("Maria");
            add("Jose");
            add("Carla");
            add("Marta");
            add("Luis");
        }};
    }
}
