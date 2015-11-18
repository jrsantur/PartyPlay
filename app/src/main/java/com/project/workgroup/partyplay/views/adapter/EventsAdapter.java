package com.project.workgroup.partyplay.views.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.workgroup.partyplay.R;
import com.project.workgroup.partyplay.model.entities.Event;
import com.project.workgroup.partyplay.views.RecyclerClickListener;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventViewHolder>  {

    private List<Event> mEventList;
    private RecyclerClickListener mRecyclerClickListener;
    private Context mContext;


    public EventsAdapter(List<Event> events , Context context){
        this.mEventList = events;
        this.mContext = context;

    }

    public void setmRecyclerListListener(RecyclerClickListener mRecyclerClickListener){
        this.mRecyclerClickListener = mRecyclerClickListener;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(rootView, mRecyclerClickListener);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
            holder.bindEvent(mEventList.get(position));
    }

    @Override
    public int getItemCount() {
        return mEventList.size();
    }


    class EventViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener{


        private final RecyclerClickListener onClickListener;
        //inyectando views
        @Bind(R.id.fecha_event) TextView fechaEvent;
        @Bind(R.id.title_event) TextView titleEvent;
        @Bind(R.id.lugar_event) TextView lugarEvent;
        @Bind(R.id.image_event) ImageView imageEvent;

        public EventViewHolder(View itemView, final RecyclerClickListener recyclerClickListener) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            imageEvent.setOnTouchListener(this);
            this.onClickListener = recyclerClickListener;
        }

        public void bindEvent(Event event){
            fechaEvent.setText(event.getFecha());
            titleEvent.setText(event.getTitle());
            lugarEvent.setText(event.getNombre_lugar());
            Glide.with(mContext).load(event.getLogo()).crossFade().into(imageEvent);
        }


        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_UP && event.getAction() != MotionEvent.ACTION_MOVE) {

                onClickListener.onElementClick(getPosition(),v, imageEvent);
            }
            return true;
        }
    }
}