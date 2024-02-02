package com.example.mydeliveryapp.sendingoption

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mydeliveryapp.R
import com.example.mydeliveryapp.networkdelivery.Option
import org.w3c.dom.Text

class SendingOptionAdapter:RecyclerView.Adapter<SendingOptionAdapter.MyViewHolder>() {
    private var sendingList = emptyList<Option>()
    class MyViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val wayToSend:TextView = itemView.findViewById(R.id.way_of_sending)
        val howMuch:TextView = itemView.findViewById(R.id.how_much)
        val howLong :TextView = itemView.findViewById(R.id.how_long)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.pay_option_list,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return sendingList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val currentOption = sendingList[position]
        holder.wayToSend.text = currentOption.name
        holder.howLong.text = currentOption.days.toString()
        holder.howMuch.text = "${currentOption.price} ₽"
    }
    fun setData(sendList:List<Option>){
        this.sendingList = sendList
        notifyDataSetChanged()
    }
}