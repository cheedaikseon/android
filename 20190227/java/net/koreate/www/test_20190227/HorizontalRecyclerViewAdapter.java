package net.koreate.www.test_20190227;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HorizontalRecyclerViewAdapter extends RecyclerView.Adapter<HorizontalRecyclerViewAdapter.HorizontalHolder> {

    ArrayList<RecyclerTestVO> list;

    public HorizontalRecyclerViewAdapter(ArrayList<RecyclerTestVO> list){
        this.list=list;
    }

    @NonNull
    @Override
    public HorizontalHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view
            = LayoutInflater.from(
                    viewGroup.getContext()
        ).inflate(
                R.layout.horizontal_item_layout,
                viewGroup,
                false);
        return new HorizontalHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalHolder holder, int position) {
        final int pos = position;

        holder.textView.setText(list.get(pos).getTitle());
        holder.imageView.setBackgroundResource(list.get(pos).getImg());

        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.remove(pos);
                notifyItemRemoved(pos);
                notifyItemRangeChanged(pos,getItemCount());
            }
        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.add(pos,list.get(pos));
                notifyItemInserted(pos);
                notifyItemRangeChanged(pos,getItemCount());
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class HorizontalHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.textView)
        TextView textView;
        @BindView(R.id.imageView)
        ImageView imageView;

        public HorizontalHolder(@NonNull View root) {
            super(root);
            ButterKnife.bind(this,root);
            /*textView = itemView.findViewById(R.id.textView);
            imageView = itemView.findViewById(R.id.imageView);*/
        }
    }
}
