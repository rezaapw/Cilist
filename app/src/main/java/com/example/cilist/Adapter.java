package com.example.cilist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.cilist.api.ResultsItem;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private ArrayList<ResultsItem> mResultItem;
    private Context mContext;

    public Adapter(ArrayList<ResultsItem> mResulItem, Context mContext) {
        this.mResultItem = mResulItem;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.daftar_list, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.judul.setText(mResultItem.get(position).getTitle());
        String url = "https://image.tmdb.org/t/p/w200" + mResultItem.get(position).getPosterPath();
        Glide.with(mContext)
                .load(url).diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .error(R.mipmap.logo).into(holder.gambar);
    }

    @Override
    public int getItemCount() {
        return mResultItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout klik;
        private ImageView gambar;
        private TextView judul;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            judul = itemView.findViewById(R.id.judul);
            gambar = itemView.findViewById(R.id.gambar);
            klik = itemView.findViewById(R.id.klik);
        }
    }
}