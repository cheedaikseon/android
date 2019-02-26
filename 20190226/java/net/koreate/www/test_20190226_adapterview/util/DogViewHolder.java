package net.koreate.www.test_20190226_adapterview.util;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.koreate.www.test_20190226_adapterview.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DogViewHolder {
    @BindView(R.id.id)
    TextView id;
    @BindView(R.id.kind)
    TextView kind;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.menu)
    ImageView menu;

    public DogViewHolder(View root){
        ButterKnife.bind(this,root);
        /*id = root.findViewById(R.id.id);
        kind = root.findViewById(R.id.kind);
        name = root.findViewById(R.id.name);
        menu = root.findViewById(R.id.menu);*/
    }
}
