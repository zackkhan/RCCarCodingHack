package com.woxthebox.draglistview.sample;

/**
 * Created by thdar on 10/22/2016.
 */


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DecorContentParent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class StageHolder extends RecyclerView.Adapter<StageHolder.ViewHolder> {

    Context mContext;
    ArrayList<StageButton> stageButtonList;

    public StageHolder(Context context, ArrayList<StageButton> stageButtons){
        mContext = context;
        stageButtonList = stageButtons;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stage_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (stageButtonList != null) {
            holder.stageText.setText(stageButtonList.get(position).getStageNum());
        }

        holder.background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context mContext = view.getContext();
                StageActivity.whichStage = position + 1;
                Log.d("myTag", StageActivity.whichStage + "");
                Intent go = new Intent(mContext, DescriptionActivity.class);
                mContext.startActivity(go);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(stageButtonList == null) {
            return 0;
        } else {
            return stageButtonList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageButton background;
        TextView stageText;

        public ViewHolder(View itemView) {
            super(itemView);
            stageText = (TextView) itemView.findViewById(R.id.stage_text);
            background = (ImageButton) itemView.findViewById(R.id.cardBackground);

        }
    }
}
