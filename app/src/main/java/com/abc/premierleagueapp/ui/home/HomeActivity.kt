package com.abc.premierleagueapp.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.abc.premierleagueapp.R
import com.abc.premierleagueapp.core.data.Resource
import com.abc.premierleagueapp.core.ui.ClubAdapter
import com.abc.premierleagueapp.databinding.ActivityMainBinding
import com.abc.premierleagueapp.ui.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val homeViewModel: HomeViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Home"
        showClubList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.home_menu,menu!!)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.favorite_menu -> {
                val uri = Uri.parse("premierleagueapp://favorite")
                startActivity(Intent(Intent.ACTION_VIEW,uri))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun showClubList() {
        val clubAdapter = ClubAdapter()
        clubAdapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        homeViewModel.clubs.observe(this, { club ->
            if (club != null) {
                when (club) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        clubAdapter.setData(club.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        binding.rvHome.visibility = View.GONE
                        binding.tvError.visibility = View.VISIBLE
                        binding.tvError.text = club.message ?: getString(R.string.something_wrong)
                    }
                }
            }
        })

        with(binding.rvHome) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = clubAdapter
        }
    }
}