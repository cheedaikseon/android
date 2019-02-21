package net.koreate.www.test20190220.util;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import net.koreate.www.test20190220.R;

public class LoadingDialog extends Dialog {

    Context context;
    ImageView progress;

    public LoadingDialog(Context context){
        super(context);
        this.context = context;
        setLayoutView();
    }

    public LoadingDialog(Context context, int layout){
        super(context,layout);
        this.context=context;
        setLayoutView();
    }

    private void setLayoutView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.loading_dialog);
        progress = findViewById(R.id.progress);
        Glide.with(context).load(R.raw.main_loading).into(progress);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
    }
}
