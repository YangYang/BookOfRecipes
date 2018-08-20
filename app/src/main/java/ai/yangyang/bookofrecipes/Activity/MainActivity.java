package ai.yangyang.bookofrecipes.Activity;

import android.annotation.SuppressLint;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import ai.yangyang.bookofrecipes.Fragment.MainFragment;
import ai.yangyang.bookofrecipes.Fragment.MoreFragment;
import ai.yangyang.bookofrecipes.Fragment.TypeFragment;
import ai.yangyang.bookofrecipes.R;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @ViewInject(R.id.btn_main)
    private Button btnMain;
    @ViewInject(R.id.btn_more)
    private Button btnMore;
    @ViewInject(R.id.btn_type)
    private Button btnType;

    @ViewInject(R.id.image_main)
    private ImageView imageMain;
    @ViewInject(R.id.image_type)
    private ImageView imageType;
    @ViewInject(R.id.image_more)
    private ImageView imageMore;

    private MainFragment mainFragment;
    private MoreFragment moreFragment;
    private TypeFragment typeFragment;



    @Event({R.id.btn_main,R.id.btn_type,R.id.btn_more,R.id.image_main,R.id.image_type,R.id.image_more})
    private void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_main:
                changeMainBackground();
                break;
            case R.id.btn_type:
                changeTypeBackground();
                break;
            case R.id.btn_more:
                changeMoreBackground();
                break;
            case R.id.image_main:
                changeMainBackground();
                break;
            case R.id.image_type:
                changeTypeBackground();
                break;
            case R.id.image_more:
                changeMoreBackground();
                break;
        }
    }

    @SuppressLint("ResourceAsColor")
    private void changeMainBackground(){
        imageMain.setImageResource(R.mipmap.ic_main_press);
        imageType.setImageResource(R.mipmap.ic_type_normal);
        imageMore.setImageResource(R.mipmap.ic_more_normal);
        btnMain.setTextColor(Color.rgb(255,173,0));
        btnType.setTextColor(Color.rgb(128,128,128));
        btnMore.setTextColor(Color.rgb(128,128,128));


        FragmentManager fm = getFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.show(mainFragment);
        transaction.hide(typeFragment);
        transaction.hide(moreFragment);
        transaction.commit();

    }

    @SuppressLint("ResourceAsColor")
    private void changeTypeBackground(){
        imageMain.setImageResource(R.mipmap.ic_main_normal);
        imageType.setImageResource(R.mipmap.ic_type_press);
        imageMore.setImageResource(R.mipmap.ic_more_normal);
        btnMain.setTextColor(Color.rgb(128,128,128));
        btnType.setTextColor(Color.rgb(255,173,0));
        btnMore.setTextColor(Color.rgb(128,128,128));
        FragmentManager fm = getFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.hide(mainFragment);
        transaction.show(typeFragment);
        transaction.hide(moreFragment);
        transaction.commit();
    }

    @SuppressLint("ResourceAsColor")
    private void changeMoreBackground(){
        imageMain.setImageResource(R.mipmap.ic_main_normal);
        imageType.setImageResource(R.mipmap.ic_type_normal);
        imageMore.setImageResource(R.mipmap.ic_more_press);
        btnMain.setTextColor(Color.rgb(128,128,128));
        btnType.setTextColor(Color.rgb(128,128,128));
        btnMore.setTextColor(Color.rgb(255,173,0));
        FragmentManager fm = getFragmentManager();
        // 开启Fragment事务
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.hide(mainFragment);
        transaction.hide(typeFragment);
        transaction.show(moreFragment);
        transaction.commit();
    }

    private void initialFragment(){
        mainFragment = new MainFragment();
        typeFragment = new TypeFragment();
        moreFragment = new MoreFragment();
    }

    private void setDefaultFragment()
    {
        imageMain.setImageResource(R.mipmap.ic_main_press);
        imageType.setImageResource(R.mipmap.ic_type_normal);
        imageMore.setImageResource(R.mipmap.ic_more_normal);
        btnMain.setTextColor(Color.rgb(255,173,0));
        btnType.setTextColor(Color.rgb(128,128,128));
        btnMore.setTextColor(Color.rgb(128,128,128));

        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();


        transaction.replace(R.id.fragment_main,mainFragment);
        transaction.replace(R.id.fragment_more,moreFragment);
        transaction.replace(R.id.fragment_type,typeFragment);

        transaction.hide(moreFragment);
        transaction.hide(typeFragment);

        transaction.commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialFragment();
        setDefaultFragment();
    }
}