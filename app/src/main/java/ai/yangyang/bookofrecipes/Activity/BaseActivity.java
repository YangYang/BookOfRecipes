package ai.yangyang.bookofrecipes.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.vondear.rxtool.RxTool;
import com.vondear.rxui.activity.ActivityBase;

import org.xutils.x;


public class BaseActivity extends ActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        RxTool.init(this);
    }
}