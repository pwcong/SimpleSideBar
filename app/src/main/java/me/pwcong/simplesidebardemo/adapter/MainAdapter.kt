package me.pwcong.simplesidebardemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import me.pwcong.simplesidebardemo.R
import me.pwcong.simplesidebardemo.constant.Constant
import me.pwcong.simplesidebardemo.entity.MainData

class MainAdapter(
    var context: Context, private var mainDataList: List<MainData>,
    var onClickListener: View.OnClickListener
) :
    RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return mainDataList[position].type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewHolder: ViewHolder

        if (viewType == Constant.TYPE_HEAD) {
            viewHolder = ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_head, parent, false)
            )
        } else {
            viewHolder = ViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
            )
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.mainData = mainDataList[position]
        holder.textView.text = mainDataList[position].string

        if (holder.mainData?.type == Constant.TYPE_ITEM) {
            holder.view.setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount(): Int {
        return mainDataList.size
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = itemView.findViewById<View>(R.id.tv_simple) as TextView
        var mainData: MainData? = null
    }

    fun getLetterPosition(header: String?): Int {
        for (i in mainDataList.indices) {
            if (mainDataList[i].string == header) {
                return i
            }
        }
        return -1
    }
}
