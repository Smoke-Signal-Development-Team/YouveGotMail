package com.example.youvegotmail;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

class POBoxAdapter extends RecyclerView.Adapter<POBoxAdapter.ViewHolder> implements Filterable {

    // Member variables.
    private ArrayList<POBoxes> poBoxData;
    private List<POBoxes> poBoxDataFull;
    private Context mContext;

    POBoxAdapter(Context context, ArrayList<POBoxes> poBoxData) {
        this.poBoxData = poBoxData;
        this.mContext = context;
        poBoxDataFull = new ArrayList<>(poBoxData);
    }

    /**
     * Required method for creating the viewholder objects.
     *
     * @param parent The ViewGroup into which the new View will be added
     *               after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return The newly created ViewHolder.
     */

    @NonNull
    @Override
    public POBoxAdapter.ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.list_item, parent, false));
    }

    /**
     * Required method that binds the data to the viewholder.
     *
     * @param holder The viewholder into which the data should be put.
     * @param position The adapter position.
     */

    @Override
    public void onBindViewHolder(POBoxAdapter.ViewHolder holder,
                                 int position) {
        // Get current p.o.box.
        POBoxes currentPOBoxes = poBoxData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentPOBoxes);
    }

    /**
     * Required method for determining the size of the data set.
     *
     * @return Size of the data set.
     */

    @Override
    public int getItemCount() {
        return poBoxData.size();
    }

    /**
     * ViewHolder class that represents each row of data in the RecyclerView.
     */

    class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        // Member Variables for the TextViews
        private TextView mTitleText;
        private TextView mInfoText;
        private ImageView poBoxImage;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         *
         * @param itemView The rootview of the list_item.xml layout file.
         */

        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            mTitleText = itemView.findViewById(R.id.title);
            mInfoText = itemView.findViewById(R.id.subTitle);
            poBoxImage = itemView.findViewById(R.id.PO_BOX_IMAGE);

            // Set the OnClickListener to the entire view.
            itemView.setOnClickListener(this);

        }

        void bindTo(POBoxes currentPOBoxes){
            // Populate the textviews with data.
            mTitleText.setText(currentPOBoxes.getTitle());
            mInfoText.setText(currentPOBoxes.getInfo());

            // Load the images into the ImageView using the Glide library.
            Glide.with(mContext).load(
                    currentPOBoxes.getImageResource()).into(poBoxImage);
        }

        /**
         * Handle click to show DetailActivity.
         *
         * @param view View that is clicked.
         */

        @Override
        public void onClick(View view) {
            POBoxes currentPOBoxes = poBoxData.get(getAdapterPosition());
            //Change to DetailActivity.class for future details page
                Intent detailIntent = new Intent(mContext, MailType.class);
                detailIntent.putExtra("title", currentPOBoxes.getTitle());
                /*detailIntent.putExtra("image_resource",
                        currentPOBoxes.getImageResource());*/
                detailIntent.putExtra("info", currentPOBoxes.getInfo());
            String mTitle = currentPOBoxes.getTitle();
            if (mTitle.equals("P.O. Box# 0001")){
                return;
            }
            else {
                mContext.startActivity(detailIntent);
            }
        }
    }

    public void filterList(ArrayList<POBoxes> filteredList) {
        poBoxData = filteredList;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            List<POBoxes> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(poBoxDataFull);
            }
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (POBoxes item : poBoxDataFull) {
                    if(item.getTitle().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            poBoxData.clear();
            poBoxData.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

}
