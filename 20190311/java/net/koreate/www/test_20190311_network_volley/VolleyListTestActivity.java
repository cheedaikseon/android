package net.koreate.www.test_20190311_network_volley;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

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
import net.koreate.www.test_20190311_network_volley.vo.TestVO;

import java.util.ArrayList;

public class VolleyListTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_list_test);
        startNetworkVolley();
    }

    public void startNetworkVolley(){
        final String test_tag = "test_list";
        StringRequest strReq
        = new StringRequest(Request.Method.POST, UrlConfig.TEST_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("VolleyList response", response);
                JsonArray testArray = new JsonParser().parse(response).getAsJsonArray();

                ArrayList<TestVO> list = new ArrayList<>();

                Gson gson = new Gson();

                if(testArray != null && !testArray.isJsonNull()){
                    for(int i=0; i<testArray.size(); i++){
                        JsonObject obj = (JsonObject) testArray.get(i);
                        TestVO testVO = gson.fromJson(obj,TestVO.class);
                        Log.i("testVo"+i,testVO.toString());
                        list.add(testVO);
                    }
                    /*for(JsonElement obj : testArray){

                    }*/
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("error ",error.toString());
            }
        });
        MainController.getInstance().addToRequestQueue(strReq,test_tag);
        /*
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(strReq);*/
    }

}
