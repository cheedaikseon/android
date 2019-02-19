package net.koreate.www.myapplication2.aaa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;

import net.koreate.www.myapplication2.R;

import java.util.Stack;
import java.util.StringTokenizer;

public class GridActivity extends AppCompatActivity implements View.OnClickListener{

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);
        editText = findViewById(R.id.editText);
        GridLayout wrapGrid = findViewById(R.id.wrapGrid);
        int childCount = wrapGrid.getChildCount();
        System.out.println("자식 VIEW 개수 : " + childCount);
        System.out.println("0인덱스 요소 : " + wrapGrid.getChildAt(0));

        for(int i=0; i<childCount;i++){
            View v = wrapGrid.getChildAt(i);
            if(v instanceof Button){
                System.out.println("childAt "+i+" : "+((Button)v).getText());
                v.setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {
        Button btn = (Button)v;
        String btnStr = btn.getText().toString();
        String resultText = editText.getText().toString();
        System.out.println("btn value : "+btnStr);
        if(btnStr.equals("=")){
            // 연산
            calc(resultText);
        }else if(btnStr.equals("C")){
            editText.setText("");
        }else{
            resultText = resultText + btnStr;
            editText.setText(resultText);
            System.out.println("resultText : "+resultText);
        }
    }

    public void calc(String str){
        StringTokenizer st_num = new StringTokenizer(str,"+-/*");
        StringTokenizer st_opt = new StringTokenizer(str,"1234567890");
        /*
        System.out.println("토큰 num : " + st_num.countTokens());
        System.out.println("토큰 opt : " + st_opt.countTokens());


        while(st_num.hasMoreTokens()){
            String num = st_num.nextToken();
            System.out.println("st_num value : " + num);
        }

        while(st_opt.hasMoreTokens()){
            String opt = st_opt.nextToken();
            System.out.println("st_opt value : " + opt);
        }

        System.out.println("토큰 num : " + st_num.countTokens());
        System.out.println("토큰 opt : " + st_opt.countTokens());
*/
        Stack<Integer> value = new Stack<>();
        // peek (현재 뱉어낼 값을 반환)
        // push (영역 가장 뒤에 값을 추가)
        // pop  (현재 뱉어낼 값을 가져온뒤 값 삭제/추출)
        if(st_num.countTokens() > 0){
            value.push(Integer.parseInt(st_num.nextToken()));
        }

        while(st_num.hasMoreTokens()){
            String opt = st_opt.nextToken();
            System.out.println("opt : " + opt);

            String num = st_num.nextToken();
            System.out.println("num : " + num);

            int a =0;

            if(opt.equals("*")){
                a = value.pop();
                value.push(a * Integer.parseInt(num));
            }else if(opt.equals("/")){
                a = value.pop();
                value.push(a / Integer.parseInt(num));
            }else if(opt.equals("+")){
                value.push(Integer.parseInt(num));
            }else if(opt.equals("-")){
                value.push(-1*(Integer.parseInt(num)));
            }
        }

        int tot = 0;
        while(!value.isEmpty()){
            System.out.println("peek : "+value.peek());
            tot += value.pop();
            System.out.println("tot : "+tot);
        }
        editText.setText(String.valueOf(tot));
    }

}
