package com.example.youvegotmail;

import android.app.SearchManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.widget.SearchView;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RegisteredBoxes extends AppCompatActivity {

    private ArrayList<POBoxes> poBoxData;
    private POBoxAdapter mAdapter;
    TextView textview;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_boxes);

        int gridColumnCount =
                getResources().getInteger(R.integer.grid_column_count);

        // Initialize the RecyclerView.
        // Member variables.
        RecyclerView mRecyclerView = findViewById(R.id.r_box_list);

        // Set the Layout Manager.
        mRecyclerView.setLayoutManager(new
                GridLayoutManager(this, gridColumnCount));

        // Initialize the ArrayList that will contain the data.
        poBoxData = new ArrayList<>();
        recyclerView = findViewById(R.id.r_box_list);
        recyclerView.setAdapter(mAdapter);

        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new POBoxAdapter(this, poBoxData);
        mRecyclerView.setAdapter(mAdapter);

        // Get the data.
        initializeData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        SearchManager searchManager = (SearchManager)
                getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        EditText searchEditText = (EditText) searchView.findViewById(com.google.android.material.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.colorText));
        searchEditText.setHintTextColor(getResources().getColor(R.color.colorText));
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
        if (searchManager != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        }

        searchView.setSubmitButtonEnabled(true);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });
        return true;
    }

    private void filter(String text) {

        ArrayList<POBoxes> filteredList = new ArrayList<>();

        for (POBoxes item : poBoxData) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                textview = findViewById(R.id.overView);
                recyclerView.setVisibility(View.VISIBLE);
                textview.setVisibility(View.GONE);
                filteredList.add(item);
                mAdapter.filterList(filteredList);
            }
            else if (filteredList.isEmpty()) {
                textview = findViewById(R.id.overView);
                recyclerView.setVisibility(View.GONE);
                textview.setVisibility(View.VISIBLE);
            }
        }
    }
     private void initializeData() {

        // Get the resources from the XML file.
        String[] poBoxList = getResources()
                .getStringArray(R.array.pobox_titles);
        String[] poBoxInfo = getResources()
                .getStringArray(R.array.pobox_info);
        TypedArray poBoxImageResources = getResources()
                .obtainTypedArray(R.array.pobox_images);

        // Clear the existing data (to avoid duplication).
        poBoxData.clear();

        for (int i = 0; i < poBoxList.length; i++) {
            poBoxData.add(new POBoxes(poBoxList[i], poBoxInfo[i],
                    poBoxImageResources.getResourceId(i, 0)));
        }

        // Recycle the typed array.
        poBoxImageResources.recycle();

        // Notify the adapter of the change.
        mAdapter.notifyDataSetChanged();
    }

}
