package com.example.assignment2_kotlin.model

class Model private constructor() {
    val students: MutableList<Student> = ArrayList()

    companion object {
        val shared = Model()
    }

    // פונקציה להוספת סטודנט חדש
    fun addStudent(student: Student) {
        students.add(student)
    }

    // פונקציה למציאת סטודנט לפי מזהה
    fun getStudentById(id: String): Student? {
        return students.find { it.id == id }
    }

    // פונקציה לעדכון סטודנט קיים
    fun updateStudent(student: Student) {
        val index = students.indexOfFirst { it.id == student.id }
        if (index != -1) {
            students[index] = student
        }
    }

    // פונקציה למחיקת סטודנט לפי מזהה
    fun deleteStudent(id: String) {
        students.removeAll { it.id == id }
    }
}