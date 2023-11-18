package com.example.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private List<ItemModel> itemModelList;
    private Context context;

    protected ItemAdapter(Context context, List<ItemModel> itemModelList){
        this.context = context;
        this.itemModelList = itemModelList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_model_design, parent, false);

        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ItemModel itemModel = itemModelList.get(position);

        holder.txt_nome_task.setText(itemModel.getNome());
        holder.txt_descricao_task.setText(itemModel.getDescricao());
    }

    @Override
    public int getItemCount() {
        return itemModelList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView txt_nome_task;
        TextView txt_descricao_task;

        public ItemViewHolder(@NonNull View itemView){
            super(itemView);

            txt_nome_task = itemView.findViewById(R.id.txt_nome_task);
            txt_descricao_task = itemView.findViewById(R.id.txt_descricao_task);
        }

    }
}
