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
public class NavigationItemAdapter extends RecyclerView.Adapter<NavigationItemAdapter.MyViewHolder> {

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
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.navigation_item_row, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        NavigationItem item = items.get(position);
        holder.tittle.setText(item.getTittle());
        holder.icon.setImageResource(item.getIconId());
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @Override
    public int getItemCount() {

        return items.size();

    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tittle;
        ImageView icon;

        public MyViewHolder(View itemView) {
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

    public interface ClickListener {
        public void itemClicked(View view, int position);
    }
}
