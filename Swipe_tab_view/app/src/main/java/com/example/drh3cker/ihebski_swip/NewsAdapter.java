package com.example.drh3cker.ihebski_swip;

import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrador on 11/11/2015.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{

    private ArrayList<News> item;

    public NewsAdapter(ArrayList<News> item) {
        this.item = item;
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_news,viewGroup,false);
        NewsViewHolder news = new NewsViewHolder(v);
        return news;
    }

    @Override
    public void onBindViewHolder(NewsViewHolder NewsViewHolder, int position) {
        NewsViewHolder.title.setText(item.get(position).getTitle());
        NewsViewHolder.description.setText(item.get(position).getDescription());
        NewsViewHolder.image.setImageResource(item.get(position).getImage());

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        TextView title,description;
        ImageView image;
        public NewsViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.title);
            description=(TextView)itemView.findViewById(R.id.description);
            image=(ImageView)itemView.findViewById(R.id.image);
        }
    }


}
