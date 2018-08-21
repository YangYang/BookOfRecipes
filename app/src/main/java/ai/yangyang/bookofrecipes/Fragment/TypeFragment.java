package ai.yangyang.bookofrecipes.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import ai.yangyang.bookofrecipes.R;

@ContentView(R.layout.fragment_type)
public class TypeFragment extends BaseFragment {

    @ViewInject(R.id.type_viewpager)
    private ViewPager viewPager;
    @ViewInject(R.id.type_tabs)
    private TabLayout tabLayout;
    @ViewInject(R.id)

    private void initView(){
        tabLayout.addTab(tabLayout.newTab().setText("分类"));
        tabLayout.addTab(tabLayout.newTab().setText("材料"));

        tabLayout.setTabTextColors(Color.rgb(128,128,128),Color.rgb(255,173,0));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
                Log.e("position",position+ " ");
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager){
        Adapter adapter = new Adapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new ClassficationFragment(),"分类");
        adapter.addFragment(new FoodMaterialFragment(),"材料");
        viewPager.setAdapter(adapter);

    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<BaseFragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitle = new ArrayList<>();
        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public BaseFragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFragment(BaseFragment fragment, String title){
            fragmentList.add(fragment);
            fragmentTitle.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitle.get(position);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();

    }
}
