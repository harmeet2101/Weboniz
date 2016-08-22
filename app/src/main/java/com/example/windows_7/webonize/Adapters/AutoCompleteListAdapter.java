package com.example.windows_7.webonize.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.windows_7.webonize.Holders.AutoCompleteViewHolder;
import com.example.windows_7.webonize.R;

import java.util.ArrayList;
import java.util.List;

public class AutoCompleteListAdapter extends BaseAdapter implements Filterable {


    private Context context;
    private List<String> suggestions;
    private List<String> suggestions_filter=new ArrayList<>();
    private List<String>suggestions_original=new ArrayList<>();
    private ItemFilter mFilter = new ItemFilter();

    public AutoCompleteListAdapter(Context context,List<String> suggestions){
        this.context=context;
        this.suggestions=suggestions;
        suggestions_original=suggestions;
        suggestions_filter=suggestions;
    }

    @Override
    public int getCount() {
        return suggestions_filter.size();
    }

    @Override
    public Object getItem(int position) {
        return suggestions_filter.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        AutoCompleteViewHolder viewHolder;
        if(convertView==null){
            LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=layoutInflater.inflate(R.layout.layout_place_list_data,null);
            viewHolder=new AutoCompleteViewHolder();
            viewHolder.name=(TextView)convertView.findViewById(R.id.text);
            viewHolder.dividerLine=convertView.findViewById(R.id.dividerLine);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(AutoCompleteViewHolder)convertView.getTag();
        }
        viewHolder.name.setText(suggestions_filter.get(position));
        if(position==suggestions.size()-1){
            viewHolder.dividerLine.setVisibility(View.INVISIBLE);
        }else
            viewHolder.dividerLine.setVisibility(View.VISIBLE);
        return convertView;
    }

    public void updateDataSource(List<String> suggestions){
        this.suggestions_filter=suggestions;
        notifyDataSetChanged();
    }


    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            String filterString = constraint.toString().toLowerCase();

            Filter.FilterResults results = new FilterResults();
            if (constraint == null || constraint.length() == 0) {


                results.values = suggestions_original;
                results.count = suggestions_original.size();
            }
            else {
                final List<String> list = suggestions_original;

                int count = list.size();
                final ArrayList<String> nlist = new ArrayList<String>(count);

                String filterableString;

                for (int i = 0; i < count; i++) {

                    filterableString = list.get(i);

                    if (filterableString.toLowerCase().startsWith(filterString)) {
                        nlist.add(filterableString);
                    }
                }

                results.values = nlist;
                results.count = nlist.size();
            }


            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            if (results.count == 0) {
                notifyDataSetInvalidated();
            } else {
                suggestions_filter = (ArrayList<String>) results.values;
                notifyDataSetChanged();
            }
        }

    }
    @Override
    public Filter getFilter() {
        return mFilter;
    }
}
