package ai.yangyang.bookofrecipes.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.google.gson.Gson;
import com.vondear.rxtool.view.RxToast;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import ai.yangyang.bookofrecipes.Adapter.SearchResAdapter;
import ai.yangyang.bookofrecipes.Bean.SearchBean;
import ai.yangyang.bookofrecipes.R;
import ai.yangyang.bookofrecipes.Util.MyParamsBuilder;

@ContentView(R.layout.activity_search)
public class SearchActivity extends BaseActivity{
    @ViewInject(R.id.floating_search_view)
    private FloatingSearchView floatingSearchView;
    private SearchResAdapter searchResAdapter;
    @ViewInject(R.id.lv_search_res)
    private ListView lvSearchRes;

    private void initView(){
        floatingSearchView.setOnQueryChangeListener(new FloatingSearchView.OnQueryChangeListener() {
            @Override
            public void onSearchTextChanged(String oldQuery, String newQuery) {

            }
        });
        floatingSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {

            }

            @Override
            public void onSearchAction(String currentQuery) {
                //构造请求参数
                RequestParams requestParams = new MyParamsBuilder("/PrivateFood/api",false)
                        .addParameter("flag","searchbyname")
                        .addParameter("search_name",currentQuery)
                        .builder();
                x.http().get(requestParams, new Callback.CommonCallback<String>() {
                    @Override
                    public void onSuccess(String result) {
                        SearchBean searchBean = new Gson().fromJson(result,SearchBean.class);
                        if(searchBean.getResult().equals("0")){
//                            searchResAdapter = new SearchResAdapter(SearchActivity.this,searchBean.getData());
//                            lvSearchRes.setAdapter(searchResAdapter);
                            Intent intent = new Intent(SearchActivity.this,ShowSearchRes.class);
                            intent.putExtra("res",searchBean);
                            startActivity(intent);
                        } else {
                            RxToast.error("请求错误");
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
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }
}
