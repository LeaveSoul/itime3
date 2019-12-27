package com.example.itime;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class add_change_activity extends AppCompatActivity {

    private Button button1,button2;
    private EditText editTexttitle,editTextday;
    private  int insertposition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_change_activity);

        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        editTextday=(EditText)findViewById(R.id.edittext_day);
        editTexttitle=(EditText)findViewById(R.id.edittext_title);

        editTextday.setText(getIntent().getStringExtra("time"));
        editTexttitle.setText(getIntent().getStringExtra("text"));
        insertposition = getIntent().getIntExtra("insert_position", 0);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.putExtra("time",editTextday.getText().toString());
                intent.putExtra("insert_position",insertposition);
                intent.putExtra("text",editTexttitle.getText().toString());
                setResult(RESULT_OK,intent);
                add_change_activity.this.finish();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_change_activity.this.finish();
            }
        });
    }
}
