package com.example.homework_3;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Listview extends AppCompatActivity {
    private String[] names = new String[]{"Lion","Tiger","Monkey","Dog","Cat","Elephant"};
    private int[] imageIds=new int[]{R.drawable.lion,R.drawable.tiger ,R.drawable.monkey,R.drawable.dog,R.drawable.cat,R.drawable.elephant};
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainlistview);
        List<Map<String, Object>> listItems = new ArrayList<>();
        for(int i=0;i<names.length;i++){
            Map<String ,Object> listItem = new HashMap<>();
            listItem.put("name",names[i]);
            listItem.put("image",imageIds[i]);
            listItems.add(listItem);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,listItems,R.layout.listview,new String[]{"name","image"},
                new int[]{R.id.name,R.id.image});
        ListView list=findViewById(R.id.mylist);
        list.setAdapter(simpleAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Map<String, Object> item = (Map<String, Object>) adapterView.getItemAtPosition(position);
                if (item != null) {
                    String selectedName = (String) item.get("name");
                    Toast.makeText(Listview.this, "Selected: " + selectedName, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(Listview.this, "Failed to retrieve item", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void Alertdialog() {
        // 创建一个 AlertDialog.Builder 对象
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // 设置对话框的标题
        builder.setTitle("Custom Dialog");

        // 加载自定义布局
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alertdialog, null);


        // 将自定义布局添加到对话框中
        builder.setView(dialogView);

        // 设置对话框的按钮和点击事件
        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 用户点击了取消按钮
            }
        });

        // 创建并显示对话框
        AlertDialog dialog = builder.create();
        dialog.show();

        EditText usernameEditText = dialogView.findViewById(R.id.editTextText);
        EditText passwordEditText = dialogView.findViewById(R.id.editTextTextPassword);
        Button cancelButton = dialogView.findViewById(R.id.button);
        Button signInButton = dialogView.findViewById(R.id.button2);
        // 设置取消按钮的点击事件（这是对话框自身的按钮）
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // 处理取消逻辑
            }
        });

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取用户输入并处理
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                // TODO: 处理用户名和密码

                // 关闭对话框
                dialog.dismiss();
            }
        });
    }
}
