package net.koreate.www.test_20190304_intent_callback;

import android.Manifest;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.Ringtone;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import net.koreate.www.test_20190304_intent_callback.util.ContactUtil;
import net.koreate.www.test_20190304_intent_callback.vo.ContactVO;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotifyActivity extends AppCompatActivity {

    // 무음 // 진동 // 벨소리
    @BindView(R.id.btnNotify)
    Button btnNotify;
    // 주소록 목록
    @BindView(R.id.btnAddress)
    Button btnAddress;
    // 벨소리 변경 확인
    @BindView(R.id.btnAlim)
    Button btnAlim;

    @BindView(R.id.btnStop)
    Button btnStop;

    Ringtone ring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify);
        ButterKnife.bind(this);

        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("모드 설정 변경");

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                    NotificationManager manager
                    = (NotificationManager)getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);

                    if(manager.isNotificationPolicyAccessGranted()){
                        setNotification();
                    }else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(NotifyActivity.this);
                        builder.setTitle("권한 불충분");
                        builder.setMessage("알림 설정 변경을 위해서는 권한이 필요합니다. \n 알림 접근 권한 설정창으로 이동하시겠습니까?");
                        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(Settings.ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);
                                startActivityForResult(intent, 2001);
                            }
                        });
                        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }else{
                    setNotification();
                }
            }
        });

        btnAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("주소록 보기");
                PermissionListener listener =new PermissionListener() {
                    // 권한이 허용 되었을때.
                    @Override
                    public void onPermissionGranted() {
                        showToast("Permission Granted!");
                        ContactUtil cu = new ContactUtil(getApplicationContext());
                        ArrayList<ContactVO> contactList = cu.getContectList();
                        for(ContactVO cv : contactList){
                            Log.i("contactList",cv.toString());
                        }

                        Intent i = new Intent(NotifyActivity.this, ContactActivity.class);
                        i.putExtra("contactList",contactList);
                        startActivityForResult(i,3001);




                    }
                    // 권한이 허용되지 않았을때
                    @Override
                    public void onPermissionDenied(List<String> deniedPermissions) {
                        showToast("Permission denied \n"+deniedPermissions.toString());
                    }
                };

                TedPermission.with(NotifyActivity.this)
                        .setPermissionListener(listener)
                        .setDeniedMessage("권한 설정이 거부 되었습니다.\n 설정 창에 권한 설정이 가능합니다.")
                        .setPermissions(Manifest.permission.READ_CONTACTS)
                        .check();


            }
        });

        btnAlim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    // 권한  확인 o
                    if(Settings.System.canWrite(getApplicationContext())){

                    }else{
                        Intent i = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
                        i.setData(Uri.parse("package:"+NotifyActivity.this.getPackageName()));
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    }
                }else{
                    // 권한  확인 x
                }
            }
        });

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ring != null)ring.stop();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 2001 :
                showToast("권한 설정 완료");
                break;
            case 3001 :
                ContactVO contactVO = (ContactVO) data.getSerializableExtra("contactVO");
                showToast(contactVO.toString());
                break;
        }
    }

    public void setNotification(){
        final AudioManager audioManager = (AudioManager)getApplicationContext().getSystemService(Context.AUDIO_SERVICE);
        // 무음 0
        System.out.println("무음 코드 : " + AudioManager.RINGER_MODE_SILENT);
        // 진동 1
        System.out.println("진동 코드 : " + AudioManager.RINGER_MODE_VIBRATE);
        // 기본 2
        System.out.println("기본 코드 : " + AudioManager.RINGER_MODE_NORMAL);
        // 현재상태
        System.out.println("현재 상태 : " + audioManager.getRingerMode());

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("알림 모드 설정");
        builder.setSingleChoiceItems(R.array.bellName, audioManager.getRingerMode(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int[] bellCodes = getResources().getIntArray(R.array.bellCodes);
                switch(which){
                    case 0 :
                        // 무음모드로 설정
                        audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                        showToast("무음모드");
                      break;
                    case 1 :
                        // 진동 모드
                        audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                        showToast("진동 모드");
                        break;
                    case 2 :
                        audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                        showToast("기본 모드");
                        break;
                }
            }
        });

        builder.setPositiveButton("완료", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

}
