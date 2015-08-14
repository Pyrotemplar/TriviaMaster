package com.pyrotemplar.triviamaster.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.pyrotemplar.triviamaster.Objects.CategoryItem;
import com.pyrotemplar.triviamaster.Adapters.CategoryItemGridAdapter;
import com.pyrotemplar.triviamaster.Activities.QuestionsActivity;
import com.pyrotemplar.triviamaster.R;

import java.util.ArrayList;



/**
 * Created by Pyrotemplar on 8/14/2015.
 */
public class CategoryGridFragment extends android.support.v4.app.Fragment implements CategoryItemGridAdapter.ClickListener {

    private static final String CATEGORY_POSITION = "categoryPosition";

    private RecyclerView categoryGrid;
    private CategoryItemGridAdapter categoryItemGridAdapter;

    public CategoryGridFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_category_grid, container, false);
        categoryGrid = (RecyclerView) view.findViewById(R.id.categoryGrid);
        categoryGrid.setHasFixedSize(true);
        categoryItemGridAdapter = new CategoryItemGridAdapter(getActivity(), getCategories());
        categoryItemGridAdapter.setClickListener(this);
        categoryGrid.setAdapter(categoryItemGridAdapter);
        categoryGrid.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        return view;
    }

    public static ArrayList<CategoryItem> getCategories() {
        ArrayList<CategoryItem> itemList = new ArrayList<>();

        int[] icons = {R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher};
        int tempIcon = R.drawable.ic_launcher;
        String[] tittles = {"History", "Math", "Science", "Music", "Video Games", "Computers", "Entertainment", "Animals", "Food"};

        for (int i = 0; i < tittles.length; i++) {
            CategoryItem item = new CategoryItem();

            item.setIcon(tempIcon);
            item.setTittle(tittles[i]);

            itemList.add(item);
        }
        return itemList;

    }


    @Override
    public void itemClicked(View view, int position) {

        Intent intent = new Intent(getActivity(), QuestionsActivity.class);
        intent.putExtra(CATEGORY_POSITION, position);
        getActivity().startActivityForResult(intent, 1);
    }
}
