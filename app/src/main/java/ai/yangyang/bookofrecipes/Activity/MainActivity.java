package ai.yangyang.bookofrecipes.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.vondear.rxtool.RxTool;

import org.xutils.view.annotation.ContentView;

import ai.yangyang.bookofrecipes.R;
@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxTool.init(this);
    }
}
