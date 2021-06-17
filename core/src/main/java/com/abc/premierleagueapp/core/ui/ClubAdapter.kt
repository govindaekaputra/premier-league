package com.abc.premierleagueapp.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abc.premierleagueapp.core.R
import com.abc.premierleagueapp.core.databinding.CardListBinding
import com.abc.premierleagueapp.core.domain.model.Club
import com.bumptech.glide.Glide

class ClubAdapter: RecyclerView.Adapter<ClubAdapter.ListViewHolder>() {

    private var listData = ArrayList<Club>()
    var onItemClick: ((Club) -> Unit)? = null

    fun setData(newListData: List<Club>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_list, parent, false))

    override fun getItemCount() = listData.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CardListBinding.bind(itemView)
        fun bind(data: Club) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.img)
                    .into(imgClub)
                tvName.text = data.name
                tvStadium.text = data.stadium
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}