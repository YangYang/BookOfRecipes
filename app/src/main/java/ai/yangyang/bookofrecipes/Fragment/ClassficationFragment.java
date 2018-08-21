package ai.yangyang.bookofrecipes.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.vondear.rxtool.view.RxToast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

import ai.yangyang.bookofrecipes.Bean.AllCategoryTypeBean;
import ai.yangyang.bookofrecipes.R;
import ai.yangyang.bookofrecipes.Util.MyParamsBuilder;

@ContentView(R.layout.fragment_type_classification)
public class ClassficationFragment extends BaseFragment{

    @ViewInject(R.id.lv_all_type)
    private ListView lvAllType;

    private ArrayAdapter<String> arrayAdapter;

    private Gson gson = new Gson();

    private void initAllType(){
        //构造请求参数
        RequestParams requestParams = new MyParamsBuilder("/PrivateFood/api",false)
                .addParameter("flag","getallcategorytype")
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
                    lvAllType.setAdapter(arrayAdapter);
                    lvAllType.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            RxToast.success(view.getId() + "");
                        }
                    });
                } else {

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
        initAllType();
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
