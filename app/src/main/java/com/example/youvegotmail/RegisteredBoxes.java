package com.example.youvegotmail;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegisteredBoxes extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ArrayList<POBoxes> poBoxData;
    private POBoxAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_boxes);

        int gridColumnCount =
                getResources().getInteger(R.integer.grid_column_count);
        // Initialize the RecyclerView.
        // Member variables.
        RecyclerView mRecyclerView = findViewById(R.id.recyclerView);

        // Set the Layout Manager.
        mRecyclerView.setLayoutManager(new
                GridLayoutManager(this, gridColumnCount));

        // Initialize the ArrayList that will contain the data.
        poBoxData = new ArrayList<>();

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
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        return true;
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

    /**
     * onClick method for th FAB that resets the data.
     *
     * @param view The button view that was clicked.
     */
    public void resetpoBoxes(View view) {
        initializeData();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String userInput = newText.toLowerCase();
        List<POBoxes> newList = new ArrayList<>();

        for (POBoxes poBoxes : poBoxData) {
            String pobox_titles = POBoxes.getTitle().toLowerCase();
            if(pobox_titles.contains(userInput)) {
                newList.add(poBoxes);
            }
        }
        mAdapter.updateArrayList(newList);
        return false;
    }
}
