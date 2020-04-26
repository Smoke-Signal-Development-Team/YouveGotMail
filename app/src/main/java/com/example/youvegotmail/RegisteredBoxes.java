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
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;

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

        // If there is more than one column, disable swipe to dismiss
        int swipeDirs;
        if(gridColumnCount > 1){
            swipeDirs = 0;
        } else {
            swipeDirs = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        }

        // Helper class for creating swipe to dismiss and drag and drop
        // functionality
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper
                .SimpleCallback(
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |
                        ItemTouchHelper.DOWN | ItemTouchHelper.UP,
                swipeDirs) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView,
                                  @NonNull RecyclerView.ViewHolder viewHolder,
                                  @NonNull RecyclerView.ViewHolder target) {
                // Get the from and to positions.
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();

                // Swap the items and notify the adapter.
                Collections.swap(poBoxData, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }

            /**
             * Defines the swipe to dismiss functionality.
             *
             * @param viewHolder The viewholder being swiped.
             * @param direction The direction it is swiped in.
             */

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder,
                                 int direction) {
                // Remove the item from the dataset.
                poBoxData.remove(viewHolder.getAdapterPosition());
                // Notify the adapter.
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        });

        // Attach the helper to the RecyclerView.
        helper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
        SearchManager searchManager = (SearchManager)
                getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

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
