package com.rishabhrawat.devlight.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rishabhrawat.devlight.Account_Activity.Explore.ExploreFragment;
import com.rishabhrawat.devlight.R;
import com.rishabhrawat.devlight.Account_Activity.Explore.exploredata;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by rishabh on 23-Oct-17.
 */

public class explore_card_Adapter extends RecyclerView.Adapter<explore_card_Adapter.ViewHolder> {


    List<exploredata> exploredatas;
    private Context mContext;


    public explore_card_Adapter(List<exploredata> exploredatas,Context mContext) {
        this.exploredatas = exploredatas;
        this.mContext=mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.explore_card,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        exploredata exploredata=exploredatas.get(position);

        Glide.with(mContext).load(exploredata.getProjectImage()).into(holder.projectImage);
        holder.projectHeader.setText(exploredata.getProjectHeader());
        holder.projectText.setText(exploredata.getProjectText());
        holder.devname.setText(exploredata.getDevname());
        holder.devemail.setText(exploredata.getDevemail());
        holder.profile_name.setText(exploredata.getDevname());


    }

    @Override
    public int getItemCount() {
        return exploredatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView projectImage;
        TextView projectHeader;
        TextView projectText;
        TextView devname;
        TextView devemail;
        TextView profile_name;




        public ViewHolder(View itemView) {
            super(itemView);

            projectImage=(ImageView) itemView.findViewById(R.id.cardimage);
            projectHeader=(TextView) itemView.findViewById(R.id.project_header);
            projectText=(TextView) itemView.findViewById(R.id.project_text);
            devname=(TextView) itemView.findViewById(R.id.devname);
            devemail=(TextView) itemView.findViewById(R.id.devemail);
            profile_name=(TextView) itemView.findViewById(R.id.profile_name);


        }
    }
}
