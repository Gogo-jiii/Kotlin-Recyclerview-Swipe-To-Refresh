package com.example.recyclerviewswipetorefresh

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private var arrayList = ArrayList<ModelClass>()
    private var data = arrayOf("a", "b", "c", "d", "e", "f", "g", "h", "i")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myAdapter = MyAdapter(this, getData())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
        recyclerView.adapter = myAdapter
        swipeToRefresh()
    }

    private fun swipeToRefresh() {
        swipeRefreshLayout!!.setOnRefreshListener {
            swipeRefreshLayout!!.isRefreshing = false
            resetRecyclerview()
        }
    }

    private fun resetRecyclerview() {
        //do some work...
        Toast.makeText(this, "Refresh...", Toast.LENGTH_SHORT).show()
        getData().shuffle(Random(System.currentTimeMillis()))
        val myAdapter = MyAdapter(this@MainActivity, getData())
        recyclerView!!.adapter = myAdapter
    }

    private fun getData(): ArrayList<ModelClass> {
        for (i in data.indices) {
            arrayList.add(ModelClass(data[i]))
        }
        return arrayList
    }
}