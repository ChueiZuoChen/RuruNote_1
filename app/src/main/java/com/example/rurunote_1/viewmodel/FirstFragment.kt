package com.example.rurunote_1.viewmodel

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.rurunote_1.R
import com.example.rurunote_1.adapter.RecyclerViewAdapter
import com.example.rurunote_1.databinding.FragmentFirstBinding
import com.example.rurunote_1.db.ItemDatabase
import com.example.rurunote_1.db.ItemRepository
import kotlinx.android.synthetic.main.fragment_first.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    private lateinit var adapter: RecyclerViewAdapter
    private lateinit var binding: FragmentFirstBinding
    private lateinit var viewModel: FirstFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_first, container, false)
        val dao = ItemDatabase.getInstance(this.requireContext()).dao
        val repository = ItemRepository(dao)
        viewModel = ViewModelProvider(this, FragmentViewModelFactory(repository)).get(FirstFragmentViewModel::class.java)
        binding.viewmodel = viewModel
        initialRecyclerView()
        return binding.root

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fab.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

    }

    private fun initialRecyclerView() {
        val staggeredGridLayoutManager = StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL)
        binding.recycler.layoutManager = staggeredGridLayoutManager
        binding.recycler.setHasFixedSize(true)
        adapter = RecyclerViewAdapter()
        binding.recycler.adapter = adapter
        displayList()
    }

    private fun displayList() {
        viewModel.items.observe(this.viewLifecycleOwner, Observer {
            adapter.setList(it)
            adapter.notifyDataSetChanged()
        })
    }

}
