package net.koreate.www.pushzzi;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    CanvasView mView;           // 화면을 그려줄 캔버스
    Point mPoTouch;             // 터치포인트 좌표
    Bitmap[] mImage;            // 이미지 배열
    int[][] mScreenImageType;  // 비트맵 이미지 스크린배치 배열
    int mGameLevelNum;          // 게임 스테이지 번호

    final int COUNT_SCREEN_IMAGE_ROW = 12;      //화면 행 개수
    final int COUNT_SCREEN_IMAGE_COL = 10;      //화면 열 개수

    final int LENGTH_SCREEN_START_X = 0;        // 시간 화면 X 좌표
    final int LENGTH_SCREEN_START_Y = 0;        // 시간 화면 Y 좌표
    int LENGTH_IMAGE_WIDTH = 48;                 // 이미지 넓이
    int LENGTH_IMAGE_HEIGHT = 48;               // 이미지 높이
    final int MAX_GAME_LEVEL_NUM = 36;          // 최대 스테이지 수

    final int IMAGE_TYPE_BACK = 0;              // 배경
    final int IMAGE_TYPE_BLOCK = 1;             // 벽
    final int IMAGE_TYPE_CHUR = 2;             // 츄르
    final int IMAGE_TYPE_HOUSE_EMPTY = 3;         // 빈집
    final int IMAGE_TYPE_HOUSE_FULL = 4;         // 츄르&집
    final int IMAGE_TYPE_MOZZI = 5;           // 모찌
    final int IMAGE_TYPE_MOZZI_IN_HOUSE = 6;  // 집& 모찌

    final  int KEY_UP = 0;                          // UP 버튼
    final  int KEY_DOWN = 1;                          // DOWN 버튼
    final  int KEY_LEFT = 2;                          // LEFT 버튼
    final  int KEY_RIGHT = 3;                          // RIGHT 버튼




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initVariable();

        mView = new CanvasView(this);
        FrameLayout f = findViewById(R.id.mainlayout);
        f.addView(mView,0);
        loadDataFile(mGameLevelNum);

    }

    private void initVariable() {
        mPoTouch = new Point(-1,-1);
        mImage = new Bitmap[6];
        for(int i=0; i<6; i++){
            mImage[i] = null;
        }

        mImage[IMAGE_TYPE_BACK]
                = BitmapFactory.decodeResource(getResources(),R.drawable.img_back);
        mImage[IMAGE_TYPE_BLOCK]
                = BitmapFactory.decodeResource(getResources(),R.drawable.img_block);
        mImage[IMAGE_TYPE_CHUR]
                = BitmapFactory.decodeResource(getResources(),R.drawable.img_chur);
        mImage[IMAGE_TYPE_HOUSE_EMPTY]
                = BitmapFactory.decodeResource(getResources(),R.drawable.img_house_empty);
        mImage[IMAGE_TYPE_HOUSE_FULL]
                = BitmapFactory.decodeResource(getResources(),R.drawable.img_house_full);
        mImage[IMAGE_TYPE_MOZZI]
                = BitmapFactory.decodeResource(getResources(),R.drawable.img_mozzi);

        mScreenImageType = new int[COUNT_SCREEN_IMAGE_ROW][COUNT_SCREEN_IMAGE_COL];
        int i=0; int j=0;
        for(j=0; j<COUNT_SCREEN_IMAGE_ROW; j++){
            for(i=0; i<COUNT_SCREEN_IMAGE_COL; i++){
                mScreenImageType[j][i] = 0;
            }
        }
        mGameLevelNum = 1;
    }

    private String readTextFile(String strFilePath){
        String text = null;
        try {
            InputStream is  = getAssets().open(strFilePath);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            text = new String(buffer);
            Log.i("text info",text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

    private void loadDataFile(int levelNum){
        String bufFile = null;
        String filePath = null;

        if(levelNum < 1 || levelNum > MAX_GAME_LEVEL_NUM){
            return;
        }

        filePath = String.format("data/stage_%d.txt",levelNum);

        bufFile = readTextFile(filePath);

        int pos = 0, row=0, col = 0, length =0, imageType =0;
        String bufLine, bufItem;
        while((pos = bufFile.indexOf("\n")) >= 0){
            bufLine = bufFile.substring(0,pos);
            bufLine.trim();
            bufFile = bufFile.substring(pos+1);
            length = bufFile.length();
            if(length <= 1){
                continue;
            }
            if(length > COUNT_SCREEN_IMAGE_COL){
                length = COUNT_SCREEN_IMAGE_COL;
            }

            for(col =0; col<length; col++){
                bufItem = bufLine.substring(col,col+1);
                imageType = Integer.parseInt(bufItem);
                mScreenImageType[row][col] = imageType;
            }
            row++;
            if(COUNT_SCREEN_IMAGE_ROW <= row){
                break;
            }

            mGameLevelNum = levelNum;

            String titleText;
            titleText = String.format("PUSH MOZZI level %d",mGameLevelNum);
            this.setTitle(titleText);

            // CanvasView 갱신
            mView.invalidate();

        }

    }

    public void onClick(View v){
        boolean gameComplete = false;

        switch (v.getId()){
            case R.id.buttonLevelRetry:
                loadDataFile(mGameLevelNum);
                break;
            case R.id.buttonLevelPrev :
                loadDataFile(mGameLevelNum - 1);
                break;
            case R.id.buttonMoveUp :
                moveToPushMozzi(KEY_UP);
                gameComplete = isGameComplete();
                break;
            case R.id.buttonMoveDown :
                moveToPushMozzi(KEY_DOWN);
                gameComplete = isGameComplete();
                break;
            case R.id.buttonMoveLeft :
                moveToPushMozzi(KEY_LEFT);
                gameComplete = isGameComplete();
                break;
            case R.id.buttonMoveRight :
                moveToPushMozzi(KEY_RIGHT);
                gameComplete = isGameComplete();
                break;

        }
        if(gameComplete){
            onCompleteGame();
        }
    }
    public void onCompleteGame(){
        String message = String.format("축하합니다 모찌에게 level %d 의 츄르를 가져다 주셨습니다 '-'",mGameLevelNum);
        new AlertDialog.Builder(this).setMessage(message).setTitle("다음 츄르를 모찌 집에 가져다 주시겠습니까?").setCancelable(false).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                loadDataFile(mGameLevelNum+1);
            }
        }).show();
    }


    public boolean isGameComplete(){
        for(int row=0; row<COUNT_SCREEN_IMAGE_ROW; row++){
            for(int col=0; col<COUNT_SCREEN_IMAGE_COL; col++){
                if(mScreenImageType[row][col] == IMAGE_TYPE_CHUR){
                    return false;
                }
            }
        }
        return true;
    }


    public Point getPositionPushMozzi(){
        int col=0, row=0;
        Point pushMozzi = new Point();
        for(row=0; row<COUNT_SCREEN_IMAGE_ROW;row++){
            for(col=0; col<COUNT_SCREEN_IMAGE_COL; col++){
                if(mScreenImageType[row][col] == IMAGE_TYPE_MOZZI ||
                        mScreenImageType[row][col] == IMAGE_TYPE_MOZZI_IN_HOUSE){
                    pushMozzi.x = col;
                    pushMozzi.y = row;
                    return pushMozzi;
                }
            }
        }
        return pushMozzi;
    }

    public boolean insertChurToCell(Point poCell){
        switch(mScreenImageType[poCell.y][poCell.x]){
            case IMAGE_TYPE_BACK :
                mScreenImageType[poCell.y][poCell.x] = IMAGE_TYPE_CHUR;
                break;
            case IMAGE_TYPE_HOUSE_EMPTY :
                mScreenImageType[poCell.y][poCell.x] = IMAGE_TYPE_HOUSE_FULL;
                break;
            default:
                // 이동 불가
                return false;
        }
        return true;
    }

    public void moveToPushMozzi(int keyType){
        Point poPushMozzi, poNewPushMozzi,poNewBall;
        poPushMozzi = getPositionPushMozzi();
        poNewPushMozzi = new Point(poPushMozzi);
        poNewBall = new Point(poPushMozzi);

        switch (keyType){
            case KEY_UP :
                poNewPushMozzi.y -= 1;
                poNewBall.y -= 2;
                break;
            case KEY_DOWN :
                poNewPushMozzi.y += 1;
                poNewBall.y += 2;
                break;
            case KEY_LEFT :
                poNewPushMozzi.x -= 1;
                poNewBall.x -= 2;
                break;
            case KEY_RIGHT :
                poNewPushMozzi.x += 1;
                poNewBall.x += 2;
                break;
        }

        switch(mScreenImageType[poNewPushMozzi.y][poNewPushMozzi.x]){
            case IMAGE_TYPE_BACK :
                mScreenImageType[poNewPushMozzi.y][poNewPushMozzi.x]
                        = IMAGE_TYPE_MOZZI;
                break;
            case IMAGE_TYPE_BLOCK :
                return;
            case IMAGE_TYPE_CHUR:
                if(insertChurToCell(poNewBall)){
                    mScreenImageType[poNewPushMozzi.y][poNewPushMozzi.x]
                            = IMAGE_TYPE_MOZZI;
                }else{
                    return;
                }
                break;
            case IMAGE_TYPE_HOUSE_EMPTY :
                mScreenImageType[poNewPushMozzi.y][poNewPushMozzi.x]
                        = IMAGE_TYPE_MOZZI_IN_HOUSE;
                break;
            case IMAGE_TYPE_HOUSE_FULL :
                if(insertChurToCell(poNewBall)){
                    mScreenImageType[poNewPushMozzi.y][poNewPushMozzi.x]
                            = IMAGE_TYPE_MOZZI_IN_HOUSE;
                }else{
                    return;
                }
                break;
        }
        recoverToEmptyCell(poPushMozzi);
        mView.invalidate();
    }

    public void recoverToEmptyCell(Point poCell){
        switch(mScreenImageType[poCell.y][poCell.x]){
            case IMAGE_TYPE_CHUR:
                mScreenImageType[poCell.y][poCell.x] = IMAGE_TYPE_BACK;
                break;
            case IMAGE_TYPE_HOUSE_FULL :
                mScreenImageType[poCell.y][poCell.x] = IMAGE_TYPE_HOUSE_EMPTY;
                break;
            case IMAGE_TYPE_MOZZI:
                mScreenImageType[poCell.y][poCell.x] = IMAGE_TYPE_BACK;
                break;
            case IMAGE_TYPE_MOZZI_IN_HOUSE:
                mScreenImageType[poCell.y][poCell.x] = IMAGE_TYPE_HOUSE_EMPTY;
                break;
        }
    }

    protected class CanvasView extends View{

        public CanvasView(Context context) {super(context);}

        @Override
        public void onDraw(Canvas can){
            drawScreenImage(can);
        }

        public Rect getScreenRect(){
            Rect rtScreen  = new Rect();
            rtScreen.left =0;
            rtScreen.top = 0;
            rtScreen.right = this.getWidth();
            rtScreen.bottom =this.getHeight();
            return rtScreen;
        }

        public void drawScreenImage(Canvas canvas){
            Rect rScreen = getScreenRect();

            LENGTH_IMAGE_WIDTH = (int)(rScreen.width()/COUNT_SCREEN_IMAGE_COL);
            LENGTH_IMAGE_HEIGHT = LENGTH_IMAGE_WIDTH;
            int imageType =0, posX =0, posY =0, width = 0, height = 0;

            for(int j=0; j<COUNT_SCREEN_IMAGE_ROW; j++){
                for(int i=0; i<COUNT_SCREEN_IMAGE_COL; i++){
                    imageType = mScreenImageType[j][i];
                    if(IMAGE_TYPE_MOZZI_IN_HOUSE == imageType){
                        imageType = IMAGE_TYPE_MOZZI;
                    }

                    posX = LENGTH_SCREEN_START_X+ i * LENGTH_IMAGE_WIDTH;
                    posY = LENGTH_SCREEN_START_Y+ j * LENGTH_IMAGE_HEIGHT;
                    width = LENGTH_IMAGE_WIDTH;
                    height= LENGTH_IMAGE_HEIGHT;

                    canvas.drawBitmap(
                            mImage[imageType],
                            null,
                            new Rect(posX,posY,posX+width,posY+height),
                            null);
                }

            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean gameComplete = false;
        super.onTouchEvent(event);

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            mPoTouch.x = (int)event.getX();
            mPoTouch.y = (int)event.getY();
            Log.i("action event ","X : "+mPoTouch.x +" Y : "+mPoTouch.y);
        }else if(event.getAction() == MotionEvent.ACTION_MOVE){
            if(mPoTouch.x == -1 && mPoTouch.y == -1){
                return true;
            }
            int nMoveX = (int)event.getX() - mPoTouch.x;
            int nMoveY = (int)event.getY() - mPoTouch.y;

            if(Math.abs(nMoveX) >= LENGTH_IMAGE_WIDTH){
                if(nMoveX < 0){
                    moveToPushMozzi(KEY_LEFT);
                }else{
                    moveToPushMozzi(KEY_RIGHT);
                }
            }else if(Math.abs(nMoveY) >= LENGTH_IMAGE_HEIGHT){
                if(nMoveY < 0){
                    moveToPushMozzi(KEY_UP);
                }else{
                    moveToPushMozzi(KEY_DOWN);
                }
            }else {
                return true;
            }

            gameComplete = isGameComplete();
            mPoTouch.x = (int)event.getX();
            mPoTouch.y = (int)event.getY();

        }else if(event.getAction() == MotionEvent.ACTION_UP) {
            mPoTouch.x = -1;
            mPoTouch.y = -1;
        }

        if(gameComplete){
            mPoTouch.x = -1;
            mPoTouch.y = -1;
            onCompleteGame();
        }
        return true;
    }
}
