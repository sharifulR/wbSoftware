package com.wb.wbsoftware.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.wb.wbsoftware.R
import com.wb.wbsoftware.databinding.ItemLeadsListBinding
import com.wb.wbsoftware.models.leads.AllLeadResponse
import com.wb.wbsoftware.utils.Constants

class AllLeadAdapter(): ListAdapter<AllLeadResponse.Data.Data, AllLeadAdapter.LeadsViewHolder>(ComparatorDiffUtil()) {
    class LeadsViewHolder(private val binding: ItemLeadsListBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(allLead: AllLeadResponse.Data.Data){
            binding.tvProjectName.text=allLead.organization
            Log.d("TAG", "bind: ${allLead.organization}")
            //binding.tvShopName.text=allLead.organization
            //binding.serialNoId.text = (position+1).toString()
            binding.tvShopName.text = allLead.organization
            binding.tvProjectName.text = allLead.projectData!!.projectName

           /* val date = CommonMethods.dateFormatter(allLead.createdAt!!)
            binding.dateId.text = date
            val image = allLead.image
//            if (image == null) {
//                binding.image.background =
//                    context.resources.getDrawable(R.drawable.ic_shop_image)
//            }else{

            Glide.with(context)
                .load(Constants.BASE_URL + "uploads/organization_image/$image")
                .diskCacheStrategy(DiskCacheStrategy.ALL)

                .apply(
                    RequestOptions()
                        .error(R.drawable.ic_shop_image)
                )
                .into(binding.image)*/
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeadsViewHolder {
        val binding= ItemLeadsListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return LeadsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LeadsViewHolder, position: Int) {
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