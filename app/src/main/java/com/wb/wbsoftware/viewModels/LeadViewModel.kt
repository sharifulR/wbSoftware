package com.wb.wbsoftware.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wb.wbsoftware.viewModels.repositorys.AllLeadRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeadViewModel @Inject constructor(private val leadRepository: AllLeadRepository) : ViewModel() {

    val leadLiveData get()=leadRepository.leadLiveData

    fun getAllLeads(){
        viewModelScope.launch {
            leadRepository.getAllLeads()
        }
    }
}