package net.koreate.www.test_20190311_network_volley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.koreate.www.test_20190311_network_volley.util.MainController;
import net.koreate.www.test_20190311_network_volley.util.UrlConfig;
import net.koreate.www.test_20190311_network_volley.vo.BoardVO;
import net.koreate.www.test_20190311_network_volley.vo.TestVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VolleyMapTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_map_test);

        volleyNetwork();
    }

    public void volleyNetwork(){
        final String tag = "test1_map";

        StringRequest request
        = new StringRequest(Request.Method.POST, UrlConfig.TEST1_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("test1 response " , response);
                JsonObject json = new JsonParser().parse(response).getAsJsonObject();
                JsonArray testArray = json.getAsJsonArray("testList");
                JsonArray boardArray = json.getAsJsonArray("boardList");

                ArrayList<TestVO> testList = new ArrayList<>();
                ArrayList<BoardVO> boardList = new ArrayList<>();

                Gson gson = new Gson();

                for(int i =0; i<testArray.size(); i++){
                    JsonObject obj = (JsonObject)testArray.get(i);
                    TestVO testVO = gson.fromJson(obj,TestVO.class);
                    System.out.println(testVO);
                    testList.add(testVO);
                }

                for(int i=0; i<boardArray.size();i++){
                    JsonObject obj = (JsonObject) boardArray.get(i);
                    BoardVO board = gson.fromJson(obj,BoardVO.class);
                    System.out.println(board);
                    boardList.add(board);
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("test1 error",error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("title","이것은제목이다!");
                return params;
            }
        };
        MainController.getInstance().addToRequestQueue(request,tag);





    }

}
