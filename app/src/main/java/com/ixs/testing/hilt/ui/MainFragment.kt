package com.ixs.testing.hilt.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ixs.testing.hilt.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment(
    private val str :String
) :Fragment(R.layout.mainfragment){


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(context , str, Toast.LENGTH_SHORT).show()
    }
}