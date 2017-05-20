package com.example.kimsoohyeong.week12;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MyOwnCanvasActivity extends AppCompatActivity {
    MyOwnCanvas myOwnCanvas;
    CheckBox cBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_own_canvas);

        myOwnCanvas = (MyOwnCanvas) findViewById(R.id.myowncanvas);
        cBox = (CheckBox) findViewById(R.id.checkbox);
        cBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                myOwnCanvas.setIsStamp(isChecked);
                Log.d("DEBUG", "CHECKBOX : " + (isChecked ? "TRUE" : "FALSE"));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 1, 0, "Bluring");
        menu.add(0, 2, 0, "Coloring");
        menu.add(0, 3, 0, "Pen Width Big");
        menu.add(1, 4, 0, "Pen Color RED");
        menu.add(2, 5, 0, "Pen Color BLUE");

        menu.setGroupCheckable(0, true, false);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 1) {
            if (item.isChecked()) {
                item.setChecked(false);
            } else {
                item.setChecked(true);
            }
            myOwnCanvas.setBluring(item.isChecked());
        } else if (item.getItemId() == 2) {
            if (item.isChecked()) {
                item.setChecked(false);
            } else {
                item.setChecked(true);
            }
            myOwnCanvas.setColoring(item.isChecked());
        } else if (item.getItemId() == 3) {
            if (item.isChecked()) {
                item.setChecked(false);
            } else {
                item.setChecked(true);
            }
            myOwnCanvas.setBigWidth(item.isChecked());
        } else if (item.getItemId() == 4) {
            myOwnCanvas.setColor(0);
        } else if (item.getItemId() == 5) {
            myOwnCanvas.setColor(1);
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View v) {
        if (v.getTag() != null) {
//            Log.d("DEBUG", (String) v.getTag());
            cBox.setChecked(true);
            Log.d("DEBUG", "CHECKBOX : " + (cBox.isChecked() ? "TRUE" : "FALSE"));
            myOwnCanvas.setIsStamp(true);
            myOwnCanvas.setOperationType((String) v.getTag());
            Toast.makeText(this, v.getTag().toString() + " 모드입니다.",
                    Toast.LENGTH_SHORT).show();
        } else {
            Log.d("DEBUG", Integer.valueOf(v.getId()).toString());
            if (v.getId() == R.id.eraser) {
                myOwnCanvas.clear();
            } else if (v.getId() == R.id.open) {
                File f = new File(getFilesDir() + "picture.jpg");
                if (f.isFile()) {
                    myOwnCanvas.clear();
                    Bitmap loadImg = BitmapFactory.decodeFile(getFilesDir() + "picture.jpg");
                    myOwnCanvas.openImg(loadImg);
                } else {
                    Toast.makeText(this, "저장된 파일이 없습니다.",
                            Toast.LENGTH_SHORT).show();
                }
                // TODO : 그림 띄워주기
            } else if (v.getId() == R.id.save) {
                // TODO : SAVE
                Bitmap saveImg = myOwnCanvas.getBitmap();
                try {
                    FileOutputStream fos = new FileOutputStream(
                            getFilesDir() + "picture.jpg");
                    saveImg.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                    fos.close();
                    Toast.makeText(this, "저장완료",
                            Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(this, e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
