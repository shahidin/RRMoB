package com.bizmaxsol.rrmob.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.bizmaxsol.rrmob.R;
import com.bizmaxsol.rrmob.models.TransactionRights;

import java.util.List;
import java.util.Objects;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder>{
    private final OnitemTransClickListener monitemClickListener;

    public TransactionAdapter(LiveData<List<TransactionRights>> listLiveData, OnitemTransClickListener onitemClickListener) {
        this.monitemClickListener=onitemClickListener;
        this.listLiveData = listLiveData;

    }

    LiveData<List<TransactionRights>> listLiveData;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.grid,parent,false);
        return new ViewHolder(view, monitemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.title.setText(Objects.requireNonNull(listLiveData.getValue()).get(position).getUsertran_scaption());
    }

    @Override
    public int getItemCount() {
        return (listLiveData.getValue()).size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        OnitemTransClickListener onitemClickListener;
        public ViewHolder(@NonNull View itemView, OnitemTransClickListener onitemClickListener) {
            super(itemView);

                    title = itemView.findViewById(R.id.tvmoduletitle);
                    this.onitemClickListener = onitemClickListener;
                    itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onitemClickListener.onItemClick(getAdapterPosition());
        }

    }
    public interface OnitemTransClickListener{
        void onItemClick(int position);
    }
}