package com.example.assignment2_kotlin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment2_kotlin.model.Model
import com.example.assignment2_kotlin.model.Student
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StudentsViewActivity : AppCompatActivity() {

    private var students: MutableList<Student>? = null
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_students_view)

        // הגדרת ה-Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Students List"

        // אתחול רשימת הסטודנטים
        students = Model.shared.students

        // אתחול RecyclerView
        recyclerView = findViewById(R.id.students_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = StudentsViewAdapter(students)

        // חיבור כפתור ה-FAB למסך הוספת סטודנט
        val addStudentFab: FloatingActionButton = findViewById(R.id.add_student_fab)
        addStudentFab.setOnClickListener {
            val intent = Intent(this, AddStudentActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // רענון הרשימה כאשר חוזרים למסך
        recyclerView.adapter?.notifyDataSetChanged()
    }

}

class StudentsViewAdapter(private val students: List<Student>?) : RecyclerView.Adapter<StudentsViewAdapter.StudentViewHolder>() {

    override fun getItemCount(): Int = students?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflation = LayoutInflater.from(parent.context)
        val view = inflation.inflate(R.layout.student_list_row, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = students?.get(position)
        holder.bind(student)

        // מאזין ללחיצה על שורה
        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, StudentDetailsActivity::class.java)
            intent.putExtra("STUDENT_ID", student?.id)
            context.startActivity(intent)
        }
    }

    class StudentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.student_row_name_text_view)
        private val idTextView: TextView = itemView.findViewById(R.id.student_row_id_text_view)
        private val checkBox: CheckBox = itemView.findViewById(R.id.student_row_check_box)

        fun bind(student: Student?) {
            nameTextView.text = student?.name
            idTextView.text = student?.id
            checkBox.isChecked = student?.isChecked ?: false

            // עדכון מצב תיבת סימון
            checkBox.setOnCheckedChangeListener { _, isChecked ->
                student?.isChecked = isChecked
            }
        }
    }


}

//done