package com.example.windows_7.webonize.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.windows_7.webonize.Model.Place;
import com.example.windows_7.webonize.Model.PlaceList;
import com.example.windows_7.webonize.R;
import com.example.windows_7.webonize.Utils.KeyboardUtils;

import java.util.List;

/**
 * Created by windows-7 on 8/20/2016.
 */
public class PlaceListRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


    private List<Place> placeLists;
    private Context context;
    private static final int ITEM_VIEW_TYPE__LOADING = 0;
    private static final int ITEM_VIEW_TYPE__EMPTY_LIST = 1;
    private static final int ITEM_VIEW_TYPE__NORMAL = 2;

    public PlaceListRecyclerViewAdapter(List<Place> placeLists,Context context){
        this.context=context;
        this.placeLists=placeLists;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case ITEM_VIEW_TYPE__LOADING : {
                final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_loading_place_list, parent, false);
                viewHolder = new BulkViewHolder(view);
                break;
            }
            case ITEM_VIEW_TYPE__EMPTY_LIST : {
                final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_empty_place_list, parent, false);
                fillParent(parent, view);

                viewHolder = new BulkViewHolder(view);
                break;
            }
            case ITEM_VIEW_TYPE__NORMAL : {
                View itemView = LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.layout_place_list_data, parent, false);
                viewHolder = new PlaceListViewHolder(itemView);
                break;
            }

            default : {
                viewHolder = null;
            }
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        if (placeLists == null) {

        } else if (placeLists.isEmpty()) {

        } else {
            bindViewHolder_Normal((PlaceListViewHolder) viewHolder, position);
        }
    }
    @Override
    public int getItemCount() {
        int count = 0;
        if (placeLists == null) {
            count = 1;
        } else if (placeLists.isEmpty()) {
            count = 1;
        } else {
            count = placeLists.size();
        }
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        int itemViewType = -1;
        if (placeLists == null) {
            itemViewType = ITEM_VIEW_TYPE__LOADING;
        } else if (placeLists.isEmpty()) {
            itemViewType = ITEM_VIEW_TYPE__EMPTY_LIST;
        } else {
            itemViewType = ITEM_VIEW_TYPE__NORMAL;
        }
        return itemViewType;
    }
    public void updateDataSource(List<Place> placeLists) {
        this.placeLists = placeLists;
        notifyDataSetChanged();
    }

    private void fillParent(ViewGroup parent, View view) {
        int parentHeight = parent.getHeight();
        ViewGroup.LayoutParams viewParams = view.getLayoutParams();
        viewParams.height = parentHeight;
        view.setLayoutParams(viewParams);
    }

    public class BulkViewHolder extends RecyclerView.ViewHolder {
        public BulkViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    KeyboardUtils.tryToHideKeyboard((Activity) context);
                }
            });
        }
    }

    public void bindViewHolder_Normal(PlaceListViewHolder viewHolder, int position) {
        String name=placeLists.get(position).getName();
        viewHolder.placeListItem.setText(name);
        if (position==placeLists.size()-1){
            viewHolder.dividerLine.setVisibility(View.INVISIBLE);
        }else
            viewHolder.dividerLine.setVisibility(View.VISIBLE);
    }

    public  class PlaceListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView placeListItem;
        public View dividerLine;
        public PlaceListViewHolder(View itemView) {
            super(itemView);
            this.placeListItem = (TextView) itemView.findViewById(R.id.text);
            this.dividerLine=itemView.findViewById(R.id.dividerLine);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mClickListener.onItemClick(getAdapterPosition());
        }
    }


    public void setOnClickListener(OnItemClickListener mClickListener){
        this.mClickListener=mClickListener;
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    private OnItemClickListener mClickListener;
}
