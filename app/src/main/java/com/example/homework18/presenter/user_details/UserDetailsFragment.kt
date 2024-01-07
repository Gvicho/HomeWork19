package com.example.homework18.presenter.user_details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.homework18.databinding.FragmentUserDetailsBinding
import com.example.homework18.presenter.common.BaseFragment
import com.example.homework18.presenter.model_presenter.UserPresenter
import com.example.test6.data.common.ResultWrapper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserDetailsFragment : BaseFragment<FragmentUserDetailsBinding>(FragmentUserDetailsBinding::inflate) {

    private val viewModel: UserDetailsViewModel by viewModels()
    private val args: UserDetailsFragmentArgs by navArgs()

    private var id = -1

    override fun initData(savedInstanceState: Bundle?) {
        id = args.id
    }

    override fun setUp() {
        viewModel.loadUserInfo(id)
    }

    override fun setBindings() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.userFlow.collect {
                    it?.let {
                        val result = it.user

                        when(result){
                            is ResultWrapper.Success -> {
                                result.data?.let {
                                    bindUserInfo(it.data)
                                }

                            }

                            is ResultWrapper.Error -> {
                                failedLoad(result.errorMessage?:"loading error")
                            }
                            is ResultWrapper.Loading -> {
                                loading(result.loading)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun bindUserInfo(user: UserPresenter){

        binding.apply {
            Glide.with(requireContext())
                .load(user.avatar)
                .into(avatarImage)

            tvEmail.text = user.email
            tvFirstName.text = user.firstName
            tvLastName.text = user.lastName
        }
    }

    private fun failedLoad(errorMessage:String){
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun loading(isLoading:Boolean){
        lifecycleScope.launch {
            binding.apply {
                if(isLoading){
                    progressBar.visibility = View.VISIBLE
                }else{
                    progressBar.visibility = View.GONE
                }
            }
        }
    }

}

//https://reqres.in/api/users/7