package me.pwcong.simplesidebar.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.pwcong.simplesidebar.R;
import me.pwcong.simplesidebar.conf.Constants;
import me.pwcong.simplesidebar.entity.SimpleData;

/**
 * Created by pwcong on 2016/8/2.
 */
public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.ViewHolder>{


    Context context;

    List<SimpleData> simpleDatas;

    View.OnClickListener onClickListener;

    public SimpleAdapter(Context context, List<SimpleData> simpleDatas, View.OnClickListener onClickListener) {
        this.context = context;
        this.simpleDatas = simpleDatas;
        this.onClickListener = onClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        return simpleDatas.get(position).getType();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        ViewHolder viewHolder;

        if(viewType== Constants.TYPE_HEADER){
            viewHolder=new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_header,parent,false));
        }else {
            viewHolder=new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_simple,parent,false));
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.simpleData=simpleDatas.get(position);
        holder.textView.setText(simpleDatas.get(position).getString());

        if(holder.simpleData.getType()==Constants.TYPE_DATA){
            holder.view.setOnClickListener(onClickListener);
        }


    }

    @Override
    public int getItemCount() {
        return simpleDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public final View view;
        public final TextView textView;
        public SimpleData simpleData;


        public ViewHolder(View itemView) {
            super(itemView);

            this.view=itemView;
            this.textView= (TextView) itemView.findViewById(R.id.tv_simple);

        }
    }

    public int getLetterPosition(String header){

        for(int i=0;i<simpleDatas.size();i++){

            if(simpleDatas.get(i).getString().equals(header)){
                return i;
            }

        }
        return -1;

    }




}
