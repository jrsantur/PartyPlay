package com.project.workgroup.partyplay.views.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.workgroup.partyplay.R;
import com.project.workgroup.partyplay.model.entities.Preference;
import com.project.workgroup.partyplay.views.RecyclerClickListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by TALLER on 13/11/2015.
 */
public class PreferenceAdapter extends RecyclerView.Adapter<PreferenceAdapter.PreferenceViewHolder> {

    public int count = 0;
    private List<Preference> preferenceList;
    private RecyclerClickListener recyclerClickListener;
    public Context context;

    public PreferenceAdapter(Context context, List<Preference> preferences){
        this.preferenceList =  preferences;
        this.context = context;
    }

    public void setRecyclerClickListener(RecyclerClickListener recyclerClickListener){
        this.recyclerClickListener = recyclerClickListener;

    }

    @Override
    public PreferenceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item_preference, parent,false);
        return new PreferenceViewHolder(rootView, recyclerClickListener);
    }

    @Override
    public void onBindViewHolder(PreferenceViewHolder holder, int position) {
        holder.imgPreference.setImageResource(preferenceList.get(position).getImagen());
        holder.titlePreference.setText(preferenceList.get(position).getTitle());
    }


    @Override
    public int getItemCount() {
        return preferenceList.size();
    }
    public class PreferenceViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener{

        private final RecyclerClickListener onClickListener;

        @Bind(R.id.img_preference)   ImageView imgPreference;
        @Bind(R.id.text_preference)  TextView titlePreference;


        public PreferenceViewHolder(View itemView, final RecyclerClickListener recyclerClickListener) {

            super(itemView);
            ButterKnife.bind(this, itemView);
            imgPreference.setOnTouchListener(this);
            this.onClickListener = recyclerClickListener;
           // bindListener(itemView, recyclerClickListener);

        }


        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP && event.getAction() != MotionEvent.ACTION_MOVE) {

                onClickListener.onElementClick(getPosition(),v, imgPreference);
            }
            return true;
        }
    }
}
