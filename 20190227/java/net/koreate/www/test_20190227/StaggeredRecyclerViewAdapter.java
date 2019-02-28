package net.koreate.www.test_20190227;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class StaggeredRecyclerViewAdapter extends RecyclerView.Adapter<StaggeredRecyclerViewAdapter.StaggeredHolder>{

    ArrayList<RecyclerTestVO> list;

    public StaggeredRecyclerViewAdapter(ArrayList<RecyclerTestVO> list){
        this.list=list;
    }

    @Override
    public StaggeredHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(
                viewGroup.getContext()
        ).inflate(
                R.layout.staggered_item_layout,
                viewGroup,
                false
        );
        return new StaggeredHolder(v);
    }

    @Override
    public void onBindViewHolder(StaggeredHolder h, int pos) {
        h.textView.setText(list.get(pos).getTitle());
        h.imageView.setBackgroundResource(list.get(pos).getImg());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class StaggeredHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;

        public StaggeredHolder(@NonNull View root) {
            super(root);
            textView = root.findViewById(R.id.textView);
            imageView = root.findViewById(R.id.imageView);
        }
    }
}
