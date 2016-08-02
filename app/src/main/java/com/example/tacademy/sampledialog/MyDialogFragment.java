package com.example.tacademy.sampledialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

/**
 * Created by Tacademy on 2016-08-02.
 */
public class MyDialogFragment extends android.support.v4.app.DialogFragment {
    String[] items = {"item 1", "item 2", "item 3", "item 4", "item 5", "item 6"}; // secondDialog 에 표현될

    @NonNull
    @Override
    // DialogFragment 생성하기 위해 호출 되는 곳
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Dialog Fragment");
//        builder.setMessage("Show Dialog Fragment");
// ============================== secondDialog 에 DialogFragment 생성=============================
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String message = items[i];
                SecondDialogFragment f = SecondDialogFragment.newInstance(message);
                android.support.v4.app.Fragment old = getFragmentManager().findFragmentByTag("dialog");
                android.support.v4.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                if (old != null) {
                    ft.remove(old);
                }
                ft.addToBackStack(null);
                f.show(ft, "childfragment");
            }
        });


        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getContext(), "OK Click", Toast.LENGTH_SHORT).show();
            }
        });

        return builder.create();
    }
    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }
        //뒤로가기 할때 호출
    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }


    //사라질 때 호출

}