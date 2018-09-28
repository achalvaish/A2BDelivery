package com.del.a2b.recyclesample.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.Toast;

import com.del.a2b.recyclesample.model.RecyclerViewRow;

import java.util.List;

public class RecyclerViewAdapter<T> extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private List<T> mDataset;
    private int layoutId;
    private Context context;
    public RecyclerViewAdapter(Context context,List<T> mDataset, int layoutId) {
        this.mDataset = mDataset;
        this.layoutId = layoutId;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerViewRow<T> row = (RecyclerViewRow<T>) LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        ViewHolder vh = new ViewHolder(row);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mRow.showData(mDataset.get(position));
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder deleteDialog = new AlertDialog.Builder(context);
                deleteDialog.setMessage("Do you want to delete this tracking ?");
                deleteDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteItem(position);
                    }
                });
                deleteDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context,"Delete action cancelled",Toast.LENGTH_SHORT).show();
                    }
                });
                deleteDialog.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public RecyclerViewRow mRow;

        public ViewHolder(RecyclerViewRow itemView) {
            super((View) itemView);
            mRow = itemView;
        }
    }

    public void updateDataset(List<T> data)
    {
        mDataset = data;
    }

    public void deleteItem(int index)
    {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }
}
