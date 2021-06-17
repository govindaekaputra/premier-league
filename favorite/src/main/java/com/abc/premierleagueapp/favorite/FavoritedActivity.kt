package com.abc.premierleagueapp.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.abc.premierleagueapp.core.ui.ClubAdapter
import com.abc.premierleagueapp.favorite.databinding.ActivityFavoritedBinding
import com.abc.premierleagueapp.ui.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoritedActivity : AppCompatActivity() {

    private val favoritedViewModel: FavoritedViewModel by viewModel()
    private lateinit var binding: ActivityFavoritedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritedBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Favorite"
        showFavoritedClubs()
    }

    private fun showFavoritedClubs() {
        loadKoinModules(favoritedModule)
        val clubAdapter = ClubAdapter()
        clubAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoritedViewModel.favoritedClubs.observe(this, { club ->
            clubAdapter.setData(club)
            if(club.isEmpty()) binding.tvEmptyData.visibility = View.VISIBLE
        })

        with(binding.rvFavorited) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = clubAdapter
        }
    }
}