package com.project.workgroup.partyplay.views.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.project.workgroup.partyplay.R;
import com.project.workgroup.partyplay.model.entities.Event;
import com.project.workgroup.partyplay.views.RecyclerClickListener;
import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventViewHolder>  {

    private List<Event> mEventList;
    private RecyclerClickListener mRecyclerClickListener;
    private Context mContext;


    public EventsAdapter(List<Event> events , Context context, RecyclerClickListener recyclerClickListener){
        this.mEventList = events;
        this.mContext = context;
        this.mRecyclerClickListener = recyclerClickListener;
    }


    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(rootView, mRecyclerClickListener);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    class EventViewHolder extends RecyclerView.ViewHolder implements View.OnTouchListener{

        //inyectando views


        public EventViewHolder(View itemView, final RecyclerClickListener recyclerClickListener) {
            super(itemView);
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            return false;
        }
    }
}