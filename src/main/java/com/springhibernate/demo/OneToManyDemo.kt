package com.springhibernate.demo

import com.springhibernate.entity.*
import org.hibernate.cfg.Configuration
import java.lang.Exception

fun main() {
    // create session factory
    val sessionFactory = Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Student::class.java)
            .addAnnotatedClass(Instructor::class.java)
            .addAnnotatedClass(InstructorDetail::class.java)
            .addAnnotatedClass(Course::class.java)
            .addAnnotatedClass(Review::class.java)
            .buildSessionFactory()

    // create session
    val session = sessionFactory.currentSession

    try{
        /*
        val instructor = Instructor("Ted", "Mosbey", "tmosbey@himyn.com")

        val instructorDetail = InstructorDetail("www.youtube.com/dougb", "cricket")

        instructor.instructorDetail = instructorDetail


        session.beginTransaction()

        session.save(instructor)

        session.transaction.commit()

        session.beginTransaction()
        */
        session.beginTransaction()

        val instructor = session.get(Instructor::class.java, 45L)

        val course1 = Course("Cobra Kai Dojo")
        val course2 = Course("Biker Gang 101")

        instructor.addCourse(course1)
        instructor.addCourse(course2)

        session.save(course1)
        session.save(course2)

        session.transaction.commit()

    }catch (exception : Exception) {
        exception.printStackTrace()
    }finally {
        session.close()
        sessionFactory.close()
    }
}