package com.example.shoppinglist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ItemListAdapter extends RecyclerView.Adapter<ItemListAdapter.ItemViewHolder>{
    public interface OnDeleteClickListener {
        void OnDeleteClickListener(Item myItem);
    }

    private final LayoutInflater layoutInflater;
    private Context mContext;
    private List<Item> mItems;
    private OnDeleteClickListener onDeleteClickListener;

    public ItemListAdapter(Context context, OnDeleteClickListener listener) {
        layoutInflater = LayoutInflater.from(context);
        mContext = context;
        this.onDeleteClickListener = listener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.list_item, parent, false);
        ItemViewHolder viewHolder = new ItemViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        if (mItems != null) {
            Item item = mItems.get(position);
            holder.setData(item.getItem(), position);
            holder.setListeners();
        } else {
            // Covers the case of data not being ready yet.
            holder.itemItemView.setText(R.string.no_item);
        }
    }

    @Override
    public int getItemCount() {
        if (mItems != null)
            return mItems.size();
        else return 0;
    }

    public void setItems(List<Item> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView itemItemView;
        private int mPosition;
        private ImageView imgDelete, imgEdit;

        public ItemViewHolder(View itemView) {
            super(itemView);
            itemItemView = itemView.findViewById(R.id.txvItem);
            imgDelete 	 = itemView.findViewById(R.id.ivRowDelete);
            imgEdit 	 = itemView.findViewById(R.id.ivRowEdit);
        }

        public void setData(String item, int position) {
            itemItemView.setText(item);
            mPosition = position;
        }

        public void setListeners() {
            imgEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, EditItemActivity.class);
                    intent.putExtra("item_id", mItems.get(mPosition).getId());
                    ((Activity)mContext).startActivityForResult(intent, MainActivity.UPDATE_ITEM_ACTIVITY_REQUEST_CODE);
                }
            });

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onDeleteClickListener != null) {
                        onDeleteClickListener.OnDeleteClickListener(mItems.get(mPosition));
                    }
                }
            });
        }
    }
}
