package com.project.workgroup.partyplay.views.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.project.workgroup.partyplay.R;
import com.project.workgroup.partyplay.mvp.presenter.EventDetailsPresenter;
import com.project.workgroup.partyplay.mvp.views.EventDetailsView;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EventDetailsActivity extends AppCompatActivity implements EventDetailsView {

    @Bind(R.id.map_inagen) ImageView mapImageView;

    @Inject EventDetailsPresenter eventDetailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        setContentView(R.layout.activity_event_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mapImageView.setImageBitmap(getGoogleMapThumbnail(40.7127837, -74.00594130000002));

    }

  public Bitmap getGoogleMapThumbnail(double lati, double longi)  {
        String URL = "http://maps.google.com/maps/api/staticmap?center=" +lati + "," + longi + "&zoom=15&size=200x200&sensor=false";
        Bitmap bitmap  = null;
        try {
             bitmap = Glide.with(this).load(URL).asBitmap().into(600,400).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}