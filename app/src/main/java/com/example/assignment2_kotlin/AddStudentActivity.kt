package com.example.assignment2_kotlin

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment2_kotlin.model.Model
import com.example.assignment2_kotlin.model.Student


class AddStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val saveButton: Button = findViewById(R.id.add_student_activity_save_button)
        val cancelButton: Button = findViewById(R.id.add_student_activity_cancel_button)
        val nameEditText: EditText = findViewById(R.id.add_student_activity_name_edit_text)
        val idEditText: EditText = findViewById(R.id.add_student_activity_id_edit_text)
        val savedMessageTextView : TextView = findViewById(R.id.add_student_activity_save_message_text_view)

        cancelButton.setOnClickListener{
            finish()
        }

        saveButton.setOnClickListener{
            val name = nameEditText.text.toString().trim()
            val id = idEditText.text.toString().trim()

            if (name.isNotBlank() && id.isNotBlank()) {
                val newStudent = Student(name = name, id = id, avatarUrl = "", isChecked = false)
                Model.shared.addStudent(newStudent) // שמירה במודל
                savedMessageTextView.text = "Student saved: $name, ID: $id"
                finish() // חוזר למסך הקודם
            } else {
                savedMessageTextView.text = "Please fill in all fields"
            }
        }

    }
}