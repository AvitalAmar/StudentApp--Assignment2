package com.example.assignment2_kotlin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment2_kotlin.model.Model
import com.example.assignment2_kotlin.model.Student

class StudentDetailsActivity : AppCompatActivity() {

    private lateinit var student: Student
    private lateinit var nameTextView: TextView
    private lateinit var idTextView: TextView
    private lateinit var avatarImageView: ImageView
    private lateinit var checkBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_student_details)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // קישור רכיבי ה-UI
        nameTextView = findViewById(R.id.details_name_text_view)
        idTextView = findViewById(R.id.details_id_text_view)
        avatarImageView = findViewById(R.id.details_avatar_image_view)
        checkBox = findViewById(R.id.details_checked_checkbox)

        // קבלת ה-ID של הסטודנט מה-Intent
        val studentId = intent.getStringExtra("STUDENT_ID")
        student = Model.shared.getStudentById(studentId!!)!!

        // עדכון פרטי הסטודנט במסך
        refreshStudentDetails()

        // הגדרת כפתור העריכה
        val editButton: Button = findViewById(R.id.details_edit_button)
        editButton.setOnClickListener {
            val intent = Intent(this, EditStudentActivity::class.java)
            intent.putExtra("STUDENT_ID", student.id)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        // רענון הפרטים של הסטודנט
        refreshStudentDetails()
    }

    private fun refreshStudentDetails() {
        nameTextView.text = student.name
        idTextView.text = student.id
        avatarImageView.setImageResource(R.drawable.avatar) // שימוש בתמונה מה-drawable
        checkBox.isChecked = student.isChecked
    }

}