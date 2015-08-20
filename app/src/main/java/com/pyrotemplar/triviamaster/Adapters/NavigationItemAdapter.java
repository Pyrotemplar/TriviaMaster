package com.pyrotemplar.triviamaster.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.pyrotemplar.triviamaster.Objects.NavigationItem;
import com.pyrotemplar.triviamaster.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by Pyrotemplar on 8/13/2015.
 */
public class NavigationItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private LayoutInflater inflater;
    List<NavigationItem> items = Collections.emptyList();
    private Context context;
    private ClickListener clickListener;


    public NavigationItemAdapter(Context context, List<NavigationItem> items) {

        this.context = context;
        inflater = LayoutInflater.from(context);
        this.items = items;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_HEADER) {
            view = inflater.inflate(R.layout.navigation_drawer_header, parent, false);
            HeaderHolder holder = new HeaderHolder(view);
            return holder;
        } else {
            view = inflater.inflate(R.layout.navigation_item_row, parent, false);
            ItemHolder holder = new ItemHolder(view);
            return holder;

        }

    }

    @Override
    public int getItemViewType(int position) {

        return position == 0 ? TYPE_HEADER : TYPE_ITEM;


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof HeaderHolder) {

        } else {
            ItemHolder itemHolder = (ItemHolder) holder;
            NavigationItem item = items.get(position-1);
            itemHolder.tittle.setText(item.getTittle());
            itemHolder.icon.setImageResource(item.getIconId());
        }
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {

        return items.size()+1;

    }

    class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tittle;
        ImageView icon;

        public ItemHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            tittle = (TextView) itemView.findViewById(R.id.navigation_item_text);
            icon = (ImageView) itemView.findViewById(R.id.navigation_item_image);
        }

        @Override
        public void onClick(View view) {

            if (clickListener != null) {
                clickListener.itemClicked(view, getAdapterPosition());
            }
        }
    }

    class HeaderHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tittle;
        ImageView icon;

        public HeaderHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

            if (clickListener != null) {
                clickListener.itemClicked(view, getAdapterPosition());
            }
        }
    }


    public interface ClickListener {
        public void itemClicked(View view, int position);
    }
}
