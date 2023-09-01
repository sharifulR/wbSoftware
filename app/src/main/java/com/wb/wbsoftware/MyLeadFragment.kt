package com.wb.wbsoftware

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.wb.wbsoftware.adapter.AllLeadAdapter
import com.wb.wbsoftware.databinding.FragmentMyLeadBinding
import com.wb.wbsoftware.network.AllLeadAPI
import com.wb.wbsoftware.network.NetworkResult
import com.wb.wbsoftware.viewModels.LeadViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyLeadFragment : Fragment() {

    private var _binding: FragmentMyLeadBinding?=null
    private val binding get()=_binding!!

    private val leadViewModel by viewModels<LeadViewModel>()

    @Inject
    lateinit var leadAPI: AllLeadAPI

    private lateinit var leadAdapter: AllLeadAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentMyLeadBinding.inflate(inflater,container, false)
        leadAdapter= AllLeadAdapter()

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        leadViewModel.getAllLeads()
        bindObservers()
        binding.recyclerview.layoutManager=
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        binding.recyclerview.adapter=leadAdapter
    }

    private fun bindObservers() {
        leadViewModel.leadLiveData.observe(viewLifecycleOwner, Observer {
            //binding.progressBar.isVisible=false

            when(it){
                is NetworkResult.Success->{
                    Log.d("TAG", "bindObservers: ${it.data}")
                    val r = it.data
                    leadAdapter.submitList(it.data?.data?.data)
                }
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(),it.message.toString(), Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    //binding.progressBar.isVisible=true
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }
}