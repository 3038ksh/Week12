//package com.example.kimsoohyeong.week12;
//
//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//
//public class MainActivity extends AppCompatActivity {
//    MyCanvas myCanvas;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        myCanvas = (MyCanvas) findViewById(R.id.canvas);
//    }
//
//    public void onClick(View v) {
//        myCanvas.setOperationType((String)v.getTag());
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        menu.add(0, 1, 0, "Bluring");
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == 1) {
//            myCanvas.setOperationType("blur");
//        }
//        return super.onOptionsItemSelected(item);
//    }
//}
