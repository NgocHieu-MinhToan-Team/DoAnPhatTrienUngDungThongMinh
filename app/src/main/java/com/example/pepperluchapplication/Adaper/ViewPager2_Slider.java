package com.example.pepperluchapplication.Adaper;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pepperluchapplication.DTO.IMAGE_NEWS;
import com.example.pepperluchapplication.DTO.NEWS;
import com.example.pepperluchapplication.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ViewPager2_Slider extends RecyclerView.Adapter<ViewPager2_Slider.ViewHolder> {
    List<NEWS> list;

    public ViewPager2_Slider(List<NEWS> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item,parent,false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NEWS news = list.get(position);
        if(news==null){
            return;
        }
        ArrayList<IMAGE_NEWS> listImage = news.getImage().values().stream().collect(
                Collectors.toCollection(ArrayList::new));
        Picasso.get().load(listImage.get(0).getPATH_IMAGE()).into(holder.imageView);
        // xu ly click vao slider here
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.iv_slider_item);
        }
    }
}
