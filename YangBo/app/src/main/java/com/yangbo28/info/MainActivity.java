package com.yangbo28.info;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView list_view;
    private MovieListAdapter adapter ;
    private TextView nick_name;
    private JkbdApplication application;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        application = (JkbdApplication) getApplication();
        list_view = findViewById(R.id.list_view);
        nick_name = findViewById(R.id.nick_name);
        adapter = new MovieListAdapter(this);
        nick_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,NickActivity.class);
                startActivity(intent);
            }
        });
        list_view.setAdapter(adapter);
        String result = FileUtil.getJson("DouBanMovie.json",this);
        Gson gson = new Gson();
        JsonResult jsonResult = gson.fromJson(result,JsonResult.class);
        adapter.addList(jsonResult.getList());
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(!TextUtils.isEmpty(application.getNick())){
            nick_name.setText(application.getNick());
        }
    }

    public class JsonResult{
        private int result;
        ArrayList<MovieInfo> list;

        public int getResult() {
            return result;
        }

        public void setResult(int result) {
            this.result = result;
        }

        public ArrayList<MovieInfo> getList() {
            return list;
        }

        public void setList(ArrayList<MovieInfo> list) {
            this.list = list;
        }
    }
}
