package com.amjad.valguide.ui.agents

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.amjad.valguide.data.remote.response.AgentList
import com.amjad.valguide.databinding.FragmentAgentsBinding
import com.amjad.valguide.ui.ViewModelFactory
import com.amjad.valguide.ui.agents.detailagent.DetailAgentActivity
import com.amjad.valguide.utils.Constants.EXTRA_ID

class AgentsFragment : Fragment() {

    private var _binding: FragmentAgentsBinding? = null
    private val binding get() = _binding!!
    private lateinit var agentsViewModel: AgentsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAgentsBinding.inflate(inflater, container, false)

        binding.rvAgents.layoutManager = LinearLayoutManager(activity)

        setupViewModel()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupViewModel() {
        val factory = ViewModelFactory.getInstance(requireContext())
        agentsViewModel = ViewModelProvider(this, factory)[AgentsViewModel::class.java]
        agentsViewModel.setAgents()
        getAgents()
        showLoading(true)
    }

    private fun getAgents() {
        val adapter = AgentsListAdapter()
        binding.rvAgents.adapter = adapter
        agentsViewModel.getAgents().observe(viewLifecycleOwner) {
            adapter.submitList(it)
            showLoading(false)
        }
        adapter.setOnItemClickCallback(object : AgentsListAdapter.OnItemClickCallback {
            override fun onItemClicked(data: AgentList) {
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

    private fun showSelectedItem(data: AgentList) {
        val intent = Intent (activity, DetailAgentActivity::class.java)
        intent.putExtra(EXTRA_ID, data.uuid)
        activity?.startActivity(intent)
        Toast.makeText(activity, data.displayName + " Selected" , Toast.LENGTH_SHORT).show()
    }
}