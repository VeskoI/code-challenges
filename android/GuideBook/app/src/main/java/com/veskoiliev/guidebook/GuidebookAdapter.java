package com.veskoiliev.guidebook;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.veskoiliev.guidebook.model.Guide;

import java.util.ArrayList;
import java.util.List;

public class GuidebookAdapter extends RecyclerView.Adapter<GuidebookAdapter.GuideViewHolder> {

    private List<Guide> mData = new ArrayList<>(20);

    public void setData(List<Guide> guides) {
        mData.clear();
        mData.addAll(guides);
        notifyDataSetChanged();
    }

    @Override
    public GuideViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.guide_list_item, parent, false);
        return new GuideViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GuideViewHolder holder, int position) {
        Guide guide = mData.get(position);
        holder.name.setText(guide.getName());
        Picasso.with(holder.icon.getContext()).load(guide.getIconUrl()).into(holder.icon);
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    static class GuideViewHolder extends RecyclerView.ViewHolder {

        private ImageView icon;
        private TextView name;

        public GuideViewHolder(View itemView) {
            super(itemView);

            icon = (ImageView) itemView.findViewById(R.id.guide_icon);
            name = (TextView) itemView.findViewById(R.id.guide_name);

        }
    }
}
