package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.*

data class Item(
    val photo :Int,
    val name :String,
    val price:Int
)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val spinner = findViewById<Spinner>(R.id.spinner)
        val gridview = findViewById<GridView>(R.id.gridview)
        val listView = findViewById<ListView>(R.id.listView)
        val count = ArrayList<String>() //儲存購買數量資訊
        val item = ArrayList<Item>() //儲存水果資訊
        val priceRange = IntRange(10, 100) //建立價格範圍
        val array =
            resources.obtainTypedArray(R.array.resourceList) //從 R 類別讀取圖檔
        for(i in 0 until array.length()) {
            val photo = array.getResourceId(i,0)
            val name = "水果${i+1}"
            val price = priceRange.random()
            count.add("${i+1}個")
            item.add(Item(photo, name, price))
        }
        array.recycle()
        spinner.adapter = ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1, count)

        gridview.numColumns = 3
        gridview.adapter = MyAdapter(this, item, R.layout.adapter_vertical)
        listView.adapter = MyAdapter(this, item,
            R.layout.adapter_horizontal)
    }
}
