package com.amjad.valguide.ui.maps

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.amjad.valguide.data.remote.response.MapList
import com.amjad.valguide.databinding.FragmentMapsBinding
import com.amjad.valguide.ui.ViewModelFactory

class MapsFragment : Fragment() {

    private var _binding: FragmentMapsBinding? = null
    private val binding get() = _binding!!
    private lateinit var mapsViewModel: MapsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapsBinding.inflate(inflater, container, false)

        binding.rvMaps.layoutManager = LinearLayoutManager(activity)

        setupViewModel()


        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupViewModel() {
        val factory = ViewModelFactory.getInstance(requireContext())
        mapsViewModel = ViewModelProvider(this, factory)[MapsViewModel::class.java]
        mapsViewModel.setMaps()
        getMaps()
        showLoading(true)
    }

    private fun getMaps() {
        val adapter = MapsListAdapter()
        binding.rvMaps.adapter = adapter
        mapsViewModel.getMaps().observe(viewLifecycleOwner) {
            adapter.submitList(it)
            showLoading(false)
        }
        adapter.setOnItemClickCallback(object : MapsListAdapter.OnItemClickCallback {
            override fun onItemClicked(data: MapList) {
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

    private fun showSelectedItem(data: MapList) {
//        val intent = Intent (activity, DetailAgentActivity::class.java)
//        intent.putExtra(Constants.EXTRA_ID, data.uuid)
//        activity?.startActivity(intent)
//        Toast.makeText(activity, data.displayName + " Selected" , Toast.LENGTH_SHORT).show()
    }
}