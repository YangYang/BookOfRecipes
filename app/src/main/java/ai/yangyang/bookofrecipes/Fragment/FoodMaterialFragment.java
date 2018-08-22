package ai.yangyang.bookofrecipes.Fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.vondear.rxtool.view.RxToast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import ai.yangyang.bookofrecipes.Bean.AllCategoryTypeBean;
import ai.yangyang.bookofrecipes.Bean.CateTypeBean;
import ai.yangyang.bookofrecipes.R;
import ai.yangyang.bookofrecipes.Util.MyParamsBuilder;

@ContentView(R.layout.fragment_type_food_material)
public class FoodMaterialFragment extends BaseFragment {
    @ViewInject(R.id.lv_all_food_type)
    private ListView lvAllFoodType;

    @ViewInject(R.id.gv_food_detail_type)
    private GridView gvFoodDetailType;

    private ArrayAdapter<String> arrayAdapter;

    private ArrayAdapter<String> gridViewAdapter;

    private Gson gson = new Gson();

    private int PRE_CLICK = -1;

    private void initAllType(){
        //构造请求参数
        RequestParams requestParams = new MyParamsBuilder("/PrivateFood/api",false)
                .addParameter("flag","getallfoodtype")
                .builder();
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                AllCategoryTypeBean allCategoryTypeBean = gson.fromJson(result, AllCategoryTypeBean.class);
                if(allCategoryTypeBean.getResult().equals("1")){
                    List<String> content = allCategoryTypeBean.getData();
                    String []data = new String[content.size()];
                    for(int i = 0;i< data.length; i++){
                        data[i] = content.get(i);
                    }
                    arrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.list_view_item_all_type,data);
                    arrayAdapter.notifyDataSetChanged();
                    lvAllFoodType.setAdapter(arrayAdapter);
//                    initFirstChose();
                    lvAllFoodType.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @SuppressLint("ResourceAsColor")
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            initPreClick();
                            PRE_CLICK = i;
                            TextView tv = (TextView) view;
                            tv.setBackgroundResource(R.color.colorWhite);
                            tv.setTextColor(Color.rgb(255,173,0));
                            updateGridView(tv.getText().toString());

                        }
                    });
                    //TODO handler
//                    initPreClick();
//                    TextView tv = (TextView) lvAllType.getChildAt(0);
//                    tv.setBackgroundResource(R.color.colorWhite);
//                    tv.setTextColor(Color.rgb(255,173,0));
//                    PRE_CLICK = 0;
//                    lvAllType.performItemClick(lvAllType.getAdapter().getView(0, null, null), 0, lvAllType.getItemIdAtPosition(0));

                } else {
                    RxToast.error("网络请求失败");
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

    private void updateGridView(String foodType) {
        RequestParams requestParams = new MyParamsBuilder("/PrivateFood/api",false)
                .addParameter("flag","getfoodbytype")
                .addParameter("food_type",foodType)
                .builder();
        x.http().get(requestParams, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                CateTypeBean cateTypeBean = gson.fromJson(result, CateTypeBean.class);
                if(cateTypeBean.getResult().equals("1")){
                    List<String> content = cateTypeBean.getData();
                    String []data = new String[content.size()];
                    for(int i = 0;i< data.length; i++){
                        data[i] = content.get(i);
                    }
                    gridViewAdapter = new ArrayAdapter<String>(getActivity(),R.layout.grid_view_classification_item,data);
                    gridViewAdapter.notifyDataSetChanged();
                    gvFoodDetailType.setAdapter(gridViewAdapter);
                } else {
                    RxToast.error("网络请求失败");
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

    @SuppressLint("ResourceAsColor")
    private void initFirstChose() {
        TextView tv = (TextView) lvAllFoodType.getChildAt(0);
        tv.setBackgroundResource(R.color.colorWhite);
        tv.setTextColor(Color.rgb(255,173,0));
    }

    @SuppressLint({"ResourceAsColor"})
    private void initPreClick(){
        if(PRE_CLICK == -1){
            return ;
        } else {
            TextView tv = (TextView) lvAllFoodType.getChildAt(PRE_CLICK);
            tv.setTextColor(R.color.white);
            tv.setBackgroundColor(Color.rgb(243,243,243));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        initAllType();
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
