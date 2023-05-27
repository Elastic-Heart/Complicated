package com.example.complicated.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.complicated.R
import com.example.complicated.data.CondoUnit
import com.example.complicated.ui.CondoAdapter
import com.example.complicated.ui.MarginItemSeparator
import com.martini.complicated.details.DetailsActivity
import okhttp3.internal.notify

class CondoListFragment : Fragment(R.layout.condo_list_recycler) {
    private var condoRecyclerView: RecyclerView? = null

    private val condoAdapter = CondoAdapter(
        onItemClicked = ::onItemClicked
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        condoRecyclerView = view.findViewById(R.id.condoListRecyclerView)

        val size = resources.displayMetrics.density * 16 //16dp ?

        condoRecyclerView?.apply {
            adapter = condoAdapter
            layoutManager = LinearLayoutManager(this@CondoListFragment.context)
            addItemDecoration(MarginItemSeparator(size.toInt()))
        }
    }

    fun setData(condos: List<CondoUnit>) {
        condoAdapter.condos = condos
        condoAdapter.notifyDataSetChanged()
    }

    private fun onItemClicked(id: Long) {
        Intent(activity, DetailsActivity::class.java).also {
            it.putExtra(DetailsActivity.songId, id)
            startActivity(it)
        }
    }
}