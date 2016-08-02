package com.example.tacademy.sampledialog;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onAlertDialog(View view) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("Alert Dialog");
        builder.setMessage("Dialog Message");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Yes click", Toast.LENGTH_SHORT).show();
            }
        });
//        builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                Toast.makeText(MainActivity.this, "Cancel click", Toast.LENGTH_SHORT).show();
//            }
//        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "No click", Toast.LENGTH_SHORT).show();
            }
        });

//        builder.setCancelable(false);

        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                Toast.makeText(MainActivity.this, "dialog cancel", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        }, 2000);
    }

    android.app.AlertDialog dialog;
    Handler mHandler = new Handler(Looper.getMainLooper());

    String[] items = {"item0", "item1","item2","item3","item4"};
    public void onListDialog(View view) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("List...");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position) {
                Toast.makeText(MainActivity.this, "select : " + items[position], Toast.LENGTH_SHORT).show();
            }
        });

        builder.create().show();
    }

    int selectedPosition = -1;
    public void onSingleChoice(View view) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("List...");
        selectedPosition = 2;
        builder.setSingleChoiceItems(items, 2, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                selectedPosition = i;
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (selectedPosition != -1) {
                    Toast.makeText(MainActivity.this, "selected : " + items[selectedPosition], Toast.LENGTH_SHORT).show();
                }
            }
        });

        builder.create().show();
    }

    boolean[] selectedArray = new boolean[items.length];

    public void onMultipleChoice(View view) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("List...");
        selectedArray[1] = true;
        selectedArray[3] = true;
        builder.setMultiChoiceItems(items, selectedArray, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position, boolean checked) {
                selectedArray[position] = checked;
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int position) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < selectedArray.length ; i++) {
                    if (selectedArray[i]) {
                        sb.append(items[i]).append(",");
                    }
                }
                Toast.makeText(MainActivity.this, "items : " + sb.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }

    public void onProgress(View view) {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setIcon(android.R.drawable.ic_dialog_alert);
        dialog.setTitle("Progress Dialog");
        dialog.setMessage("Message.....");
        dialog.show();
    }

    ProgressDialog horizontal;
    public void onHorizontalProgress(View view) {
        horizontal = new ProgressDialog(this);
        horizontal.setIcon(android.R.drawable.ic_dialog_alert);
        horizontal.setTitle("Download...");
        horizontal.setMessage("file download...");
        horizontal.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        horizontal.setMax(100);
        horizontal.show();
        progress = 0;
        mHandler.post(progressRunnable);
    }

    int progress = 0;
    Runnable progressRunnable = new Runnable() {
        @Override
        public void run() {
            if (progress <= 100) {
                horizontal.setProgress(progress);
                progress += 10;
                mHandler.postDelayed(this, 500);
            } else {
                horizontal.dismiss();
            }

        }
    };


    //DialogFragment 클릭시 MyDialogFragmenet 에 있는 onCreateDialog를 호출한다 .
    public void onDialogFragmentClick(View view){
        MyDialogFragment f = new MyDialogFragment();
        f.show(getSupportFragmentManager(), "dialog"); // Dialog는 support이다.
    }

    public void onCustomDialog(View view) {
        LoginFragment f = new LoginFragment();
        f.show(getSupportFragmentManager(), "login");
    }
}
