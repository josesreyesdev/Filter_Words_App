package com.example.filterwords

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filterwords.adapter.WordAdapter
import com.example.filterwords.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    //Patron singleton = solo habrá una unica instancia de objeto complementario
    companion object {
        const val LETTER = "letter"
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //recuperando la letra desde cualquier activity
        val letterId = intent?.extras?.getString(LETTER).toString()

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = WordAdapter(this, letterId) /* cyclerView.adapter =
        intent?.extras?.getString(LETTER).toString().let { WordAdapter(this, it) } */

        recyclerView.addItemDecoration(
            DividerItemDecoration( this, DividerItemDecoration.VERTICAL)
        )

        title = getString(R.string.detail_prefix) + " " + letterId
    }
}