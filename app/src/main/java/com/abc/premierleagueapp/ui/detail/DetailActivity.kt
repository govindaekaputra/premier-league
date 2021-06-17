package com.abc.premierleagueapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.abc.premierleagueapp.R
import com.abc.premierleagueapp.core.domain.model.Club
import com.abc.premierleagueapp.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import org.koin.android.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_DATA = "extra_data"
    }
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailClub = intent.getParcelableExtra<Club>(EXTRA_DATA)
        showDetailClub(detailClub)
    }

    private fun showDetailClub(detailClub: Club?) {
        detailClub?.let {
            supportActionBar?.title = it.name
            binding.tvName.text = it.name
            binding.tvStadium.text = it.stadium
            binding.tvDesc.text = it.desc
            Glide.with(this@DetailActivity)
                .load(it.img)
                .into(binding.imgClub)

            var statusFavorite = it.isFavorited
            setStatusFavorite(statusFavorite)
            binding.fabFavorite.setOnClickListener {
                statusFavorite = !statusFavorite
                detailViewModel.setFavoriteClub(detailClub, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite))
        } else {
            binding.fabFavorite.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_baseline_favorite_border))
        }
    }
}