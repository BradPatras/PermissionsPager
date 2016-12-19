package com.iboism.permissionspagerexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.R.attr.button;


public class MainActivity extends AppCompatActivity {

    private TextView exampleTextView;
    private Button exampleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exampleTextView = (TextView) findViewById(R.id.exampleTextView);
        exampleButton = (Button) findViewById(R.id.exampleButton);

        exampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPager();
            }
        });
    }

    private void openPager(){
        //TODO
    }
}
