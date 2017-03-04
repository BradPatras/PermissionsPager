package com.iboism.permissionspagerexample;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.iboism.permissionspager.PagerActivity;
import com.iboism.permissionspager.PermissionsPager;

import java.util.Arrays;

import static android.R.attr.button;
import static com.iboism.permissionspager.PermissionsPager.DENIED_ARRAY_INTENT_KEY;
import static com.iboism.permissionspager.PermissionsPager.GRANTED_ARRAY_INTENT_KEY;


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
        //Updated builder-style method for showing permission pager
        PermissionsPager.buildPermissionsPager(this)
                .with(Manifest.permission.ACCESS_FINE_LOCATION, "We need to see where u at")
                .and(Manifest.permission.WRITE_EXTERNAL_STORAGE, "Maybe this is a file explorer app")
                .and(Manifest.permission.READ_CALENDAR, "Or maybe this is a calendar app")
                .show();

        //Deprecated method for showing permission pager
//        String[] p = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_CALENDAR};
//        String[] r = {"We need to see where u at", "We need to see wat ur up to"};
//        PermissionsPager.showPermissionsPager(p,r,this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            String[] denied = data.getStringArrayExtra(DENIED_ARRAY_INTENT_KEY);
            String[] granted = data.getStringArrayExtra(GRANTED_ARRAY_INTENT_KEY);
            exampleTextView.setText("Denied Permissions: \n" + Arrays.toString(denied) + "\n" + "Granted Permissions: \n" + Arrays.toString(granted));
        }
    }
}
