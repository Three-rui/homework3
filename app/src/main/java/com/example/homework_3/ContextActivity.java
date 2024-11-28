package com.example.homework_3;

import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContextActivity extends AppCompatActivity {
    private ListView listView;
    private ActionMode actionMode;
    private List<Integer> selectedItems = new ArrayList<>();
    private AdapterView.OnItemLongClickListener longClickListener;
    private AdapterView.OnItemClickListener itemClickListener;
    private String[] names = new String[]{"One","Two","Three","Four","Five"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainlistview);

        List<Map<String, Object>> listItems = new ArrayList<>();
        for(int i=0;i<names.length;i++){
            Map<String ,Object> listItem = new HashMap<>();
            listItem.put("name",names[i]);
            listItems.add(listItem);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(this,listItems,R.layout.listview,new String[]{"name","image"},
                new int[]{R.id.name,R.id.image});

        listView = findViewById(R.id.mylist);
        listView.setAdapter(simpleAdapter);


        // 设置长点击监听器以启动ActionMode
        longClickListener = new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (actionMode != null) {
                    return false;
                }
                view.setSelected(true);
                actionMode = startActionMode(actionModeCallback);
                selectedItems.clear();
                selectedItems.add(position);
                actionMode.setTitle("选中了一项");
                return true;
            }
        };

        // 设置点击监听器以处理多选
        itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (actionMode != null) {
                    view.setSelected(!view.isSelected());
                    if (view.isSelected()) {
                        selectedItems.add(position);
                    } else {
                        selectedItems.remove(Integer.valueOf(position));
                    }
                    actionMode.setTitle("选中了 " + selectedItems.size() + " 项");
                }
            }
        };

        listView.setOnItemLongClickListener(longClickListener);
        listView.setOnItemClickListener(itemClickListener);
    }

    private ActionMode.Callback actionModeCallback = new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            getMenuInflater().inflate(R.menu.context_menu, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.one:
                    Toast.makeText(ContextActivity.this, "你当前选中 " + selectedItems.size() + ")", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    break;
                case R.id.two:
                    Toast.makeText(ContextActivity.this, "你点击 " + selectedItems.size() + ")", Toast.LENGTH_SHORT).show();
                    mode.finish();
                    break;
            }
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            actionMode = null;
            for (int position : selectedItems) {
                View view = listView.getChildAt(position);
                if (view != null) {
                    view.setSelected(false);
                }
            }
            selectedItems.clear();
            listView.clearChoices();
        }
    };
}