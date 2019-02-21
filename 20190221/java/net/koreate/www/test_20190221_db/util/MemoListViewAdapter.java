package net.koreate.www.test_20190221_db.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.koreate.www.test_20190221_db.R;
import net.koreate.www.test_20190221_db.vo.MemoVO;

import java.util.ArrayList;

public class MemoListViewAdapter extends BaseAdapter {

    ArrayList<MemoVO> memoList;
    int layout;
    LayoutInflater li;
    Context context;
    MemoDBHelper helper;

    public MemoListViewAdapter(Context context, ArrayList<MemoVO> memoList, int layout){
        this.context=context;
        this.memoList=memoList;
        this.layout=layout;
        li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        helper = new MemoDBHelper(context);
    }

    @Override
    public int getCount() {
        return memoList.size();
    }

    @Override
    public Object getItem(int position) {
        return memoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return memoList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;

        if(convertView == null){
            convertView = this.li.inflate(layout,parent,false);
        }
        //MemoVO memo = (MemoVO)getItem(pos);
        final MemoVO memo = memoList.get(pos);

        TextView textID = convertView.findViewById(R.id.textID);
        TextView textTitle = convertView.findViewById(R.id.textTitle);
        TextView textContent = convertView.findViewById(R.id.textContent);
        ImageView closeBtn = convertView.findViewById(R.id.closeBtn);

        textID.setText(String.valueOf(memo.getId()));
        textTitle.setText(memo.getTitle());
        textContent.setText(memo.getContent());

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"memo : " +memo.toString(),Toast.LENGTH_SHORT).show();
                helper.deleteMemo(memo.getId());
                memoList.remove(pos);
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
}
