package ai.yangyang.bookofrecipes.Util;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

import ai.yangyang.bookofrecipes.R;

public class GoTopScrollView extends ScrollView implements View.OnClickListener
{
    private ImageView goTopBtn;

    private int screenHeight;

    public GoTopScrollView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public void setScrollListener(ImageView goTopBtn)
    {
        this.goTopBtn = goTopBtn;
        this.goTopBtn.setOnClickListener(this);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt)
    {
        super.onScrollChanged(l, t, oldl, oldt);
        /**
         * 滑动距离超过500px,出现向上按钮,可以做为自定义属性
         */
        if (t >= 500)
        {
            goTopBtn.setVisibility(View.VISIBLE);
        }
        else
        {
            goTopBtn.setVisibility(View.GONE);
        }
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.image_btn_to_top)
        {
            this.smoothScrollTo(0, 0);
        }
    }
}