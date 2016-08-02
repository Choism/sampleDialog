package com.example.tacademy.sampledialog;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ListPopupWindow;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
// 자동검색기능 같은 원리
// 네트워크에서 값을 받아서 리스트 로 표현
public class ListPopupActivity extends AppCompatActivity {

    ListPopupWindow listPopup;
    ArrayAdapter<String> mAdapter;
    EditText keywordView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_popup);
        keywordView = (EditText)findViewById(R.id.edit_keyword);
        listPopup = new ListPopupWindow(this);  //ListPopupWindow 선언
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        listPopup.setAdapter(mAdapter);
        listPopup.setAnchorView(keywordView);
        listPopup.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String text = mAdapter.getItem(position); // text 호출시 아래 final String text가 또 호출되기에 is Forced를 선언하여 방지
                isForced = true;
                keywordView.setText(text);
                isForced = false;
                listPopup.dismiss();


            }
        });
        keywordView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (isForced) return;
                final String text = charSequence.toString();    //값이 변경 되면 data를 받아옴
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        String[] list = {text+"01", text+"02", text+"03"};
                        mAdapter.clear();
                        mAdapter.addAll(list);
                        listPopup.show();
                    }
                }, 500);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    boolean isForced = false;   // text 호출시 아래 final String text가 두번 호출 되는 걸 isForced를 선언하여 방지

    Handler mHandler = new Handler(Looper.getMainLooper());
}
