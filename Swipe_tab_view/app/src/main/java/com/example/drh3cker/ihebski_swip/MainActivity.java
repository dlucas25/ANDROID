package com.example.drh3cker.ihebski_swip;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import com.github.clans.fab.FloatingActionButton;

//this project developped by iheb ben salem@IBSSoft
public class MainActivity extends FragmentActivity {

    ViewPager pager;
    PagerTabStrip tab_strp;

    FloatingActionButton fab1,fab2,fab3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_tab);
        ma_pager_adapter mapager=new ma_pager_adapter(getSupportFragmentManager());
        pager=(ViewPager)findViewById(R.id.pager);

        pager.setAdapter(mapager);
        tab_strp=(PagerTabStrip)findViewById(R.id.tab_strip);
        tab_strp.setTextColor(Color.WHITE);

        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab3 = (FloatingActionButton) findViewById(R.id.fab2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab3);

        final FloatingActionMenu menu1 = (FloatingActionMenu) findViewById(R.id.menu1);

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
     //   tab_strp.setTextSize(14,14);
       // tab_strp.setTabIndicatorColor(Color.WHITE);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.share) {
            return true;
        }

        return super.onOptionsItemSelected(item);

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
          Toast.makeText(MainActivity.this,text,Toast.LENGTH_LONG).show();
      }
    };

}
