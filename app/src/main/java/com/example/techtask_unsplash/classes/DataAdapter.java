package com.example.techtask_unsplash.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.techtask_unsplash.R;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    final Context context;
    ArrayList<com.example.techtask_unsplash.classes.GetData> modelList;
    private OnItemClickListener onItemClickListener;

    public DataAdapter(Context context, ArrayList<com.example.techtask_unsplash.classes.GetData> modelList) {
        this.context = context;
        this.modelList = modelList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        com.example.techtask_unsplash.classes.GetData data = modelList.get(position);
        Glide.with(context).load(data.getImageUrl()).into(holder.imageView);
        holder.imgNameText.setText(data.getImageName());
        holder.imgCreatorText.setText(data.getImageCreator());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView imgNameText, imgCreatorText;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById((R.id.imgCard));
            imgCreatorText = itemView.findViewById(R.id.imgCreatorText);
            imgNameText = itemView.findViewById(R.id.imgNameText);
            itemView.setOnClickListener(view -> {
                if (onItemClickListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onItemClickListener.onItemClick(position);
                    }
                }
            });
        }
    }
}
