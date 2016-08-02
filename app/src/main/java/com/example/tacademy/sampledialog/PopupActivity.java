package com.example.tacademy.sampledialog;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

public class    PopupActivity extends AppCompatActivity {
    PopupWindow popup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

    View view = getLayoutInflater().inflate(R.layout.popup_button_menu, null);
        view.setBackgroundColor(Color.YELLOW);

        Button btn = (Button)findViewById(R.id.btn_yes);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(PopupActivity.this, "Yes Click", Toast.LENGTH_SHORT).show();
            popup.dismiss();
            }
        });

        popup = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        popup.setOutsideTouchable(true); // 밖 클릭시 사라짐

//        btn = (Button)findViewById(R.id.btn_popup);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                popup.showAsDropDown(view);
//            }
//        });

    }
}


