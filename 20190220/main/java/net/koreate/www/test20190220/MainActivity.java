package net.koreate.www.test20190220;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import net.koreate.www.test20190220.util.LoadingDialog;

import java.util.Calendar;

import butterknife.BindArray;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.btnVib) Button btnVib;
    @BindView(R.id.btnSound) Button btnSound;
    @BindView(R.id.btnAlert) Button btnAlert;
    @BindView(R.id.btnList) Button btnList;
    @BindView(R.id.btnDate) Button btnDate;
    @BindView(R.id.btnTime) Button btnTime;
    @BindView(R.id.btnCustom) Button btnCustom;
    @BindView(R.id.btnProgress) Button btnProgress;

    @BindArray(R.array.dog_list) String[] dog_list;
    @BindString(R.string.app_name) String appName;

    AlertDialog basicDialog;
    AlertDialog listDialog;
    LoadingDialog loadingDialg;
    DialogInterface.OnClickListener  listener;
    String dog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log.i("TAG","app name : " + appName);
        initView();
        setDialogInterface();
    }

    private void initView() {
        btnVib.setOnClickListener(this);
        btnSound.setOnClickListener(this);
        btnAlert.setOnClickListener(this);
        btnList.setOnClickListener(this);
        btnDate.setOnClickListener(this);
        btnTime.setOnClickListener(this);
        btnCustom.setOnClickListener(this);
        btnProgress.setOnClickListener(this);
        dog = dog_list[0];
        loadingDialg = new LoadingDialog(MainActivity.this,R.style.whiteProgressStyle);
    }

    @Override
    public void onClick(View v) {
        if(v == btnVib){
            Vibrator vib = (Vibrator) getSystemService(VIBRATOR_SERVICE);
            //vib.vibrate(1000);
            long[] pattern = {1000,500,2000,500,500,500};
            vib.vibrate(pattern,-1);// 반복 안함
            //vib.vibrate(pattern,0);// 반복
            Log.i("MAINACTIVITY",vib.hasVibrator()+"");
            vib.cancel();

            Toast.makeText(this,"Vibrator Click",Toast.LENGTH_SHORT).show();
        }else if(v == btnSound){
            // 알림음
            /*Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Log.i("SOUND","uri : "+uri);
            Ringtone ring = RingtoneManager.getRingtone(getApplicationContext(),uri);
            ring.play();*/

            /*Uri uri = RingtoneManager.getActualDefaultRingtoneUri(
                    getApplicationContext(),
                    RingtoneManager.TYPE_RINGTONE);

            Ringtone ring = RingtoneManager.getRingtone(getApplicationContext(),uri);
            if(ring != null){
                ring.setStreamType(AudioManager.STREAM_ALARM);
                ring.play();
            }
            ring.stop();*/

            MediaPlayer mp  = MediaPlayer.create(getApplicationContext(),R.raw.fallbackring);
            mp.start();
        }else if(v == btnAlert){
            Toast.makeText(this, "btn Alert click", Toast.LENGTH_SHORT).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("NOTICE");
            builder.setIcon(R.drawable.puppy);
            builder.setMessage("확인을 누르면 결제가 완료됩니다! \n 50,000,000원");
            builder.setNegativeButton("OK", listener);
            builder.setPositiveButton("Cancel",listener);
            builder.setNeutralButton("Next",listener);
            builder.setCancelable(false);
            basicDialog = builder.create();
            basicDialog.show();
            //basicDialog.dismiss();
        }else if(v == btnList){
            //dog_list = getResources().getStringArray(R.array.dog_list);
            for(String s : dog_list){
                Log.i("dog_list" ,s);
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("고양이");
            builder.setIcon(R.drawable.puppy);
            //builder.setSingleChoiceItems(R.array.dog_list,0,listener);
            boolean[] checkedItems = {true,true,false,false,false};
            builder.setMultiChoiceItems(dog_list, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                    if(which>=0) {
                        Log.i("multi check", dog_list[which] + " 선택 : " + isChecked);
                    }
                }
            });
            builder.setPositiveButton("확인",listener);
            listDialog = builder.create();
            listDialog.show();
        }else if(v == btnDate){
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    Log.i("datePicker",year+"."+(month+1)+"."+dayOfMonth);
                }
            },year,month,day);
            datePickDialog.show();
        }else if(v == btnTime){
            Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);
            TimePickerDialog timeDialog = new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    Log.i("time picker",hourOfDay+":"+minute);
                }
            },hour,minute,false);
            timeDialog.show();

        }if(v == btnCustom){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
            View view  = inflater.inflate(R.layout.custom_dialog,null);
            builder.setView(view);
            builder.setTitle("CUSTOM");
            builder.setIcon(R.drawable.puppy);
            builder.setNegativeButton("취소",null);
            builder.setPositiveButton("확인",null);
            AlertDialog customDialog = builder.create();
            customDialog.show();
        }else if(v == btnProgress){
            loadingDialg.show();
        }

    }

    private void setDialogInterface() {
        listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(dialog == basicDialog){
                    if(which == DialogInterface.BUTTON_NEGATIVE){
                        Toast.makeText(
                                MainActivity.this,
                                "결제 완료.",
                                Toast.LENGTH_SHORT
                        ).show();
                    }else if(which == DialogInterface.BUTTON_POSITIVE){
                        Toast.makeText(
                                MainActivity.this,
                                "결제 취소",
                                Toast.LENGTH_SHORT
                        ).show();
                    }else if(which == DialogInterface.BUTTON_NEUTRAL){
                        Toast.makeText(
                                MainActivity.this,
                                "다음",
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                }else if(dialog == listDialog){

                    if(which >= 0 ) {
                        dog = dog_list[which];
                        Toast.makeText(MainActivity.this, "index : " + dog_list[which], Toast.LENGTH_SHORT).show();
                    }
                    if(which == DialogInterface.BUTTON_POSITIVE){
                        Toast.makeText(MainActivity.this, "선택된 고양이 : " + dog_list[which]+"입니다.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };
    }

}
