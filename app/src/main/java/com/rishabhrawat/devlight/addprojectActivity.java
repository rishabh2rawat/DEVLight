package com.rishabhrawat.devlight;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.rishabhrawat.devlight.Account_Activity.AccountActivity;

public class addprojectActivity extends AppCompatActivity {
    private static final String TAG = "addprojectActivity";

    ImageView backarrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproject);
        /*link to the xml-------------------------------*/
        backarrow = (ImageView) findViewById(R.id.arrow_back);






        /*-----back arrow -----------------*/
        Log.d(TAG, "onCreate: on back arrow click ");
        backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addprojectActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });
    }
}
