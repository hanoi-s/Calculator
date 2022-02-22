package com.mobdeve.minors.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.minors.calculator.item.Adapter
import com.mobdeve.minors.calculator.item.Item


class History : AppCompatActivity() {
    lateinit var DBHelper : DBHelper
    private var rvHistory: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        rvHistory = findViewById(R.id.rvHistory)
        DBHelper = DBHelper(this)

        var items = DBHelper.getHistory()
        val postItems = ArrayList<Item>()

        items.forEach {
            postItems.add(
                Item(
                    it.computation,
                    it.result
                )
            )
        }

        val objAdapter = Adapter(postItems)


        rvHistory?.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        rvHistory?.adapter = objAdapter
    }
}