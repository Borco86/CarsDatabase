package com.example.rent.carsdatabase.listing;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rent.carsdatabase.CarsDatabaseOpenHelper;
import com.example.rent.carsdatabase.CarsTableContract;
import com.example.rent.carsdatabase.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by RENT on 2017-03-27.
 */

public class ListingFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, OnDeleteButtonClickListener {

    private static final String QUERY_KEY = "query_key";
    public static final int CARS_LOADER = 1;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private Unbinder unbinder;

    private CarsDatabaseOpenHelper openHelper;
    private RecyclerViewCursorAdapter adapter;

    public static Fragment getInstance(String query) {
        ListingFragment fragment = new ListingFragment();
        Bundle arguments = new Bundle();
        arguments.putString(QUERY_KEY, query);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openHelper = new CarsDatabaseOpenHelper(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_listing, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        adapter = new RecyclerViewCursorAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
        getLoaderManager().initLoader(CARS_LOADER, getArguments(), this);
        adapter.setOnCarItemClickListener((OnCarItemClickListener) getActivity());
        adapter.setOnDeleteButtonClickListener(this);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String query = getArguments().getString(QUERY_KEY);
        return new CursorLoader(getActivity(), CarsTableContract.DATA_CONTENT_URI, null, CarsTableContract.COLUMN_MAKE + " LIKE ?",
                new String[]{query + "%"}, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.setCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.setCursor(null);
    }

    @Override
    public void onDeleteButtonClick(String id) {
        getContext().getContentResolver()
                .delete(CarsTableContract.DATA_CONTENT_URI
                        .buildUpon()
                        .appendPath(id)
                        .build(), null, null);
    }
}
