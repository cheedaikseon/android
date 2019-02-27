package net.koreate.www.test_20190227;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class BasicVerticalRecyclerViewAdapter extends RecyclerView.Adapter<BasicVerticalRecyclerViewAdapter.TextHolder>{

    List<String> list;

    static MyClickListener myClickListener;

    public BasicVerticalRecyclerViewAdapter(List<String> list){
        this.list = list;
    }

    public interface MyClickListener{
        void onItemClick(int position, View v);
    }

    public void setOnItemClickListener(MyClickListener myClickListener){
        this.myClickListener = myClickListener;
    }


    @NonNull
    @Override
    public TextHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View root = LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_list_item_1,viewGroup,false);
        return new TextHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull TextHolder textHolder, int position) {
        String s = list.get(position);
        textHolder.textView.setText(s);
    }

    public void addItem(String data, int position){
        list.add(position,data);
        //notifyDataSetChanged();
        notifyItemInserted(position);
        notifyItemRangeChanged(position,getItemCount());
    }

    public void deleteItem(int position){
        list.remove(position);
        //notifyDataSetChanged();
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,getItemCount());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class TextHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textView;

        public TextHolder(View root){
            super(root);
            textView = (TextView)itemView.findViewById(android.R.id.text1);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            myClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

}
