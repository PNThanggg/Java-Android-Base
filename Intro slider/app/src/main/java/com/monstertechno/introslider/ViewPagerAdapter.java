package com.monstertechno.introslider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.Objects;

public class ViewPagerAdapter extends PagerAdapter {

    Context context;
    int[] images;
    String[] header,description;
    LayoutInflater layoutInflater;

    public ViewPagerAdapter(Context context, int[] images, String[] header, String[] description) {
        this.context = context;
        this.images = images;
        this.header = header;
        this.description = description;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View itemView = layoutInflater.inflate(R.layout.item, container, false);
        ImageView imageView = itemView.findViewById(R.id.slider_image);
        TextView header_title = itemView.findViewById(R.id.header_title),
                description_text = itemView.findViewById(R.id.description);
        header_title.setText(header[position]);
        description_text.setText(description[position]);
        imageView.setImageResource(images[position]);
        Objects.requireNonNull(container).addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout) object);
    }
}
