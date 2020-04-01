package com.example.test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.List;
import java.util.Locale;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public final static int FOOTER = 1;
    public final static int NORMAL_DATA = 2;
    private List<DataInfo> datas;

    Adapter(List<DataInfo> datas) {
        this.datas = datas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == NORMAL_DATA) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
            return new Holder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_footer, parent, false);
            StaggeredGridLayoutManager.LayoutParams layoutParams = new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setFullSpan(true);
            view.setLayoutParams(layoutParams);
            return new FooterHolder(view);
        }
    }

    public DataInfo getItem(int position) {
        return datas.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Holder) {
            DataInfo info = getItem(position);
            ViewGroup.LayoutParams params = ((Holder) holder).referencePicture.getLayoutParams();
            params.height = info.height;
            params.width = info.width;
            ((Holder) holder).referencePicture.setLayoutParams(params);
            ((Holder) holder).goodName.setText(String.format(Locale.CANADA, "%s %d %d", info.title, info.width, info.height));
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return FOOTER;
        } else {
            return NORMAL_DATA;
        }
    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() + 1 : 0;
    }

    static class Holder extends RecyclerView.ViewHolder {
        ImageView referencePicture;
        TextView goodName;

        Holder(View itemView) {
            super(itemView);
            referencePicture = itemView.findViewById(R.id.reference_picture);
            goodName = itemView.findViewById(R.id.good_name);
        }
    }

    static class FooterHolder extends RecyclerView.ViewHolder {
        FooterHolder(View itemView) {
            super(itemView);
        }
    }

}
