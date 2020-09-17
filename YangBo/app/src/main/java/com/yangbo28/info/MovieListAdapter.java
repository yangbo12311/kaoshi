package com.yangbo28.info;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;

public class MovieListAdapter extends BaseAdapter {
    ImageOptions.Builder builder;
    private ArrayList<MovieInfo> data = new ArrayList<MovieInfo>();
    private LayoutInflater mLayoutInflater;
    private Activity mContext;
    public MovieListAdapter(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
        mContext = (Activity) context;
        builder = new ImageOptions.Builder();
        builder.setUseMemCache(true);
        builder.setPlaceholderScaleType(ImageView.ScaleType.CENTER_CROP);
        builder.setImageScaleType(ImageView.ScaleType.CENTER_CROP);
        builder.setLoadingDrawableId(R.mipmap.default_item_icon);
        builder.setFailureDrawableId(R.mipmap.default_item_icon);
        builder.setFadeIn(true);
    }

    public void addList(ArrayList<MovieInfo> list)
    {
        this.data.addAll(list);
    }

    public ArrayList<MovieInfo> getList()
    {
        return data;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public MovieInfo getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null || convertView.getTag() == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_movie_list, null);
            viewHolder = new ViewHolder();
            viewHolder.thumbPath = (ImageView) convertView.findViewById(R.id.thumbPath);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.profile = (TextView) convertView.findViewById(R.id.profile);
            viewHolder.bottom = (LinearLayout) convertView.findViewById(R.id.bottom);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final MovieInfo item = getItem(position);
        viewHolder.title.setText(item.getTitle());
        viewHolder.profile.setText(item.getProfile());
        x.image().bind(viewHolder.thumbPath,item.getThumbPath(), builder.build());
        viewHolder.bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext,WebDetailActivity.class);
                intent.putExtra("url",item.getFilePath());
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    static class ViewHolder {
        ImageView thumbPath;
        TextView title;
        TextView profile;
        LinearLayout bottom;
    }
}