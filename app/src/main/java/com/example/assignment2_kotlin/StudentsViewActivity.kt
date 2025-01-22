package com.example.assignment2_kotlin

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class StudentsViewActivity : AppCompatActivity() {

    var Students: MutableList<Student>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_students_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recycleView : RecyclerView? = findViewById(R.id.students_recycler_view)


        class StudentsAdapter(): BaseAdapter() {
            override fun getCount(): Int {
                TODO("Not yet implemented")
            }

            override fun getItem(p0: Int): Any? {
                TODO("Not yet implemented")
            }

            override fun getItemId(p0: Int): Long {
                TODO("Not yet implemented")
            }

            override fun getView(
                p0: Int,
                p1: View?,
                p2: ViewGroup?
            ): View? {
                TODO("Not yet implemented")
            }
        }
    }



}