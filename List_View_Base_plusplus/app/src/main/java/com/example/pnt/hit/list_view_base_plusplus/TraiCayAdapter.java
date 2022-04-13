package com.example.pnt.hit.list_view_base_plusplus;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;

import java.util.List;

public class TraiCayAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<TraiCay> traiCayList;

    public TraiCayAdapter() {
    }

    public TraiCayAdapter(Context context, int layout, List<TraiCay> traiCayList) {
        this.context = context;
        this.layout = layout;
        this.traiCayList = traiCayList;
    }


    // trả về số dòng hiện thị của list view
    @Override
    public int getCount() {
        return traiCayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    // tối ưu chương trình, giảm công việc cho phần ánh xạ
    private class ViewHolder{
        ImageView imgHinh;
        TextView txtTen, txtMoTa;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;

        if(view == null){ // lần đầu tiền chạy
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);

            holder = new ViewHolder();

            // ánh xạ view
            holder.txtTen = view.findViewById(R.id.ten);
            holder.txtMoTa = view.findViewById(R.id.mota);
            holder.imgHinh = view.findViewById(R.id.avatar);

            view.setTag(holder); // giữ trạng thái ánh xạ
        } else {
            holder = (ViewHolder) view.getTag();
        }

        // gán giá trị
        TraiCay traiCay = traiCayList.get(i);

        holder.txtTen.setText(traiCay.getTen());
        holder.txtMoTa.setText(traiCay.getMota());
        holder.imgHinh.setImageResource(traiCay.getHinh());

        return view;
    }
}
