package ai.yangyang.bookofrecipes.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.common.util.DensityUtil;
import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.List;

import ai.yangyang.bookofrecipes.Bean.Menus;
import ai.yangyang.bookofrecipes.R;

public class MenusAdapter extends BaseAdapter{
    private Context mcontext;
    private List<Menus> menusList;

    public MenusAdapter(Context mcontext, List<Menus> menusList) {
        this.mcontext = mcontext;
        this.menusList = menusList;
    }

    @Override
    public int getCount() {
        return menusList.size();
    }

    @Override
    public Object getItem(int i) {
        return menusList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if(view == null){
            holder = new ViewHolder();
            view = LayoutInflater.from(mcontext).inflate(R.layout.list_view_recipes_list_item,null,false);
            holder.imageView = view.findViewById(R.id.image_recipes_list_item);
            holder.tvRecipesName = view.findViewById(R.id.text_recipes_name);
            holder.tvRecipesPos = view.findViewById(R.id.text_recipes_position);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

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

        x.image().bind(holder.imageView, menusList.get(i).getImgurl(), imageOptions);
//        holder.imageView.setImageResource(menusList.get(i).getImageUrl());
        holder.tvRecipesName.setText(menusList.get(i).getName());
        holder.tvRecipesPos.setText(menusList.get(i).getCate());
        return view;
    }

    class ViewHolder{
        ImageView imageView;
        TextView tvRecipesName;
        TextView tvRecipesPos;
    }
}
