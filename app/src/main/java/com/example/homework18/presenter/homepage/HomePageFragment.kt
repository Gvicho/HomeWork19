package com.example.homework18.presenter.homepage

import android.util.Log.d
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework18.R
import com.example.homework18.databinding.FragmentHomePageBinding
import com.example.homework18.presenter.adapters.CallBackListener
import com.example.homework18.presenter.adapters.UsersRecyclerViewAdapter
import com.example.homework18.presenter.common.BaseFragment
import com.example.homework18.presenter.homepage.event_state.NavigationEvent
import com.example.test6.data.common.ResultWrapper
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomePageFragment : BaseFragment<FragmentHomePageBinding>(FragmentHomePageBinding::inflate),CallBackListener {

    lateinit var myAdaper: UsersRecyclerViewAdapter
    private val viewModel: HomePageViewModel by viewModels()

    override fun setUp() {
        myAdaper = UsersRecyclerViewAdapter(this)
        ifIsLastSuccessfulLoaded()
    }

    private fun ifIsLastSuccessfulLoaded(){
        val lastList = viewModel.usersLastSuccessLoadedList
        if(lastList.isNotEmpty()){
            myAdaper.submitList(viewModel.usersLastSuccessLoadedList.toMutableList())
        }
    }

    override fun setBindings() {
        binding.apply {
            usersRecycler.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = myAdaper
                setHasFixedSize(false)
            }
            reloadBtn.setOnClickListener{
                viewModel.loadUsers()
            }
            deleteBtn.setOnClickListener{
                viewModel.deleteAllSelected()
            }
        }
    }

    override fun setObserverForEvent() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.navigationEventFlow.collect{
                    when(it){
                        is NavigationEvent.NavigateToDetailsPage -> {
                            val navController = findNavController()
                            if (navController.currentDestination?.id == R.id.homePageFragment) {
                                val action = HomePageFragmentDirections.actionHomePageFragmentToUserDetailsFragment(it.userId)

                                val navOptions = NavOptions.Builder()
                                    .build()

                                navController.navigate(action,navOptions)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun setObserverForState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.usersStateFlow.collect{

                    it?.let {
                        val result = it.users
                        when(result){
                            is ResultWrapper.Success -> {
                                result.data?.let {users ->
                                    d("tag123","submited ->${users.usersList}")
                                    myAdaper.submitList(users.usersList.toMutableList())
                                }
                                setRemoveUsersBtnVisibility(it.deleteUsersButtonIsVisible)
                            }
                            is ResultWrapper.Error -> {
                                failedLoad(result.errorMessage?:"Empty loading error")
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

    private fun setRemoveUsersBtnVisibility(visibility: Boolean){
        binding.apply {
            if(visibility){
                deleteBtn.visibility = View.VISIBLE
            }else{
                deleteBtn.visibility = View.GONE
            }
        }
    }

    private fun failedLoad(errorMessage:String){
        Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
        d("tag123",errorMessage)
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

    override fun openUsersPage(id: Int) {
        viewModel.navigateToDetailsPage(id)
    }

    override fun addUserToDeletedList(id: Int) {
        viewModel.changeUserInDeletedList(id,true)
    }

    override fun removeUserToDeletedList(id: Int) {
        viewModel.changeUserInDeletedList(id,false)
    }
}