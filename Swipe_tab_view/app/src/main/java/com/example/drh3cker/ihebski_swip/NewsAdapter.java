package com.example.drh3cker.ihebski_swip;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


import java.util.List;

/**
 * Created by Administrador on 11/11/2015.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{

    private List<News> item;
    Context contexto;
    SharedPreference sharedPreference;

    public NewsAdapter(List<News> item, Context contexto) {
        this.item = item;
        this.contexto = contexto;
        sharedPreference = new SharedPreference();
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_news,viewGroup,false);
        NewsViewHolder news = new NewsViewHolder(v);


        return news;
    }

    @Override
    public void onBindViewHolder(final NewsViewHolder NewsViewHolder, final int position) {

        NewsViewHolder.title.setText(item.get(position).getTitle());
        NewsViewHolder.description.setText(item.get(position).getDescription());
        //NewsViewHolder.image.setImageResource(item.get(position).getImage())
        Picasso.with(contexto).load(item.get(position).getImage()).into(NewsViewHolder.image);

        if(item.get(position).isStatus())
        {
            NewsViewHolder.fav.setBackgroundResource(R.mipmap.star1);
            NewsViewHolder.fav.setTag("red");
        }else
        {
            NewsViewHolder.fav.setBackgroundResource(R.mipmap.star);
            NewsViewHolder.fav.setTag("grey");
        }

        NewsViewHolder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pk = item.get(position).getId();
                String  categoria = item.get(position).getCategory();
                String titulo = item.get(position).getTitle();
                String fecha = item.get(position).getDate();
                String descripcion = item.get(position).getDescription();
                String imagen = item.get(position).getImage();
                News temp = new News();
                temp.setId(pk);
                temp.setCategory(categoria);
                temp.setTitle(titulo);
                temp.setDate(fecha);
                temp.setDescription(descripcion);
                temp.setImage(imagen);

                String tag = NewsViewHolder.fav.getTag().toString();

                if(tag.equalsIgnoreCase("grey"))
                {
                    item.get(position).setStatus(true);
                    sharedPreference.addFavorite(contexto, item.get(position));
                    Toast.makeText(contexto,"add to favorites",Toast.LENGTH_SHORT).show();
                    NewsViewHolder.fav.setTag("red");
                    NewsViewHolder.fav.setBackgroundResource(R.mipmap.star1);

                }else{

                    item.get(position).setStatus(false);
                    sharedPreference.removeFavorite(contexto, item.get(position));
                    NewsViewHolder.fav.setTag("grey");
                    NewsViewHolder.fav.setBackgroundResource(R.mipmap.star);
                    Toast.makeText(contexto,"remove to favorites",Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{
        TextView title,description;
        ImageView image;
        ImageView fav;
        public NewsViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.title);
            description=(TextView)itemView.findViewById(R.id.description);
            image=(ImageView)itemView.findViewById(R.id.image);
            fav=(ImageView)itemView.findViewById(R.id.fav);

        }
    }

    public boolean checkFavoriteItem (News news){

        boolean check = false;
        List<News> favorites = sharedPreference.getFavorites(contexto);
        if (favorites != null) {
            for(News i : favorites) {
                if(i.isStatus())
                {
                    check = true;
                    break;
                }
            }
        }
        return check;
        }
    }


