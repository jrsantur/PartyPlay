package com.project.workgroup.partyplay.views.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.project.workgroup.partyplay.R;
import com.project.workgroup.partyplay.model.entities.Preference;
import com.project.workgroup.partyplay.views.RecyclerClickListener;
import com.project.workgroup.partyplay.views.adapter.PreferenceAdapter;
import com.project.workgroup.partyplay.views.custom_views.RecyclerInsetsDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PreferenceActivity extends AppCompatActivity implements RecyclerClickListener{


    int count = 0 ;
    List<Preference> preferenceList;
    PreferenceAdapter preferenceAdapter;
    @Bind(R.id.progress)    ProgressBar progressBar;
    @Bind(R.id.toolbar)     Toolbar toolbar;
    @Bind(R.id.activity_preference_recycler) RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        preferenceList = new ArrayList<>();

        Preference preference = new Preference();
        Preference preference1 = new Preference();
        Preference preference2 = new Preference();
        Preference preference3 = new Preference();
        Preference preference4 = new Preference();
        Preference preference5 = new Preference();
        Preference preference6 = new Preference();
        Preference preference7 = new Preference();
        Preference preference8 = new Preference();
        Preference preference9 = new Preference();



        preference.setImagen(R.drawable.yt_icono); preference.setTitle("Youtube");
        preference1.setImagen(R.drawable.yt_icono); preference1.setTitle("Youtube");
        preference2.setImagen(R.drawable.yt_icono); preference2.setTitle("Youtube");
        preference3.setImagen(R.drawable.yt_icono); preference3.setTitle("Youtube");
        preference4.setImagen(R.drawable.yt_icono); preference4.setTitle("Youtube");
        preference5.setImagen(R.drawable.yt_icono); preference5.setTitle("Youtube");
        preference6.setImagen(R.drawable.yt_icono); preference6.setTitle("Youtube");
        preference7.setImagen(R.drawable.yt_icono); preference7.setTitle("Youtube");
        preference8.setImagen(R.drawable.yt_icono); preference8.setTitle("Youtube");
        preference9.setImagen(R.drawable.yt_icono); preference9.setTitle("Youtube");


        preferenceList.add(preference);
        preferenceList.add(preference1);
        preferenceList.add(preference2);
        preferenceList.add(preference3);
        preferenceList.add(preference4);
        preferenceList.add(preference5);
        preferenceList.add(preference6);
        preferenceList.add(preference7);
        preferenceList.add(preference8);
        preferenceList.add(preference9);


        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.addItemDecoration(new RecyclerInsetsDecoration(this));

        //recyclerView.addOnScrollListener(mOnScrollListener);
        preferenceAdapter = new PreferenceAdapter(this, preferenceList);
        preferenceAdapter.setRecyclerClickListener(this);
        recyclerView.setAdapter(preferenceAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    public void onElementClick(int position, View sharedView, ImageView characterImageView) {
        count++;
        Toast.makeText(this, "Count: "+count, Toast.LENGTH_LONG).show();
        Log.e("MainActivity", "onclick");

    }

}
