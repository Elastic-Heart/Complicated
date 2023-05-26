package com.example.complicated

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.commit
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.complicated.domain.AvrilSongsState
import com.example.complicated.ui.SearchViewModel
import com.example.complicated.ui.fragments.CondoErrorFragment
import com.example.complicated.ui.fragments.CondoListFragment
import com.example.complicated.ui.fragments.CondoLoadingFragment
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val searchViewModel: SearchViewModel by viewModels()

    private val condoListFragment = CondoListFragment()
    private val condoErrorFragment = CondoErrorFragment()
    private val condoLoadingFragment = CondoLoadingFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<TextInputEditText>(R.id.myTextField).also {
            it.doOnTextChanged { charSequence: CharSequence?, _: Int, _: Int, _: Int ->
                searchViewModel(charSequence)
            }
        }

        startSearchListener()
    }

    private fun startSearchListener() {
        lifecycleScope.launch {
            searchViewModel.state
                .flowWithLifecycle(lifecycle)
                .collect {
                    when(it) {
                        is AvrilSongsState.Failure -> {
                            condoErrorFragment.registerCallback(refresh = searchViewModel::refresh)
                            supportFragmentManager.commit {
                                replace(R.id.activityFragmentContainer, condoErrorFragment)
                            }
                        }
                        is AvrilSongsState.Loading -> {
                            supportFragmentManager.commit {
                                replace(R.id.activityFragmentContainer, condoLoadingFragment)
                            }
                        }
                        is AvrilSongsState.Loaded -> {
                            supportFragmentManager.commit {
                                replace(R.id.activityFragmentContainer, condoListFragment)
                            }
                            condoListFragment.setData(it.condos)
                        }
                    }
                }
        }
    }
}