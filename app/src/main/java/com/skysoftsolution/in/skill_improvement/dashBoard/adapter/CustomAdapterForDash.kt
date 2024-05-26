package com.skysoftsolution.`in`.paymentintegration.dashboard.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.skysoftsolution.`in`.paymentintegration.dashboard.entity.DashBoardModule
import com.skysoftsolution.`in`.skill_improvement.MainActivity
import com.skysoftsolution.`in`.skill_improvement.R
import com.skysoftsolution.`in`.skill_improvement.padView.HandWritingActivityMain

class CustomAdapterForDash(private val context: Context, private val dataList: DashBoardModule) : BaseAdapter() {

    override fun getCount(): Int {
        return dataList.userList.size
    }

    override fun getItem(position: Int): Any {
        return dataList.userList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        val viewHolder: ViewHolder
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.custom_layout_for_gridview, parent, false)
            viewHolder = ViewHolder()
            viewHolder.itemText = convertView.findViewById(R.id.textView3)
            viewHolder.icon = convertView.findViewById(R.id.bus)


            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
        }
        viewHolder.itemText.text = dataList.userList[position].Title
        viewHolder.icon.setImageDrawable(context.resources.getDrawable(dataList.userList[position].drawable))
        convertView?.setOnClickListener{
            if(dataList.userList[position].id.toString().equals("1")){
                context.startActivity(Intent(context, HandWritingActivityMain::class.java).apply {
                    // you can add values(if any) to pass to the next class or avoid using `.apply`
                    //putExtra("keyIdentifier", value)
                })
                Toast.makeText(context,"Handwriting",Toast.LENGTH_SHORT).show();
            }else  if(dataList.userList[position].id.toString().equals("2")){
                Toast.makeText(context,"Video Recorder",Toast.LENGTH_SHORT).show();
            }
        }
        return convertView!!
    }

    private class ViewHolder {
        lateinit var itemText: TextView
        lateinit var icon : ImageView;
    }
}
