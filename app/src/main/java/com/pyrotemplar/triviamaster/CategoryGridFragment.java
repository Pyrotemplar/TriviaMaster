package com.pyrotemplar.triviamaster;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryGridFragment extends android.support.v4.app.Fragment {

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
        categoryGrid.setLayoutManager(new GridLayoutManager(getActivity(),2));
        categoryItemGridAdapter = new CategoryItemGridAdapter(getActivity(), getCategories());
        categoryGrid.setAdapter(categoryItemGridAdapter);
        return view;
    }

    public static ArrayList<CategoryItem> getCategories(){
        ArrayList<CategoryItem> itemList = new ArrayList<>();

        int[] icons = {R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher,R.drawable.ic_launcher};
        int tempIcon = R.drawable.ic_launcher;
        String[] tittles= {"History", "Math", "Science","Music", "Video Games", "Computers", "Entertainment", "Animals", "Food"};

        for (int i=0; i <tittles.length; i++){
            CategoryItem item = new CategoryItem();

            item.icon = tempIcon;
            item.tittle = tittles[i];

            itemList.add(item);
        }
        return itemList;

    }


}
