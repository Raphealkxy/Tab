package com.example.timmy.tab;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends Activity implements View.OnClickListener {

    public String[] shezhi=new String[]{ "朋友圈","设置","个人信息","清除缓存","关于软件"};
    public int[] imageIds={R.drawable.shezhi1,R.drawable.gerenxinxi1};
    private  String[] items=new String[]{
           "你的身份是xxx",
            "您的学校是xxx",
            "您的账号是xxx",
            "您的性别是xxx",
            "您的学院是xxx"
    };
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private List<View> list=new ArrayList<View>();

//    private ListView listView;



    private LinearLayout linearLayout_weixin;
    private LinearLayout linearLayout_frd;
    private LinearLayout linearLayout_address;
    private LinearLayout linearLayout_setting;

    private ImageButton imageButton_weixin;
    private ImageButton imageButton_frd;
    private ImageButton imageButton_address;
    private ImageButton imageButton_setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        initView();
        initEvents();
    }

    private void initEvents() {
         linearLayout_setting.setOnClickListener(this);
        linearLayout_weixin.setOnClickListener(this);
        linearLayout_address.setOnClickListener(this);
        linearLayout_frd.setOnClickListener(this);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

            @Override
            public void onPageSelected(int position) {
                int currentItem = viewPager.getCurrentItem();
                ImageReset();
                switch (currentItem)
                {
                    case 0:
                        imageButton_weixin.setImageResource(R.drawable.tab_weixin_pressed);
                        break;
                    case 1:
                        imageButton_frd.setImageResource(R.drawable.tab_find_frd_pressed);
                        break;
                    case 2:
                        imageButton_address
                                .setImageResource(R.drawable.tab_address_pressed);
                        break;
                    case 3:
                        imageButton_setting
                                .setImageResource(R.drawable.tab_settings_pressed);
                        break;
                    default:
                        break;

                }



            }
        });

    }

    private void initView() {
        viewPager= (ViewPager) findViewById(R.id.id_viewpager);
        linearLayout_weixin= (LinearLayout) findViewById(R.id.id_tab_weixin);
        linearLayout_frd= (LinearLayout) findViewById(R.id.id_tab_frd);
        linearLayout_address= (LinearLayout) findViewById(R.id.id_tab_address);
        linearLayout_setting= (LinearLayout) findViewById(R.id.id_tab_setting);

        imageButton_weixin= (ImageButton) findViewById(R.id.id_tab_weixin_img);
        imageButton_address= (ImageButton) findViewById(R.id.id_tab_address_img);
        imageButton_frd= (ImageButton) findViewById(R.id.id_tab_frd_img);
        imageButton_setting= (ImageButton) findViewById(R.id.id_tab_setting_img);
        LayoutInflater layoutInflater=LayoutInflater.from(this);
        View tab01=layoutInflater.inflate(R.layout.tab01,null);
        View tab02=layoutInflater.inflate(R.layout.tab02,null);
        View tab03=layoutInflater.inflate(R.layout.tab03,null);
        View tab04=layoutInflater.inflate(R.layout.tab04,null);

        list.add(tab01);
        list.add(tab02);
        list.add(tab03);
        list.add(tab04);

        pagerAdapter=new PagerAdapter() {

            @Override
            public int getCount() {
                return list.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                //super.destroyItem(container, position, object);
                container.removeView(list.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view =list.get(position);
                container.addView(view);
                return view;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }
        };

          viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public void onClick(View v) {
        ImageReset();
       switch (v.getId()){
           case R.id.id_tab_weixin:
               viewPager.setCurrentItem(0);
               imageButton_weixin.setImageResource(R.drawable.tab_weixin_pressed);
               break;

           case R.id.id_tab_frd:
               viewPager.setCurrentItem(1);
               imageButton_frd.setImageResource(R.drawable.tab_find_frd_pressed);
               break;

           case R.id.id_tab_address:
               viewPager.setCurrentItem(2);
               imageButton_address.setImageResource(R.drawable.tab_address_pressed);

               break;


           case R.id.id_tab_setting:
           {
               viewPager.setCurrentItem(3);
               imageButton_setting.setImageResource(R.drawable.tab_settings_pressed);
               List<Map<String,Object>> listItems=new ArrayList<Map<String, Object>>();
               for(int i=0;i<shezhi.length;i++)
               {
                   Map<String,Object> listItem=new HashMap<String,Object>();
                   listItem.put("shezhi",shezhi[i]);
                   if(i==1||i==2)
                       listItem.put("tupian",imageIds[i-1]);
                   listItems.add(listItem);
               }
               SimpleAdapter simpleAdapter=new SimpleAdapter(this,listItems,
                       R.layout.simple_item,
                       new String[] {"shezhi","tupian"},
                       new int[]{R.id.shezhi,R.id.tupian});
               ListView listView=(ListView) findViewById(R.id.mylist);
               listView.setAdapter(simpleAdapter);
               listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                   @Override
                   public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                       switch (i)
                       {
                           case 0:
                               Intent intent1=new Intent(MainActivity.this,First.class);
                               startActivity(intent1);
                               break;
                           case 1:
                               Intent intent2=new Intent(MainActivity.this,second.class);
                               startActivity(intent2);
                               break;
                           case 2:
                           {
                               AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this)
                                       .setTitle("简单列表项对话框")
                                       .setIcon(R.drawable.gerenxinxi1)
                                       .setItems(items, new DialogInterface.OnClickListener() {
                                           @Override
                                           public void onClick(DialogInterface dialog, int which) {
                                               Toast.makeText(MainActivity.this,"你选中了《"+items[which]+"》",Toast.LENGTH_SHORT).show();
                                           }
                                       });
                               setPositiveButton(builder);
                               setNegativeButton(builder)
                                       .create()
                                       .show();
                               break;
                           }
                           case 3:
                               AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this)
                                       .setTitle("简单对话框")
                                       .setIcon(R.drawable.gerenxinxi1)
                                       .setMessage("您是否清除数据");
                               setPositiveButton(builder);
                               setNegativeButton(builder)
                                       .create()
                                       .show();
                               break;
                           default:
                               Intent intent3=new Intent(MainActivity.this,Third.class);
                               startActivity(intent3);
                               break;
                       }

                   }
               });
               break;
           }
           default:
               break;

       }
    }


    private AlertDialog.Builder setPositiveButton(
            AlertDialog.Builder builder)
    {
        return builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                Toast.makeText(MainActivity.this,"点击了确定按钮",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private AlertDialog.Builder setNegativeButton(
            AlertDialog.Builder builder) {
        return builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"点击了取消按钮",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void ImageReset() {
        imageButton_address.setImageResource(R.drawable.tab_address_normal);
        imageButton_setting.setImageResource(R.drawable.tab_settings_normal);
        imageButton_frd.setImageResource(R.drawable.tab_find_frd_normal);
        imageButton_weixin.setImageResource(R.drawable.tab_weixin_normal);
    }

}
