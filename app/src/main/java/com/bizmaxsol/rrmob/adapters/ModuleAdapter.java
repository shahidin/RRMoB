package com.bizmaxsol.rrmob.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.bizmaxsol.rrmob.R;
import com.bizmaxsol.rrmob.models.ModuleRights;

import java.util.List;

public class ModuleAdapter extends RecyclerView.Adapter<ModuleAdapter.ViewHolder> {

    private  OnitemClickListener monitemClickListener;

    public ModuleAdapter(LiveData<List<ModuleRights>> moduleRightsLiveData,OnitemClickListener onitemClickListener) {
        this.monitemClickListener=onitemClickListener;
        this.moduleRightsLiveData = moduleRightsLiveData;
    }

    LiveData<List<ModuleRights>> moduleRightsLiveData;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater= LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.grid,parent,false);
        ViewHolder viewHolder=new ViewHolder(view,monitemClickListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(moduleRightsLiveData.getValue().get(position).getUsermodule_scaption());
//        holder.ivmod.setImageResource(700183);
    }

    @Override
    public int getItemCount() {
        return moduleRightsLiveData.getValue().size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        ImageView ivmod;
        OnitemClickListener onitemClickListener;
        public ViewHolder(@NonNull View itemView,OnitemClickListener onitemClickListener) {
            super(itemView);
            title=itemView.findViewById(R.id.tvmoduletitle);
            this.onitemClickListener=onitemClickListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onitemClickListener.onItemClick(getAdapterPosition());
        }
    }
    public interface OnitemClickListener{
        void onItemClick(int position);
    }
}
