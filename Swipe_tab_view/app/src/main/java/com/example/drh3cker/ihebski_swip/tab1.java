package com.example.drh3cker.ihebski_swip;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class tab1 extends Fragment
{
    private RecyclerView reciclador;
    private RecyclerView.LayoutManager imanager;
    private RecyclerView.Adapter adaptador;
    FloatingActionButton fab1,fab2,fab3;
    JSONArray jsonArray;
    public List<News> noticias;
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
        {
            View view=inflater.inflate(R.layout.tab1,container,false);

            noticias = new ArrayList<>();
           // ArrayList<News> newses = new ArrayList<News>();
            //newses.add(new News("http://res.cloudinary.com/dxohs8oh5/image/upload/c_scale,w_150/v1448130576/UPMPLOGO_umotyg.jpg","UPMP","BIS Universities"));
            //newses.add(new News("http://res.cloudinary.com/dxohs8oh5/image/upload/c_scale,w_350/v1448434625/BIS_jsqurd.jpg","UPMP","a new image"));
            //newses.add(new News("http://res.cloudinary.com/dxohs8oh5/image/upload/c_scale,w_400/v1448647658/Splash_ptwnnv.jpg","UPMP","Instalations"));

            reciclador=(RecyclerView)view.findViewById(R.id.reciclador);
            imanager=new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false);
            reciclador.setLayoutManager(imanager);

        fab1 = (FloatingActionButton) view.findViewById(R.id.fab1);
        fab3 = (FloatingActionButton) view.findViewById(R.id.fab2);
        fab3 = (FloatingActionButton) view.findViewById(R.id.fab3);

        final FloatingActionMenu menu1 = (FloatingActionMenu) view.findViewById(R.id.menu1);

        menu1.setOnMenuButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(menu1.isOpened()){
                    boolean band=true;
                }

                menu1.toggle(true);
            }
        });

        fab1.setOnClickListener(clickListener);
        fab3.setOnClickListener(clickListener);
        fab3.setOnClickListener(clickListener);


        return  view;
    }




    public void onStart()
    {
        super.onStart();
        String eventos = "http://django-upmp.rhcloud.com/noticias/api/noticias/";
        // Create request queue
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity().getApplicationContext());
        //  Create json array request
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET,eventos,new Response.Listener<JSONArray>()
        {

            public void onResponse(JSONArray jsonArray)
            {

                System.out.println("JsonArray: "+jsonArray);
                //this.jsonArray = jsonArray;

                for (int i=0;i<jsonArray.length();i++){
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String nombre = jsonObject.getString("titulo");
                        String fecha = jsonObject.getString("pub_date");
                        String pk = jsonObject.getString("pk");
                        String categorias = jsonObject.getString("categoria");
                        String descripcion = jsonObject.getString("descripcion");
                        String imagen = jsonObject.getString("imagen");
                        News temp = new News(imagen,nombre,descripcion);
                        noticias.add(temp);
                        temp = null;
                    }catch (JSONException e){

                    }

                }
                adaptador = new NewsAdapter(noticias,getActivity().getApplicationContext());
                reciclador.setAdapter(adaptador);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("Error", "Unable to parse json array " + volleyError.toString());
            }
        });
        // add json array request to the request queue
        requestQueue.add(jsonArrayRequest);

    }

    private View.OnClickListener clickListener = new View.OnClickListener(){
        public void onClick(View v){
            String text = "";

            switch (v.getId()){
                case R.id.fab1:
                    text = fab1.getLabelText();
                    break;
                case R.id.fab2:
                    text = fab2.getLabelText();
                    break;
                case R.id.fab3:
                    text = fab3.getLabelText();
                    break;

            }
            Toast.makeText(getActivity().getApplicationContext(), text, Toast.LENGTH_LONG).show();
        }
    };
}
