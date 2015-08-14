package com.pyrotemplar.triviamaster.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pyrotemplar.triviamaster.Objects.CategoryItem;
import com.pyrotemplar.triviamaster.R;

import java.util.ArrayList;

/**
 * Created by Pyrotemplar on 8/14/2015.
 */
public class CategoryItemGridAdapter extends RecyclerView.Adapter<CategoryItemGridAdapter.ViewHolderCategoryGrid> {

    private ArrayList<CategoryItem> categoryList = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private ClickListener clickListener;
    public CategoryItemGridAdapter(Context context, ArrayList<CategoryItem> categoryList){

        layoutInflater = layoutInflater.from(context);
        this.categoryList = categoryList;
    }

    @Override
    public ViewHolderCategoryGrid  onCreateViewHolder(ViewGroup parent, int viewType) {
       View view =  layoutInflater.inflate(R.layout.category_item_card, parent, false);
        ViewHolderCategoryGrid viewHolder = new ViewHolderCategoryGrid(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolderCategoryGrid holder, int position) {
        CategoryItem categoryItem = categoryList.get(position);
        holder.tittleView.setText(categoryItem.getTittle());
        holder.imageView.setImageResource(categoryItem.getIcon());

    }
    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    class ViewHolderCategoryGrid extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tittleView;
        private ImageView imageView;
        public ViewHolderCategoryGrid(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tittleView = (TextView) itemView.findViewById(R.id.catergoryNameView);
            imageView = (ImageView) itemView.findViewById(R.id.catergoryImage);

        }


        @Override
        public void onClick(View view) {
            if(clickListener!=null){
                clickListener.itemClicked(view, getAdapterPosition());
            }
        }
    }

    public interface ClickListener {
        public void itemClicked(View view, int position);
    }
}

