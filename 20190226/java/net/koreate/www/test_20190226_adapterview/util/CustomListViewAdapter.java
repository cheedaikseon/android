package net.koreate.www.test_20190226_adapterview.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.koreate.www.test_20190226_adapterview.vo.DogVO;

import java.util.ArrayList;

public class CustomListViewAdapter extends ArrayAdapter<DogVO> {
    Context context;
    int resId;
    ArrayList<DogVO> dogList;

    public CustomListViewAdapter(Context context, int resource, ArrayList<DogVO> dogList) {
        super(context, resource);
        this.context=context;
        this.resId = resource;
        this.dogList=dogList;
    }

    @Override
    public int getCount() {
        return dogList.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater
                    = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resId,null);
            DogViewHolder holder = new DogViewHolder(convertView);
            convertView.setTag(holder);
        }

        DogViewHolder holder = (DogViewHolder) convertView.getTag();
        TextView id = holder.id;
        TextView kind = holder.kind;
        TextView name = holder.name;
        ImageView menu = holder.menu;

        final DogVO dog = dogList.get(position);
        id.setText(String.valueOf(dog.get_id()));
        kind.setText(dog.getKind());
        name.setText(dog.getName());

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"select menu "+ dog.getName(),Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
}
