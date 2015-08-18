package com.pyrotemplar.triviamaster.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pyrotemplar.triviamaster.R;

import java.util.ArrayList;

/**
 * Created by Pyrotemplar on 8/17/2015.
 */
public class FinalScoreListAdapter extends RecyclerView.Adapter<FinalScoreListAdapter.ViewHolder> {
    ArrayList<FinalScoreItem> finalScoreItemsList = new ArrayList<>();
    FinalScoreItem finalScoreItem;

    public FinalScoreListAdapter(ArrayList<FinalScoreItem> finalScoreItems) {

        finalScoreItemsList = finalScoreItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.final_score_question_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        finalScoreItem = finalScoreItemsList.get(position);

        holder.questionItemView.setText(finalScoreItem.getQuestionText());
        holder.answerItemView.setText(finalScoreItem.getRightAnswer());
        holder.userAnswerItemView.setText(finalScoreItem.getUserAnswer());
    }

    @Override
    public int getItemCount() {
        return finalScoreItemsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView questionItemView;
        TextView answerItemView;
        TextView userAnswerItemView;
        public ViewHolder(View itemView) {
            super(itemView);
            questionItemView = (TextView) itemView.findViewById(R.id.questionItemView);
            answerItemView = (TextView) itemView.findViewById(R.id.answerItemView);
            userAnswerItemView = (TextView) itemView.findViewById(R.id.userAnswerItemView);
        }
    }
}

