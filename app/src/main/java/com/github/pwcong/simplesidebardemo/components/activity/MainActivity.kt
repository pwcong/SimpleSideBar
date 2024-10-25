package com.github.pwcong.simplesidebardemo.components.activity

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.google.android.material.snackbar.Snackbar

import com.github.pwcong.simplesidebar.view.SimpleSideBar
import com.github.pwcong.simplesidebar.view.SimpleSideBar.OnLetterTouchedChangeListener
import com.github.pwcong.simplesidebardemo.R
import com.github.pwcong.simplesidebardemo.adapter.MainAdapter
import github.pwcong.simplesidebardemo.constant.Constant
import com.github.pwcong.simplesidebardemo.entity.MainData


class MainActivity : AppCompatActivity() {
    var linearLayout: LinearLayout? = null
    var sideBar: SimpleSideBar? = null
    var recyclerView: RecyclerView? = null

    var mainDataList: MutableList<MainData>? = null
    var mainAdapter: MainAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        initVariable()

        linearLayout = findViewById<View>(R.id.root) as LinearLayout

        recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        recyclerView!!.layoutManager = LinearLayoutManager(this@MainActivity)

        mainAdapter = MainAdapter(this@MainActivity, mainDataList!!) {
            val textView = findViewById<View>(R.id.tv_simple) as TextView
            Snackbar.make(linearLayout!!, textView.text, Snackbar.LENGTH_SHORT).show()
        }

        recyclerView!!.setAdapter(mainAdapter)

        sideBar = findViewById<View>(R.id.sidebar) as SimpleSideBar
        sideBar!!.setOnLetterTouchedChangeListener(object : OnLetterTouchedChangeListener {
            override fun onTouchedLetterChange(letterTouched: String?) {
                val pos: Int = mainAdapter!!.getLetterPosition(letterTouched)
                if (pos != -1) {
                    recyclerView!!.scrollToPosition(pos)
                }
            }
        })
    }

    private fun initVariable() {
        mainDataList = mutableListOf()

        for (i in 0..25) {
            mainDataList!!.add(
                MainData(
                    Constant.TYPE_HEAD,
                    (i + 65).toChar().toString() + ""
                )
            )
            for (j in 0..4) {
                mainDataList!!.add(
                    MainData(
                        Constant.TYPE_ITEM,
                        (i + 65).toChar().toString() + " Hello World " + j
                    )
                )
            }
        }
    }
}
