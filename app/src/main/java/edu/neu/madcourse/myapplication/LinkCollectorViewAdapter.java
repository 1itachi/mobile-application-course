package edu.neu.madcourse.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LinkCollectorViewAdapter extends RecyclerView.Adapter<LinkCollectorViewHolder> {

    private ArrayList<LinkUnit> linkUnitList;
    private LinkClickListener linkClickListener;

    public LinkCollectorViewAdapter(ArrayList<LinkUnit> linkUnitList) {

        this.linkUnitList = linkUnitList;
    }

    public void setOnLinkClickListener(LinkClickListener linkClickListener) {
        this.linkClickListener = linkClickListener;
    }

    @NonNull
    @Override
    public LinkCollectorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_link_unit, parent, false);
        return new LinkCollectorViewHolder(view, linkClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull LinkCollectorViewHolder linkCollectorViewHolder, int position) {
        LinkUnit currentLinkItem = linkUnitList.get(position);
        linkCollectorViewHolder.linkName.setText(currentLinkItem.getLinkName());
        linkCollectorViewHolder.linkUrl.setText(currentLinkItem.getLinkUrl());
    }

    @Override
    public int getItemCount() {
        return linkUnitList.size();
    }


    public interface LinkClickListener {
        void onLinkClick(int position);
    }
}