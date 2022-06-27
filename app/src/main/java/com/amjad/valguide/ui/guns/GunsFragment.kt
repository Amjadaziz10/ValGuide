package com.amjad.valguide.ui.guns

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.amjad.valguide.data.remote.response.GunList
import com.amjad.valguide.databinding.FragmentGunsBinding
import com.amjad.valguide.ui.ViewModelFactory


class GunsFragment : Fragment() {

    private var _binding: FragmentGunsBinding? = null
    private val binding get() = _binding!!
    private lateinit var gunsViewModel: GunsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGunsBinding.inflate(inflater, container, false)

        binding.rvGuns.layoutManager = LinearLayoutManager(activity)

        setupViewModel()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupViewModel() {
        val factory = ViewModelFactory.getInstance(requireContext())
        gunsViewModel = ViewModelProvider(this, factory)[GunsViewModel::class.java]
        gunsViewModel.setGuns()
        getGuns()
        showLoading(true)
    }

    private fun getGuns() {
        val adapter = GunsListAdapter()
        binding.rvGuns.adapter = adapter
        gunsViewModel.getGuns().observe(viewLifecycleOwner) {
            adapter.submitList(it)
            showLoading(false)
        }
        adapter.setOnItemClickCallback(object : GunsListAdapter.OnItemClickCallback {
            override fun onItemClicked(data: GunList) {
                showSelectedItem(data)
            }
        })
    }

    private fun showLoading(state: Boolean){
        if (state){
            binding.pBar.visibility = View.VISIBLE
        }else{
            binding.pBar.visibility = View.INVISIBLE
        }
    }

    private fun showSelectedItem(data: GunList) {
//        val intent = Intent (activity, DetailAgentActivity::class.java)
//        intent.putExtra(Constants.EXTRA_ID, data.uuid)
//        activity?.startActivity(intent)
//        Toast.makeText(activity, data.displayName + " Selected" , Toast.LENGTH_SHORT).show()
    }
}