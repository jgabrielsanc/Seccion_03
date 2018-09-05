package com.android.jsanchez.seccion_03;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<String> names;
    private int layout;
    private onItemClickListener itemClickListener;

    public MyAdapter(List<String> names, int layout, onItemClickListener itemClickListener) {
        this.names = names;
        this.layout = layout;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(names.get(i), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.textViewName);
        }

        public void bind(final String name, final onItemClickListener listener){

            this.textViewName.setText(name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(name, getAdapterPosition());
                }
            });
        }

    }

    public interface onItemClickListener{
        void onItemClick(String name, int position);
    }
}
