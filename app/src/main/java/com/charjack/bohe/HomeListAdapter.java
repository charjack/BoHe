package com.charjack.bohe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.charjack.bohe.vo.WebPassageInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/3/25.
 */
public class HomeListAdapter extends BaseAdapter{

    private Context ctx;
    private List<WebPassageInfo.PagerPassageInfo.PassageInfo> list;
    public HomeListAdapter(Context ctx,List<WebPassageInfo.PagerPassageInfo.PassageInfo> list){
        this.ctx = ctx;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup) {
        ViewHolder vh =new ViewHolder();
        if(convertview ==null){
            convertview = LayoutInflater.from(ctx).inflate(R.layout.fm_homelist_layout,null);
            vh.time_homelist_item  = (TextView) convertview.findViewById(R.id.time_homelist_item);
            vh.image1_homelist_item = (ImageView) convertview.findViewById(R.id.image1_homelist_item);
            vh.text1_homelist_item  = (TextView) convertview.findViewById(R.id.text1_homelist_item);
            vh.islike1_homelist_item = (ImageView) convertview.findViewById(R.id.islike1_homelist_item);
            vh.zannum1_homelist_item  = (TextView) convertview.findViewById(R.id.zannum1_homelist_item);

            vh.image2_homelist_item = (ImageView) convertview.findViewById(R.id.image2_homelist_item);
            vh.text2_homelist_item  = (TextView) convertview.findViewById(R.id.text2_homelist_item);
            vh.islike2_homelist_item = (ImageView) convertview.findViewById(R.id.islike2_homelist_item);
            vh.zannum2_homelist_item  = (TextView) convertview.findViewById(R.id.zannum2_homelist_item);

            convertview.setTag(vh);
        }
        vh = (ViewHolder) convertview.getTag();
        vh.time_homelist_item.setText(String.valueOf(list.get(position).getCreated_at()));
        vh.image1_homelist_item.setImageResource(R.mipmap.demotest);
        vh.text1_homelist_item.setText(list.get(position).getTitle());
        vh.islike1_homelist_item.setImageResource(R.mipmap.ic_feed_favourite_normal);
        vh.zannum1_homelist_item.setText(String.valueOf(list.get(position).getLikes_count()));

        vh.image2_homelist_item.setImageResource(R.mipmap.demotest);
        vh.text2_homelist_item.setText("extandlistview");
        vh.islike1_homelist_item.setImageResource(R.mipmap.ic_feed_favourite_normal);
        vh.zannum2_homelist_item.setText("extandlistview");
        return convertview;
    }

    class ViewHolder{
        TextView time_homelist_item;
        ImageView image1_homelist_item;
        TextView text1_homelist_item;
        ImageView islike1_homelist_item;
        TextView zannum1_homelist_item;

        ImageView image2_homelist_item;
        TextView text2_homelist_item;
        ImageView islike2_homelist_item;
        TextView zannum2_homelist_item;
    }
}
