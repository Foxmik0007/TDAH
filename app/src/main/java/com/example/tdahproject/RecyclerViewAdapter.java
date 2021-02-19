package com.example.tdahproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {


    Context mContext;
    List<Task> mData;

    public RecyclerViewAdapter(Context mContext, List<Task> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.item_task, parent, false);
        MyViewHolder vHolder = new MyViewHolder (v);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.task_name.setText(mData.get(position).getName());
        holder.task_Description.setText(mData.get(position).getDescription());
        holder.imageView.setImageResource(mData.get(position).getPicture());

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView task_name;
        private TextView task_Description;
        private ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            task_name = (TextView) itemView.findViewById(R.id.task_name);
            task_Description = (TextView) itemView.findViewById(R.id.task_description);
            imageView = (ImageView) itemView.findViewById(R.id.image_task);
        }
    }
}
