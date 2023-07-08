package com.example.filterwords

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filterwords.adapter.LetterAdapter
import com.example.filterwords.databinding.ActivityMainBinding

const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var isLinearLayoutManager = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate called")

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerView
        chooseLayout()
    }

    private fun chooseLayout() { //escogerLayout
        recyclerView.layoutManager =
            if (isLinearLayoutManager) LinearLayoutManager(this)
            else GridLayoutManager(this, 4)

        recyclerView.adapter = LetterAdapter()
    }

    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null)
            return

        val icon = if (isLinearLayoutManager) R.drawable.ic_grid_layout
            else R.drawable.ic_linear_layout

        menuItem.icon = ContextCompat.getDrawable(this, icon)
    }

    //Aumento el menu de opciones y realizar cualquier configuracion adicional
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.layout_menu, menu)

        val layoutButton = menu?.findItem(R.id.action_switch_layout)
        setIcon(layoutButton)

        return true
    }

    //Llama chooseLayout() cuando se seleccione el botón
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_switch_layout -> {
                isLinearLayoutManager = !isLinearLayoutManager
                chooseLayout()
                setIcon(item)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /* Examples of lifecycle */

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart called!")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy Called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart Called")
    }
}