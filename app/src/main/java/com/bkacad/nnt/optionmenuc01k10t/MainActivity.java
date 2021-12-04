package com.bkacad.nnt.optionmenuc01k10t;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Khai báo view
    private Button btnShow;
    private PopupMenu popupMenu;
    private TextView tvTitle;
    private ListView lvTodo;
    private List<String> dataTodo;
    private ArrayAdapter<String> myAdapter;
    private MyDialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnShow = findViewById(R.id.btnShow);
        tvTitle = findViewById(R.id.tvTitle);
        lvTodo = findViewById(R.id.lvTodo);

        // Fake du lieu
        dataTodo = new ArrayList<>();
        dataTodo.add("1. Đi chợ");
        dataTodo.add("2. Nấu cơm");
        dataTodo.add("3. Học bài");

        // Tao Adapter - ArrayAdapter
        myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,dataTodo);

        // Set Adapter cho listview
        lvTodo.setAdapter(myAdapter);

        // Đăng kí context menu cho listview
        registerForContextMenu(lvTodo);

        // Tạo sẵn popup Menu
        popupMenu = new PopupMenu(this,tvTitle);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hiển thị popup Menu
                popupMenu.show();
            }
        });

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.main_popup_menu_action1:
                        Toast.makeText(MainActivity.this,"Action 1",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.main_popup_menu_action2:
                        Toast.makeText(MainActivity.this, "Action 2", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }
    // Khi đè Ctrl + O


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.main_context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_context_menu_add_new:
                // Show dialog add new lên -> xử lý sau
                if(myDialog == null){
                    myDialog = new MyDialog(this) {
                        @Override
                        public void addNewTodo(String data) {
                            dataTodo.add(data);
                            myAdapter.notifyDataSetChanged();
                        }
                    };
                }
                myDialog.show();

                break;

            case R.id.main_context_menu_get_count:
                Toast.makeText(MainActivity.this, "Count "+dataTodo.size(), Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Xử lý sự kiện khi click vào các item menu

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_option_menu_settings:
                Toast.makeText(MainActivity.this,"Mở trang settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.main_option_menu_abouts:
                Toast.makeText(MainActivity.this,"Abouts",Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}