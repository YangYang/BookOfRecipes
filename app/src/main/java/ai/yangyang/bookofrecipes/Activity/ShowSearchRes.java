package ai.yangyang.bookofrecipes.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.vondear.rxtool.view.RxToast;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import ai.yangyang.bookofrecipes.Bean.SearchBean;
import ai.yangyang.bookofrecipes.R;

@ContentView(R.layout.activity_show_search_res)
public class ShowSearchRes extends BaseActivity{

    @ViewInject(R.id.search_res_name)
    private TextView tvSearchResName;
    @ViewInject(R.id.search_res_image)
    private ImageView imageSearchResName;
    @ViewInject(R.id.search_res_detail)
    private TextView tvSearchResDetail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        SearchBean searchBean = (SearchBean) intent.getSerializableExtra("res");
        tvSearchResName.setText(searchBean.getData().get(0));
        tvSearchResDetail.setText(searchBean.getData().get(0));
        imageSearchResName.setImageResource(R.mipmap.popular_recipes);

    }
}
