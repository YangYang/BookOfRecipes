package ai.yangyang.bookofrecipes.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.google.gson.Gson;
import com.vondear.rxtool.view.RxToast;
import com.vondear.rxui.view.dialog.wheel.WheelScroller;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ai.yangyang.bookofrecipes.Activity.MainActivity;
import ai.yangyang.bookofrecipes.Adapter.MenusAdapter;
import ai.yangyang.bookofrecipes.Bean.LoginResBean;
import ai.yangyang.bookofrecipes.Bean.Menus;
import ai.yangyang.bookofrecipes.Bean.MenusBean;
import ai.yangyang.bookofrecipes.R;
import ai.yangyang.bookofrecipes.Util.MyParamsBuilder;

@ContentView(R.layout.fragment_main)
public class MainFragment extends BaseFragment {

    @ViewInject(R.id.slider)
    private SliderLayout mDemoSlider;

//    @ViewInject(R.id.lv_recipes_list)
//    private ListView lvRecipesList;

    private List<Menus> menusList = null;

    @ViewInject(R.id.ll_recipes_list)
    private LinearLayout llRecipesList;

    @ViewInject(R.id.scroll_view)
    private ScrollView scrollView;

    @ViewInject(R.id.image_btn_to_top)
    private ImageButton imageBtnToTop;


    @Event(R.id.image_btn_to_top)
    private void onClick(View view){
        scrollView.scrollTo(0,0);
        imageBtnToTop.setVisibility(Button.INVISIBLE);
    }


    @SuppressLint("ClickableViewAccessibility")
    private void initView(){
        HashMap<String,String> urlMaps = new HashMap<>();
        urlMaps.put("干烧大虾", "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2756680426,1440361842&fm=27&gp=0.jpg");
        urlMaps.put("特色美食", "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=1674366350,957705626&fm=27&gp=0.jpg");
        urlMaps.put("家常菜", "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1534759858118&di=fd51a84e54ee7397afbeb1be0e7683d0&imgtype=0&src=http%3A%2F%2Fimg06.tooopen.com%2Fimages%2F20170513%2Ftooopen_sy_209472836197.jpg");

        for(String name : urlMaps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getActivity());
            textSliderView
                    .description(name)//描述
                    .image(urlMaps.get(name))//image方法可以传入图片url、资源id、File
                    .setScaleType(BaseSliderView.ScaleType.Fit)//图片缩放类型
                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {
                            Toast.makeText(getActivity(),slider.getBundle().get("extra") + "",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });//图片点击
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra",name);//传入参数
            mDemoSlider.addSlider(textSliderView);//添加一个滑动页面
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);//滑动动画
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);//默认指示器样式
        mDemoSlider.setCustomIndicator((PagerIndicator) getActivity().findViewById(R.id.custom_indicator2));//自定义指示器
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());//设置图片描述显示动画
        mDemoSlider.setDuration(4000);//设置滚动时间，也是计时器时间
        mDemoSlider.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        scrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                imageBtnToTop.setVisibility(Button.VISIBLE);
                return false;
            }
        });

    }

    private void initRecipesListItem(){
        RequestParams requestParams = new MyParamsBuilder("/PrivateFood/api",false)
                .addParameter("flag","getfoodmemus")
                .builder();
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                MenusBean menusBean = new Gson().fromJson(result,MenusBean.class);
                if(menusBean.getResult().equals("1")){
                    menusList = menusBean.getData();
                    for(Menus m : menusList){
                        LayoutInflater mInflater = LayoutInflater.from(getActivity());
                        View contentView  = mInflater.inflate(R.layout.recipes_list_item,null);
                        ImageView imageView = contentView.findViewById(R.id.image_recipes_list_item);
                        TextView tvName = contentView.findViewById(R.id.text_recipes_name);
                        TextView tvPos = contentView.findViewById(R.id.text_recipes_position);
                        tvName.setText(m.getName());
                        tvPos.setText(m.getCate());
                        ImageOptions imageOptions = new ImageOptions.Builder()
//                .setSize(DensityUtil.dip2px(120), DensityUtil.dip2px(120))
//                .setRadius(DensityUtil.dip2px(5))
                                // 如果ImageView的大小不是定义为wrap_content, 不要crop.
                                .setCrop(true)
                                // 加载中或错误图片的ScaleType
                                //.setPlaceholderScaleType(ImageView.ScaleType.MATRIX)
                                .setImageScaleType(ImageView.ScaleType.CENTER_CROP)
                                //设置加载过程中的图片
                                .setLoadingDrawableId(R.drawable.ic_launcher)
                                //设置加载失败后的图片
                                .setFailureDrawableId(R.drawable.ic_launcher)
                                //设置使用缓存
                                .setUseMemCache(true)
                                //设置支持gif
                                .setIgnoreGif(false)
                                //设置显示圆形图片
                                .setCircular(false)
                                .setSquare(true)
                                .build();
                        x.image().bind(imageView, m.getImgurl(), imageOptions);
                        llRecipesList.addView(contentView);
                    }

                    LayoutInflater mInflater = LayoutInflater.from(getActivity());
                    View endView  = mInflater.inflate(R.layout.fragment_main_ending,null);
                    llRecipesList.addView(endView);


                } else {
                    RxToast.error("失败");
                }
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initRecipesListItem();
//        initListView();
    }
}
