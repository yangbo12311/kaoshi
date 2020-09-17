package com.yangbo28.info;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class NickActivity extends AppCompatActivity {


    private Button btn_sure;
    private EditText nick_name;
    private JkbdApplication application;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nick);
        application = (JkbdApplication) getApplication();
        btn_sure = findViewById(R.id.btn_sure);
        nick_name = findViewById(R.id.nick_name);

        btn_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(nick_name.getText().toString())){
                    Toast.makeText(NickActivity.this,"签名不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                application.setNick(nick_name.getText().toString());
                NickActivity.this.finish();
            }
        });

    }
}
