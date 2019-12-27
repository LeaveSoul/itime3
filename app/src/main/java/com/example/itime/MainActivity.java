package com.example.itime;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int CONTEXT_MENU_DELETE = 1;
    public static final int CONTEXT_MENU_NEW = CONTEXT_MENU_DELETE+1;
    public static final int CONTEXT_MENU_ABOUT = CONTEXT_MENU_NEW+1;
    public static final int REQUEST_CODE_NEWTIME = 901;
    public static final int CONTEXT_MENU_UPDATE = CONTEXT_MENU_ABOUT+1;
    public static final int REQUEST_CODE_UPDATETIME=902;
    ListView listViewtimes;
    private List<time> listtimes=new ArrayList<>();
    timesaver timesaver;
    TimeAdapter timeAdapter;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timesaver.save();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timesaver=new timesaver(this);
        listtimes=timesaver.load();
        if(listtimes.size()==0)
        init();
        
        listViewtimes= this.findViewById(R.id.list_view_times);

        timeAdapter = new TimeAdapter(MainActivity.this, R.layout.listitem_times,listtimes );
        ((ListView) findViewById(R.id.list_view_times)).setAdapter(timeAdapter);

        this.registerForContextMenu(listViewtimes);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v==listViewtimes){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        //设置标题
        menu.setHeaderTitle(listtimes.get(info.position).getName());
        //设置内容 参数1为分组，参数2对应条目的id，参数3是指排列顺序，默认排列即可
        menu.add(0, CONTEXT_MENU_DELETE, 0, "删除");
        menu.add(0, CONTEXT_MENU_NEW, 0, "添加");
        menu.add(0, CONTEXT_MENU_UPDATE, 0, "修改");
        menu.add(0, CONTEXT_MENU_ABOUT, 0, "鸣谢   ");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE_NEWTIME:
                if(requestCode == RESULT_OK){
                    String text = data.getStringExtra("text" );
                    int insertPosition = data.getIntExtra("inert_position",0);
                    String day = data.getStringExtra("day");
                    listtimes.add(new time("day",R.drawable.ic_launcher_background,"text"));
                    timeAdapter.notifyDataSetChanged();
                }
                break;
            case REQUEST_CODE_UPDATETIME:
                if(requestCode == RESULT_OK){
                    int insertPosition =data.getIntExtra("insert_position",0);
                    timeAdapter.notifyDataSetChanged();
                }
              break;

        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case CONTEXT_MENU_DELETE :
                AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                listtimes.remove(info.position);

                timeAdapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,"删除成功",Toast.LENGTH_LONG).show();
                break;
            case CONTEXT_MENU_NEW:
                int position=((AdapterView.AdapterContextMenuInfo)item.getMenuInfo()).position;
                Intent intent = new Intent(this,add_change_activity.class);
                intent.putExtra("time","2019.2.17");
                intent.putExtra("text","111");
                intent.putExtra("insert_position",position);
                startActivityForResult(intent, REQUEST_CODE_NEWTIME);
                break;
            case CONTEXT_MENU_UPDATE:
                int position2=((AdapterView.AdapterContextMenuInfo)item.getMenuInfo()).position;
                Intent intent2 = new Intent(this,add_change_activity.class);
                intent2.putExtra("time","2019.2.17");
                intent2.putExtra("text","111");
                intent2.putExtra("insert_position",position2);
                startActivityForResult(intent2, REQUEST_CODE_UPDATETIME);

            case CONTEXT_MENU_ABOUT:
                Toast.makeText(MainActivity.this,"感谢支持",Toast.LENGTH_LONG).show();
                break;
        }
        return super.onContextItemSelected(item);
    }


    private void init() {
        listtimes.add(new time("2019.2.17",R.drawable.ic_launcher_background,"111"));
        listtimes.add(new time("2019.2.17",R.drawable.ic_launcher_background,"111"));
        listtimes.add(new time("2020.2.17",R.drawable.ic_launcher_background,"1111"));
    }


    class TimeAdapter extends ArrayAdapter<time> {

        private int resourceId;

        TimeAdapter(Context context, int resource, List<time> objects) {
            super(context, resource, objects);
            resourceId = resource;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            time time = getItem(position);//获取当前项的实例
            View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            ((ImageView) view.findViewById(R.id.image_view_picture)).setImageResource(time.getCoverRecourceID());
            ((TextView) view.findViewById(R.id.text_view_time)).setText(time.getName());
            ((TextView) view.findViewById(R.id.text_view_text)).setText(time.getText());
            return view;
        }
    }
}
