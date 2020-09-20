package com.springhibernate.demo

import com.springhibernate.entity.*
import org.hibernate.cfg.Configuration
import java.lang.Exception

fun main() {
    // create session factory
    val sessionFactory = Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Instructor::class.java)
            .addAnnotatedClass(InstructorDetail::class.java)
            .addAnnotatedClass(Course::class.java)
            .addAnnotatedClass(Review::class.java)
            .addAnnotatedClass(Student::class.java)
            .buildSessionFactory()

    // create session
    val session = sessionFactory.currentSession

    try{
        session.beginTransaction()

        // get course
        val tempCourse = session.get(Course::class.java, 47L)

        // get students
        val student1 = session.get(Student::class.java, 9L)

        // add student course relation
        tempCourse.addStudent(student1)

        // save
        session.save(student1)

        println(student1.courses)
        println(tempCourse.students)

        session.transaction.commit()

    }catch (exception : Exception) {
        exception.printStackTrace()
    }finally {
        session.close()
        sessionFactory.close()
    }
}