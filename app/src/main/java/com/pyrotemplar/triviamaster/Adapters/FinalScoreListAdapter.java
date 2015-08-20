package com.pyrotemplar.triviamaster.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pyrotemplar.triviamaster.Objects.FinalQuestionItem;
import com.pyrotemplar.triviamaster.R;

import java.util.ArrayList;

/**
 * Created by Pyrotemplar on 8/17/2015.
 */
public class FinalScoreListAdapter extends RecyclerView.Adapter<FinalScoreListAdapter.ViewHolder> {
    ArrayList<FinalQuestionItem> finalQuestionItemsList = new ArrayList<>();
    FinalQuestionItem finalQuestionItem;

    public FinalScoreListAdapter(ArrayList<FinalQuestionItem> finalQuestionItems) {

        finalQuestionItemsList = finalQuestionItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.final_score_question_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        finalQuestionItem = finalQuestionItemsList.get(position);

        holder.questionItemView.setText(finalQuestionItem.getQuestionText());
        holder.answerItemView.setText(finalQuestionItem.getRightAnswer());
        holder.userAnswerItemView.setText(finalQuestionItem.getUserAnswer());
    }

    @Override
    public int getItemCount() {
        return finalQuestionItemsList.size();
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

