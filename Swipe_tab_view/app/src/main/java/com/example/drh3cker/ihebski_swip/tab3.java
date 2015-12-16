package com.example.drh3cker.ihebski_swip;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

/**
 * Created by Dr.h3cker on 16/03/2015.
 */
public class tab3 extends Fragment {

    private RecyclerView reciclador;
    private RecyclerView.LayoutManager imanager;
    private RecyclerView.Adapter adaptador;
    FloatingActionButton fab1,fab2,fab3;
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.tab3,container,false);

        ArrayList<News> newses = new ArrayList<News>();
        newses.add(new News("http://res.cloudinary.com/dxohs8oh5/image/upload/c_scale,w_150/v1448130576/UPMPLOGO_umotyg.jpg", "UPMP", "BIS Universities"));
        reciclador=(RecyclerView)view.findViewById(R.id.reciclador);
        imanager=new LinearLayoutManager(getActivity().getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        reciclador.setLayoutManager(imanager);
        adaptador = new NewsAdapter(newses,getActivity().getApplicationContext());
        reciclador.setAdapter(adaptador);

        context = getActivity().getBaseContext();

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


    private View.OnClickListener clickListener = new View.OnClickListener(){
        public void onClick(View v){
            String text = "";

            switch (v.getId()){
                case R.id.fab1:
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
                    text = fab3.getLabelText();
                    break;

            }
           // Toast.makeText(getActivity().getApplicationContext(), text, Toast.LENGTH_LONG).show();
        }
    };


    }
