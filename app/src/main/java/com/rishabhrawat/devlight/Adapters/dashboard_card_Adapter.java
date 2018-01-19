package com.rishabhrawat.devlight.Adapters;

import android.content.Context;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rishabhrawat.devlight.Account_Activity.Dashboard.Dashboarddata;
import com.rishabhrawat.devlight.R;

import java.util.List;

/**
 * Created by rishabh on 27-Oct-17.
 */

public class dashboard_card_Adapter extends RecyclerView.Adapter<dashboard_card_Adapter.ViewHolder> {

    List<Dashboarddata> dashboarddataList;
    Context mContext;

    public dashboard_card_Adapter(List<Dashboarddata> dashboarddataList, Context mContext) {
        this.dashboarddataList = dashboarddataList;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dashboard_card,parent,false);


        return new ViewHolder(v);


    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Dashboarddata dashboarddata=dashboarddataList.get(position);

        int count=dashboarddata.getCount();

        Glide.with(mContext).load(dashboarddata.getProjectImage()).into(holder.projectImage);
        holder.projectHeader.setText(dashboarddata.getProjectHeader());
        holder.projectLikes.setText(Integer.toString(count)+" Likes");



    }

    @Override
    public int getItemCount() {
        return dashboarddataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        ImageView projectImage;
        TextView projectHeader;
        TextView projectLikes;


        public ViewHolder(View itemView) {
            super(itemView);

            projectImage=(ImageView) itemView.findViewById(R.id.project_image);
            projectHeader=(TextView)itemView.findViewById(R.id.title);
            projectLikes=(TextView) itemView.findViewById(R.id.count);
        }
    }
}
