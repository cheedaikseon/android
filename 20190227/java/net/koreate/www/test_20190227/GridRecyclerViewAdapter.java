package net.koreate.www.test_20190227;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class GridRecyclerViewAdapter extends RecyclerView.Adapter<GridRecyclerViewAdapter.GridHolder>{

    ArrayList<RecyclerTestVO> list;

    public GridRecyclerViewAdapter(ArrayList<RecyclerTestVO> list){
        this.list = list;
    }

    @NonNull
    @Override
    public GridHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(
                R.layout.grid_item_layout,
                viewGroup,
                false);
        return new GridHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridHolder holder, int position) {
        RecyclerTestVO rtv = list.get(position);
        holder.textView.setText(rtv.getTitle());
        holder.imageView.setBackgroundResource(rtv.getImg());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class GridHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;

        public GridHolder(@NonNull View root) {
            super(root);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);

        }
    }
}
