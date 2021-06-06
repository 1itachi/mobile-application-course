package edu.neu.madcourse.myapplication;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LinkCollectorViewHolder extends RecyclerView.ViewHolder {
    public TextView linkName;
    public TextView linkUrl;

    public LinkCollectorViewHolder(@NonNull View itemView, final LinkCollectorViewAdapter.LinkClickListener linkClickListener) {
        super(itemView);
        linkName = itemView.findViewById(R.id.link_name_value_pair);
        linkUrl = itemView.findViewById(R.id.link_url_value_pair);

        itemView.setOnClickListener(v -> {
            int layoutPosition = getLayoutPosition();
            if (layoutPosition != RecyclerView.NO_POSITION) {
                linkClickListener.onLinkClick(layoutPosition);
            }
        });
    }
}
