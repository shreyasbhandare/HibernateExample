package com.springhibernate.demo

import com.springhibernate.entity.Student
import org.hibernate.cfg.Configuration

fun main() {
    // create session factory
    val sessionFactory = Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Student::class.java)
            .buildSessionFactory()

    // create session
    var session = sessionFactory.currentSession

    try{
        // ------------------ Create --------------------

        // create the Student object
        println("Creating new student object...")
        val student = Student (firstName = "Daniel", lastName = "LaRusso", email = "danl@larusso.com")

        // start a transaction
        session.beginTransaction()

        // saving the Student object
        println("Saving the student...")
        session.save(student)

        // commit transaction
        session.transaction.commit()

        println("Saved! Generated Id : ${student.id}")

        // ------------------ Read --------------------

        // get new session and start transaction
        session = sessionFactory.currentSession
        session.beginTransaction()

        // retrieve student
        val newStudent = session.find(Student::class.java, student.id)
        println("Retrieving the student: $newStudent")

        // commit transaction
        session.transaction.commit()
        println("Done!")

        // ------------------ Read with HQL --------------------

        // get new session and start transaction
        session = sessionFactory.currentSession
        session.beginTransaction()

        // query student
        val studentList = session.createQuery("from Student s where s.id > 5").resultList
        println("Retrieving the student list: $studentList")

        // commit transaction
        session.transaction.commit()
        println("Done!")

        // ------------------ Update --------------------

        session = sessionFactory.currentSession
        session.beginTransaction()

        // get student
        val updateStudent = session.get(Student::class.java, 6L)

        // update student
        updateStudent.firstName = "Matthew"

        // update student with query
        session.createQuery("update Student s set s.email = 'matthew@abcd.com' where s.id = 6").executeUpdate()

        // commit transaction
        session.transaction.commit()
        println("Done!")

        // ------------------ Delete --------------------

        session = sessionFactory.currentSession
        session.beginTransaction()

        // delete student
        println("Deleting Student..")
        session.createQuery("delete from Student s where s.id = 11").executeUpdate()

        // commit transaction
        session.transaction.commit()
        println("Done!")

    }finally {
        sessionFactory.close()
    }
}