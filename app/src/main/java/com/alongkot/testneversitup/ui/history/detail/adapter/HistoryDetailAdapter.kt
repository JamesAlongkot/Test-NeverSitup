package com.alongkot.testneversitup.ui.history.detail.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alongkot.testneversitup.databinding.ItemHistoryBinding
import com.alongkot.testneversitup.global.Func
import com.alongkot.testneversitup.room.entity.Currency

class HistoryDetailAdapter(private var list: ArrayList<Currency>
) : RecyclerView.Adapter<Holder>() {
    private lateinit var listener: OnItemItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding: ItemHistoryBinding = ItemHistoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.setItem(list[position],position)
    }

    override fun getItemCount() = list.size

    fun onItemItemClickListener(listener: OnItemItemClickListener) {
        this.listener = listener
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(it: List<Currency>) {
        list = it as ArrayList<Currency>
        notifyDataSetChanged()
    }

    interface OnItemItemClickListener {
        fun onClick(key: Currency)
    }
}

class Holder(v: ItemHistoryBinding, listener: HistoryDetailAdapter.OnItemItemClickListener? = null) : RecyclerView.ViewHolder(v.root) {
    var context: Context
    var b: ItemHistoryBinding
    var listener: HistoryDetailAdapter.OnItemItemClickListener? = null

    init {
        context = v.root.context
        b = v

        if (listener != null) {
            this.listener = listener
        }
    }

    @SuppressLint("UseCompatTextViewDrawableApis")
    fun setItem(
        data: Currency,
        position: Int
    ) {
        b.root.setOnClickListener {
            listener?.onClick(data)
        }
        b.setTitle(Func().convertLongToDate(data.update_timestamp,"dd-MM-yyyy HH:mm"))
        b.setValue(data.rate)

    }
}