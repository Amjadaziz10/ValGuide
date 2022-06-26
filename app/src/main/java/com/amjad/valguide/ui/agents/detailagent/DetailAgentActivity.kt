package com.amjad.valguide.ui.agents.detailagent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.amjad.valguide.R
import com.amjad.valguide.databinding.ActivityDetailAgentBinding
import com.amjad.valguide.ui.ViewModelFactory
import com.amjad.valguide.utils.Constants.EXTRA_ID
import com.bumptech.glide.Glide

class DetailAgentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailAgentBinding
    private lateinit var detailAgentViewModel: DetailAgentViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailAgentBinding.inflate(layoutInflater)
        supportActionBar?.hide()

        setupViewModel()

        binding.backBtn.setOnClickListener {
            super.onBackPressed()
        }

        setContentView(binding.root)
    }

    private fun setupViewModel() {
        val factory = ViewModelFactory.getInstance(this)
        detailAgentViewModel = ViewModelProvider(this, factory)[DetailAgentViewModel::class.java]
        val agentId = intent.getStringExtra(EXTRA_ID)
        if (agentId != null) {
            detailAgentViewModel.setAgentById(agentId)
        }
        getAgent()
    }

    private fun getAgent() {
        detailAgentViewModel.getAgentById().observe(this) {
            binding.apply {
                agentDetailTv.text = it.displayName
                roleDetailTv.text = it.role.displayName
                binding.abilityTv.text = it.abilities[0].displayName
                binding.abilityDesc.text = it.abilities[0].description
                binding.biographyDesc.text = it.description
                binding.ability1.setBackgroundResource(R.drawable.button_ability_active)
                Glide.with(this@DetailAgentActivity)
                    .load(it.fullPortraitV2)
                    .into(agentDetailImg)
                Glide.with(this@DetailAgentActivity)
                    .load(it.background)
                    .into(bgDetailImg)
                Glide.with(this@DetailAgentActivity)
                    .load(it.abilities[0].displayIcon)
                    .into(ability1)
                Glide.with(this@DetailAgentActivity)
                    .load(it.abilities[1].displayIcon)
                    .into(ability2)
                Glide.with(this@DetailAgentActivity)
                    .load(it.abilities[2].displayIcon)
                    .into(ability3)
                Glide.with(this@DetailAgentActivity)
                    .load(it.abilities[3].displayIcon)
                    .into(ability4)
            }
            binding.ability1.setOnClickListener {view ->
                view.setBackgroundResource(R.drawable.button_ability_active)
                binding.ability2.setBackgroundResource(R.drawable.button_ability)
                binding.ability3.setBackgroundResource(R.drawable.button_ability)
                binding.ability4.setBackgroundResource(R.drawable.button_ability)

                binding.abilityTv.text = it.abilities[0].displayName
                binding.abilityDesc.text = it.abilities[0].description

            }
            binding.ability2.setOnClickListener {view ->
                view.setBackgroundResource(R.drawable.button_ability_active)
                binding.ability1.setBackgroundResource(R.drawable.button_ability)
                binding.ability3.setBackgroundResource(R.drawable.button_ability)
                binding.ability4.setBackgroundResource(R.drawable.button_ability)

                binding.abilityTv.text = it.abilities[1].displayName
                binding.abilityDesc.text = it.abilities[1].description

            }
            binding.ability3.setOnClickListener {view ->
                view.setBackgroundResource(R.drawable.button_ability_active)
                binding.ability2.setBackgroundResource(R.drawable.button_ability)
                binding.ability1.setBackgroundResource(R.drawable.button_ability)
                binding.ability4.setBackgroundResource(R.drawable.button_ability)

                binding.abilityTv.text = it.abilities[2].displayName
                binding.abilityDesc.text = it.abilities[2].description

            }
            binding.ability4.setOnClickListener {view ->
                view.setBackgroundResource(R.drawable.button_ability_active)
                binding.ability2.setBackgroundResource(R.drawable.button_ability)
                binding.ability3.setBackgroundResource(R.drawable.button_ability)
                binding.ability1.setBackgroundResource(R.drawable.button_ability)

                binding.abilityTv.text = it.abilities[3].displayName
                binding.abilityDesc.text = it.abilities[3].description
            }
        }
    }

}