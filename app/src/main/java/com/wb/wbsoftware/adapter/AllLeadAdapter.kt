package com.wb.wbsoftware.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.wb.wbsoftware.databinding.ItemLeadsListBinding
import com.wb.wbsoftware.models.leads.AllLeadResponse

class AllLeadAdapter(): ListAdapter<AllLeadResponse.Data.Data, AllLeadAdapter.ClassViewHolder>(ComparatorDiffUtil()) {
    class ClassViewHolder(private val binding: ItemLeadsListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(allLead: AllLeadResponse.Data.Data){
            binding.tvProjectName.text=allLead.projectData.projectName
            binding.tvShopName.text=allLead.organization
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder {
        val binding= ItemLeadsListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ClassViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        val allClass= getItem(position)
        allClass?.let {
            holder.bind(it)
        }
    }

    class ComparatorDiffUtil : DiffUtil.ItemCallback<AllLeadResponse.Data.Data>() {
        override fun areItemsTheSame(oldItem: AllLeadResponse.Data.Data, newItem: AllLeadResponse.Data.Data): Boolean {
            TODO("Not yet implemented")
        }

        override fun areContentsTheSame(oldItem: AllLeadResponse.Data.Data, newItem: AllLeadResponse.Data.Data): Boolean {
            return oldItem==newItem
        }

    }

}