package com.example.drh3cker.ihebski_swip;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
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
import org.json.JSONTokener;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
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
    private SwipeRefreshLayout swipe;
    @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
        {
            View view=inflater.inflate(R.layout.tab1,container,false);
            //swipe = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
            //swipe.setEnabled(false);

            noticias = new ArrayList<>();
            try {
                readJson();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // ArrayList<News> newses = new ArrayList<News>();
            //newses.add(new News("http://res.cloudinary.com/dxohs8oh5/image/upload/c_scale,w_150/v1448130576/UPMPLOGO_umotyg.jpg","UPMP","BIS Universities"));
            //newses.add(new News("http://res.cloudinary.com/dxohs8oh5/image/upload/c_scale,w_350/v1448434625/BIS_jsqurd.jpg","UPMP","a new image"));
            //newses.add(new News("http://res.cloudinary.com/dxohs8oh5/image/upload/c_scale,w_400/v1448647658/Splash_ptwnnv.jpg","UPMP","Instalations"));

            reciclador=(RecyclerView)view.findViewById(R.id.reciclador);
            imanager=new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false);
            reciclador.setLayoutManager(imanager);
            adaptador = new NewsAdapter(noticias,getActivity().getApplicationContext());
            reciclador.setAdapter(adaptador);
           /* swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    new Handler().postDelayed(new Runnable(){

                        @Override
                        public void run() {
                            refresh();
                            swipe.setRefreshing(false);
                        }
                    },2500);
                }
            }); */



        fab1 = (FloatingActionButton) view.findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) view.findViewById(R.id.fab2);
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
        fab2.setOnClickListener(clickListener);
        fab3.setOnClickListener(clickListener);


        return  view;
    }

    private void refresh (){
        onStart();
        try {
            readJson();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        imanager=new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        reciclador.setLayoutManager(imanager);
        adaptador = new NewsAdapter(noticias,getActivity().getApplicationContext());
        reciclador.setAdapter(adaptador);
    }



    public void onStart()
    {
        super.onStart();
        String eventos = getResources().getString(R.string.Noticias_URL);
        // Create request queue
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity().getApplicationContext());
        //  Create json array request
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET,eventos,new Response.Listener<JSONArray>()
        {

            public void onResponse(JSONArray jsonArray)
            {
                BufferedOutputStream bos;
                File cache = new File(Environment.getExternalStorageDirectory()+ File.separator + "Noticias.json");
                //Toast.makeText(getActivity().getApplicationContext(),"archivo creado",Toast.LENGTH_SHORT).show();
                try{
                    cache.createNewFile();
                }catch (IOException e){
                    e.printStackTrace();
                }
                try{
                    bos = new BufferedOutputStream(new FileOutputStream(cache));
                    bos.write(jsonArray.toString().getBytes());
                    bos.flush();
                    bos.close();
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }catch (IOException a){
                    a.printStackTrace();
                }
                finally {
                    System.gc();
                }
                for(int i=0;i<jsonArray.length();i++){
                    try{
                        JSONObject jsonObject=jsonArray.getJSONObject(i);
                    }catch (JSONException e){
                        e.printStackTrace();
                    }
                }

                System.out.println(jsonArray);




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
    public void readJson () throws IOException, JSONException {
        File sdcard = Environment.getExternalStorageDirectory();
        File file = new File(sdcard,"Noticias.json");
        StringBuilder jsonBuilde = new StringBuilder();

        BufferedReader jsonReader = new BufferedReader(new FileReader(file));
        for (String line = null; (line = jsonReader.readLine()) != null;){
            jsonBuilde.append(line).append("\n");
        }
        JSONTokener tokener = new JSONTokener(jsonBuilde.toString());
        JSONArray jsonArray = new JSONArray(tokener);

        for(int i=0;i<jsonArray.length();i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            int pk = jsonObject.getInt("pk");
            String categoria= jsonObject.getString("categoria");
            String titulo = jsonObject.getString("titulo");
            String pub_date = jsonObject.getString("pub_date");
            String descripcion = jsonObject.getString("descripcion");
            String imagen = jsonObject.getString("imagen");
            News temp = new News(pk,categoria,titulo,pub_date,descripcion,imagen);
            noticias.add(temp);
            temp=null;
        }
    }


    private View.OnClickListener clickListener = new View.OnClickListener(){
        public void onClick(final View v){
            String text = "";

            switch (v.getId()){
                case R.id.fab1:
                    final CharSequence[] items={
                            "Facebook","Twitter","Youtube","Pagina web"
                    };

                    AlertDialog.Builder builde = new AlertDialog.Builder(v.getContext());
                    builde.setTitle("Redes Sociales");
                    builde.setItems(items, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = null;
                            switch (which)
                            {
                                case 0:
                                    try {
                                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/131926376887259"));
                                        startActivity(intent);
                                    } catch (ActivityNotFoundException e) {
                                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Metropolitana-de-Puebla-131926376887259/"));
                                        startActivity(intent);
                                    }break;
                                case 1:try {
                                    // get the Twitter app if possible
                                    v.getContext().getPackageManager().getPackageInfo("com.twitter.android", 0);
                                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?user_id=3158008044"));
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                } catch (Exception e) {
                                    // no Twitter app, revert to browser
                                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/umetropoli"));
                                }
                                    v.getContext().startActivity(intent);break;
                                case 2:
                                    try{
                                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:Xy3SmE_-Wsk" ));
                                    startActivity(intent);
                                }catch (ActivityNotFoundException ex){
                                        intent=new Intent(Intent.ACTION_VIEW,
                                            Uri.parse("http://www.youtube.com/watch?v=Xy3SmE_-Wsk"));
                                    startActivity(intent);
                                }break;
                                case 3:
                                    try{
                                        intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.metropoli.edu.mx/") );
                                        startActivity(intent);
                                    }catch (ActivityNotFoundException ex){
                                        intent=new Intent(Intent.ACTION_VIEW,
                                                Uri.parse("http://www.metropoli.edu.mx/"));
                                        startActivity(intent);
                                    }break;

                            }

                            }


                    });
                    AlertDialog alert1 = builde.create();
                    alert1.show();
                    text = fab1.getLabelText();
                    break;
                case R.id.fab2:
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setMessage("¿Desea continuar con llamada?")
                            .setTitle("Advertencia")
                            .setCancelable(false)
                            .setNegativeButton("Cancelar",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    })
                            .setPositiveButton("Continuar",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            String url = "tel:2225825222";
                                            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(url));
                                            startActivity(intent);                                            //LlamarOn(); // metodo que se debe implementar
                                            //Toast.makeText(getActivity().getApplicationContext(),"Llamada",Toast.LENGTH_LONG).show();
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                    text = fab2.getLabelText();
                    break;
                case R.id.fab3:
                    Intent intent = new Intent(getActivity().getApplicationContext(),AboutActivity.class);
                    startActivity(intent);
                    text = fab3.getLabelText();
                    break;

            }
           // Toast.makeText(getActivity().getApplicationContext(), text, Toast.LENGTH_LONG).show();
        }
    };
}
