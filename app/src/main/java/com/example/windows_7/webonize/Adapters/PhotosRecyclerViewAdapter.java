package com.example.windows_7.webonize.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.windows_7.webonize.R;
import com.example.windows_7.webonize.Utils.FileUtils;

import java.util.List;

/**
 * Created by windows-7 on 8/21/2016.
 */
public class PhotosRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;
    private List<Bitmap> bitmaps;
    private static final int ITEM_VIEW_TYPE__LOADING = 0;
    private static final int ITEM_VIEW_TYPE__EMPTY_LIST = 1;
    private static final int ITEM_VIEW_TYPE__NORMAL = 2;

    public PhotosRecyclerViewAdapter(Context context,List<Bitmap> bitmaps){
        this.context=context;
        this.bitmaps=bitmaps;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        switch (viewType) {
            case ITEM_VIEW_TYPE__LOADING : {
                final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_loading_photos_list, parent, false);

                viewHolder = new BulkViewHolder(view);
                break;
            }
            case ITEM_VIEW_TYPE__EMPTY_LIST : {
                final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_empty_place_list, parent, false);
                ((TextView) view.findViewById(R.id.empty_section_TextView)).setText(R.string.photos_empty_textview);
                viewHolder = new BulkViewHolder(view);
                break;
            }
            case ITEM_VIEW_TYPE__NORMAL : {
                View itemView = LayoutInflater.from(parent.getContext()).
                        inflate(R.layout.layout_list_items, parent, false);
                viewHolder = new PhotosViewHolder(itemView);
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
        if (bitmaps == null) {

        } else if (bitmaps.isEmpty()) {

        } else {
            bindViewHolder_Normal((PhotosViewHolder) viewHolder, position);
        }
    }
    @Override
    public int getItemCount() {
        int count = 0;
        if (bitmaps == null) {
            count = 1;
        } else if (bitmaps.isEmpty()) {
            count = 1;
        } else {
            count = bitmaps.size();
        }
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        int itemViewType = -1;
        if (bitmaps == null) {
            itemViewType = ITEM_VIEW_TYPE__LOADING;
        } else if (bitmaps.isEmpty()) {
            itemViewType = ITEM_VIEW_TYPE__EMPTY_LIST;
        } else {
            itemViewType = ITEM_VIEW_TYPE__NORMAL;
        }
        return itemViewType;
    }
    public void updateDataSource(List<Bitmap> bitmaps) {
        this.bitmaps = bitmaps;
        notifyDataSetChanged();
    }

    public class BulkViewHolder extends RecyclerView.ViewHolder {
        public BulkViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void bindViewHolder_Normal(PhotosViewHolder viewHolder, int position) {
        viewHolder.imageView.setImageBitmap(bitmaps.get(position));

    }

    public  class PhotosViewHolder extends RecyclerView.ViewHolder{

        private ImageView imageView;

        public PhotosViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.imageview);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        FileUtils.saveImageToSDCard((Activity) context,bitmaps.get(getPosition()));

                }
            });
        }
    }
}
