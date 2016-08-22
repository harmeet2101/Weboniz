package com.example.windows_7.webonize.Fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.windows_7.webonize.Activities.SecondActivity;
import com.example.windows_7.webonize.Adapters.PlaceListRecyclerViewAdapter;
import com.example.windows_7.webonize.Model.Place;
import com.example.windows_7.webonize.R;
import com.example.windows_7.webonize.Utils.KeyboardUtils;

import java.util.List;

/**
 * Created by windows-7 on 8/20/2016.
 */
public class PlaceListFragment extends Fragment {

    private RecyclerView placeListRecyclerView;
    private PlaceListRecyclerViewAdapter adapter;
    private OnFragmentInteractionListener mListener;

    public PlaceListFragment(){}


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement " + PlaceListFragment.OnFragmentInteractionListener.class.getSimpleName());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentRootView = inflater.inflate(R.layout.layout_place_list_frag, container, false);

        placeListRecyclerView=(RecyclerView)fragmentRootView.findViewById(R.id.recyler);
        adapter = new PlaceListRecyclerViewAdapter(getPlaceLists(),getActivity());
        placeListRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        placeListRecyclerView.setScrollContainer(true);
        placeListRecyclerView.setAdapter(adapter);
        adapter.setOnClickListener(new PlaceListRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //// TODO: 8/20/2016 start map activity
                Bundle bundle = new Bundle();
                bundle.putSerializable("result", getPlaceLists().get(position));
                Intent intent = new Intent(getActivity(), SecondActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        return fragmentRootView;
    }

    public void showPlacesList(double lat,double lng) {

        if (mListener == null) {
            return;
        }
        adapter.updateDataSource(mListener.getPlaceList());
    }


    public interface OnFragmentInteractionListener {
        List<Place> getPlaceList();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private List<Place> getPlaceLists () {
        if (mListener != null) {
            return mListener.getPlaceList();
        }else
            return null;

    }


}
