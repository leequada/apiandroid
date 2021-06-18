package com.example.demoapi.Adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.demoapi.R;
import com.example.demoapi.model.Loan;

import java.util.ArrayList;

public class AdapterListview extends BaseAdapter {
    public AdapterListview(ArrayList<Loan> arraylist) {
        this.arraylist = arraylist;
    }

    ArrayList<Loan> arraylist;
    public class ListViewItem{
        TextView titleLoan,titleremain,titlestart,titleend,rate;
    }
    @Override
    public int getCount() {
        return arraylist.size();
    }

    @Override
    public Object getItem(int position) {
        return arraylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ListViewItem listViewItem = null;
        if(convertView == null){
            convertView = View.inflate(parent.getContext(), R.layout.custome_listview,null);
            listViewItem = new ListViewItem();
            listViewItem.titleLoan = convertView.findViewById(R.id.contentLoan);
            listViewItem.titleremain = convertView.findViewById(R.id.contentRemainLoan);
            listViewItem.titlestart = convertView.findViewById(R.id.contentstartDate);
            listViewItem.titleend = convertView.findViewById(R.id.contentendDate);
            listViewItem.rate = convertView.findViewById(R.id.contentrate);
            convertView.setTag(listViewItem);
        }
        else {
            listViewItem = (ListViewItem) convertView.getTag();
        }
        Loan loan = arraylist.get(position);
        listViewItem.titleLoan.setText(loan.getTotalLoan()+"");
        listViewItem.titleremain.setText(loan.getRemainLoan()+"");
        listViewItem.titlestart.setText(loan.getStartDate()+"");
        listViewItem.titleend.setText(loan.getEndDate()+"");
        listViewItem.rate.setText(loan.getBankRate()+"");
        return convertView;
    }
}
