package com.example.drh3cker.ihebski_swip;

import android.content.Context;
import android.support.v7.internal.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrador on 11/11/2015.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{

    private List<News> item;
    Context contexto;

    public NewsAdapter(List<News> item, Context contexto) {
        this.item = item;
        this.contexto = contexto;
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
        //NewsViewHolder.image.setImageResource(item.get(position).getImage())
        Picasso.with(contexto).load(item.get(position).getImage()).into(NewsViewHolder.image);

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
