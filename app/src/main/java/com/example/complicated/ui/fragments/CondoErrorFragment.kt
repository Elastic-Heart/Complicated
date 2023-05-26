package com.example.complicated.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.complicated.R

class CondoErrorFragment : Fragment(R.layout.search_error) {

    private lateinit var callback: () -> Unit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.refreshButton).apply {
            setOnClickListener { callback() }
        }
    }

    fun registerCallback(refresh: () -> Unit) {
        callback = refresh
    }
}