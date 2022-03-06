package com.locus.weather.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.locus.weather.R
import com.locus.weather.databinding.ItemViewBinding
import com.locus.weather.model.ForecastDayHrDataModel

class RecyclerViewAdapter(
    private val dataList: MutableList<ForecastDayHrDataModel>
) : RecyclerView.Adapter<RecyclerView.ViewHolder?>() {
    var onItemClick: ((ForecastDayHrDataModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ItemViewHolder(ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }
    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemCount(): Int {
        return dataList.size
    }




    private inner class ItemViewHolder(private val binding: ItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(dataList[adapterPosition])
            }
        }
        fun onBindView(model: ForecastDayHrDataModel) {
            binding.nameTv.text = model.detailsModel?.text
            binding.tempTv.text = binding.tempTv.context.getString(R.string.temp, model.temp_f)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ItemViewHolder){
            holder.onBindView(dataList[position])
        }
    }
}