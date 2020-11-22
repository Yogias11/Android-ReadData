package com.example.user.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.user.MainActivity;
import com.example.user.R;
import com.example.user.model.DataModel;

import java.util.List;

public class AdapterUser extends RecyclerView.Adapter<AdapterUser.HolderData> {

    private Context ctx;
    private List<DataModel> mList;

    public AdapterUser(Context ctx, List<DataModel> mList) {
        this.ctx = ctx;
        this.mList = mList;
    }

    @NonNull
    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutlist, parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderData holder, int position) {
        DataModel dm = mList.get(position);
        holder.userid.setText(dm.getUserid());
        holder.nama.setText(dm.getNama());
        holder.status.setText(dm.getStatus());
        holder.dm = dm;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

     class HolderData extends RecyclerView.ViewHolder {
        TextView userid, nama, status;
        DataModel dm;
        public HolderData(@NonNull View itemView) {
            super(itemView);

            userid = itemView.findViewById(R.id.tvUserid);
            nama = itemView.findViewById(R.id.tvNama);
            status = itemView.findViewById(R.id.tvStatus);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(ctx, MainActivity.class);
                    i.putExtra("userid", dm.getUserid());
                    i.putExtra("nama", dm.getNama());
                    i.putExtra("status", dm.getStatus());

                    ctx.startActivity(i);
                }
            });
        }
    }
}
