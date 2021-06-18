package com.example.demoapi.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.demoapi.R;
import com.example.demoapi.model.MonthLoan;

import java.util.ArrayList;

public class AdapterListViewLoan extends BaseAdapter {
    ArrayList<MonthLoan> arrayList;

    public AdapterListViewLoan(ArrayList<MonthLoan> arrayList) {
        this.arrayList = arrayList;
    }
    public class ListViewItem{
        TextView ctmonth,ctmoney;
        ImageView img;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListViewItem listViewItem = null;
        if(convertView == null){
            convertView = View.inflate(parent.getContext(),R.layout.custome_listviewloan,null);
            listViewItem = new ListViewItem();
            listViewItem.ctmoney = convertView.findViewById(R.id.contenttotalLoan);
            listViewItem.ctmonth = convertView.findViewById(R.id.contentMonth);
            listViewItem.img = convertView.findViewById(R.id.img);
            convertView.setTag(listViewItem);
        }
        else {
            listViewItem = (ListViewItem) convertView.getTag();
        }
        MonthLoan m = arrayList.get(position);
        listViewItem.ctmoney.setText(m.getAmmount()+"");
        listViewItem.ctmonth.setText(m.getMonth()+"");
        if(m.getStatus()==0){
            listViewItem.img.setBackgroundResource(R.drawable.ic_action_x);
        }
        else {
            listViewItem.img.setBackgroundResource(R.drawable.ic_action_checked);
        }
        return convertView;
    }
}
