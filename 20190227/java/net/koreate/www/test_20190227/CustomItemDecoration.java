package net.koreate.www.test_20190227;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class CustomItemDecoration extends RecyclerView.ItemDecoration {

    Context context;

    public CustomItemDecoration(Context context){
        this.context=context;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int index = parent.getChildAdapterPosition(view)+1;

        if(index % 3 == 0){
            // left, top , right, bottom
            outRect.set(20,20,20,60);
        }else{
            outRect.set(20,20,20,20);
        }

        view.setBackgroundColor(context.getResources().getColor(R.color.over_ray));
        ViewCompat.setElevation(view , 20);

        //Log.i("getItemOffsets index",index+"");
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);

        int width = parent.getWidth();
        int height = parent.getHeight();

        Drawable dr = ResourcesCompat.getDrawable(context.getResources(),R.drawable.puppy,null);

        int drWidth = dr.getIntrinsicWidth();
        int drHeight = dr.getIntrinsicHeight();

       /* System.out.println("전체 넓이와 높이  : " + width+"/"+height);
        System.out.println("이미지 넓이와 높이  : " + drWidth+"/"+drHeight);*/

        int left = (width / 2) - (drWidth/2);
        int top = (height / 2) - (drHeight/2);

        Bitmap b = BitmapFactory.decodeResource(context.getResources(),R.drawable.puppy);
        c.drawBitmap(b,left,top,null);
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        int width = parent.getWidth();
        int height = parent.getHeight();

        Drawable dr = ResourcesCompat.getDrawable(context.getResources(),R.drawable.android,null);

        int drWidth = dr.getIntrinsicWidth();
        int drHeight = dr.getIntrinsicHeight();

       /* System.out.println("전체 넓이와 높이  : " + width+"/"+height);
        System.out.println("이미지 넓이와 높이  : " + drWidth+"/"+drHeight);*/

        int left = (width / 2) - (drWidth/2);
        int top = (height) - (drHeight);

        Bitmap b = BitmapFactory.decodeResource(context.getResources(),R.drawable.android);
        c.drawBitmap(b,left,top,null);
    }
}
