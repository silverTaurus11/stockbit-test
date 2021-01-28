package com.project.bibit_test.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.project.bibit_test.R
import com.project.bibit_test.Utils
import com.project.bibit_test.data.source.remote.model.CoinItem
import com.project.bibit_test.databinding.CoinItemBinding

class CoinListAdapter: PagedListAdapter<CoinItem, CoinListAdapter.CoinViewHolder>(
    object : DiffUtil.ItemCallback<CoinItem>(){
        override fun areItemsTheSame(oldItem: CoinItem, newItem: CoinItem): Boolean {
            return oldItem.coinInfo?.id == newItem.coinInfo?.id
        }

        override fun areContentsTheSame(oldItem: CoinItem, newItem: CoinItem): Boolean {
            return oldItem == newItem
        }

    }) {

    class CoinViewHolder(val binding: CoinItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinViewHolder {
        val binding = CoinItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoinViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CoinViewHolder, position: Int) {
        val item = getItem(position) as CoinItem
        holder.binding.nameView.text = item.coinInfo?.name
        holder.binding.fullnameView.text = item.coinInfo?.fullName
        holder.binding.priceView.text = Utils.printDecimalFormat(item.rawInfo?.usdCurrencyItem?.price?:0.0)
        holder.binding.changePerHourView.apply {
            val value = item.rawInfo?.usdCurrencyItem?.changeHour?:0.0
            text = if(value > 0) "+${Utils.printDecimalFormat(value)}" else Utils.printDecimalFormat(value)
            when {
                value < 0 -> {
                    setTextColor(ContextCompat.getColorStateList(context, R.color.valencia))
                }
                value == 0.0 -> {
                    setTextColor(ContextCompat.getColorStateList(context, R.color.black))
                }
                else -> {
                    setTextColor(ContextCompat.getColorStateList(context, R.color.eucalyptus))
                }
            }
        }
        holder.binding.changePerHourInPercentView.apply {
            val value = item.rawInfo?.usdCurrencyItem?.changeHourPercent?:0.0
            text =  "("+if(value > 0) "+${Utils.printDecimalFormat(value)})" else Utils.printDecimalFormat(value)+")"
            when {
                value < 0.0 -> {
                    setTextColor(ContextCompat.getColorStateList(context, R.color.valencia))
                }
                value == 0.0 -> {
                    setTextColor(ContextCompat.getColorStateList(context, R.color.black))
                }
                else -> {
                    setTextColor(ContextCompat.getColorStateList(context, R.color.eucalyptus))
                }
            }
        }
    }
}