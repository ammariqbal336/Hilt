package com.ixs.testing.hilt.VIewModel

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.ixs.testing.hilt.ui.MainFragment
import javax.inject.Inject

class MainFragmentFactory
    @Inject
    constructor(
    private val something :String
) :FragmentFactory(){
    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
       return when(className){
            MainFragment::class.java.name ->
                MainFragment(something)
            else-> super.instantiate(classLoader, className)
        }
    }
}