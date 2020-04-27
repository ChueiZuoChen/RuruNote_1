package com.example.rurunote_1.viewmodel

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.rurunote_1.R
import com.example.rurunote_1.databinding.FragmentSecondBinding
import com.example.rurunote_1.db.ItemDatabase
import com.example.rurunote_1.db.ItemRepository
import kotlinx.android.synthetic.main.fragment_second.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {
    private lateinit var binding: FragmentSecondBinding
    private lateinit var viewModel: SecondFragmentViewModel
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_second,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dao = ItemDatabase.getInstance(this.requireContext()).dao
        val repository = ItemRepository(dao)
        viewModel = ViewModelProvider(this,FragmentViewModelFactory(repository)).get(SecondFragmentViewModel::class.java)
        binding.viewmodel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
        viewModel.message.observe(this, Observer {
            it.getContentIfNotHandled().let { content ->
                Toast.makeText(this.context,content, Toast.LENGTH_LONG).show()
            }
        })
        viewModel.items.observe(this, Observer {
            it.forEach { item ->
                Log.d("123",item.address)
            }
        })
        save_button.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            viewModel.saveOnclick()
        }
    }
}
