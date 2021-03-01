package com.ixs.testing.hilt

import android.content.Context
import androidx.navigation.fragment.NavHostFragment
import com.ixs.testing.hilt.VIewModel.MainFragmentFactory

import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CustomNavigation :NavHostFragment(){
    @Inject
    lateinit var mainfragmentfactoryFactory: MainFragmentFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        childFragmentManager.fragmentFactory = mainfragmentfactoryFactory
    }
}