package com.example.assignment2_kotlin

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment2_kotlin.model.Model
import com.example.assignment2_kotlin.model.Student

class EditStudentActivity : AppCompatActivity() {

    private lateinit var student: Student

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_student)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val studentId = intent.getStringExtra("STUDENT_ID")
        student = Model.shared.getStudentById(studentId!!)!!

        val nameEditText: EditText = findViewById(R.id.edit_name_edit_text)
        val idEditText: EditText = findViewById(R.id.edit_id_edit_text)

        // אתחול עם הנתונים הקיימים
        nameEditText.setText(student.name)
        idEditText.setText(student.id)

        findViewById<Button>(R.id.edit_save_button).setOnClickListener {
            student.name = nameEditText.text.toString()
            student.id = idEditText.text.toString()
            Model.shared.updateStudent(student)
            finish()
        }

        findViewById<Button>(R.id.edit_delete_button).setOnClickListener {
            Model.shared.deleteStudent(student.id)
            finish()
        }

        findViewById<Button>(R.id.edit_cancel_button).setOnClickListener {
            finish()
        }
    }
}