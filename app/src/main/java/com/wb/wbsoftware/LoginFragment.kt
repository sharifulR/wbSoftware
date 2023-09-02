package com.wb.wbsoftware

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.wb.wbsoftware.databinding.FragmentLoginBinding
import com.wb.wbsoftware.models.auth.LoginRequest
import com.wb.wbsoftware.network.NetworkResult
import com.wb.wbsoftware.utils.TokenManager
import com.wb.wbsoftware.viewModels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding?=null
    private val binding get()=_binding!!

    private val authViewModel by viewModels<AuthViewModel>()

    @Inject
    lateinit var tokenManager: TokenManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentLoginBinding.inflate(inflater,container, false)
        tokenManager=TokenManager(requireContext())
        val isLogin=tokenManager.isLogin

        binding.btnSignIn.setOnClickListener {
            val validationResult= validateUserInput()
            if (validationResult.first){
                authViewModel.login(getLoginRequest())
            }
            else{
                Toast.makeText(context,validationResult.second, Toast.LENGTH_SHORT).show()
            }

        }



        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindObseres()
    }
    private fun getLoginRequest(): LoginRequest {
        val email=binding.edtEmailPhone.text.toString()
        val password=binding.edtPassword.text.toString()
        return LoginRequest(email,password)
    }

    private fun validateUserInput(): Pair<Boolean, String> {
        val loginRequest=getLoginRequest()
        return authViewModel.validateCredentials(loginRequest.email,loginRequest.password,true)
    }
    private fun bindObseres() {
        authViewModel.userRepositoryLiveData.observe(viewLifecycleOwner, Observer {

            //binding.pogss.isViibl=fals

            when(it){
                is NetworkResult.Success ->{
                    tokenManager.saveToken(it.data!!.accessToken)
                    findNavController().navigate(R.id.action_loginFragment_to_myLeadFragment)
                }
                is NetworkResult.Error -> {
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading->{
                    //binding.pogss.isViibl=tu
                }
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null
    }

}