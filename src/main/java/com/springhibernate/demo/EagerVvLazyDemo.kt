package com.springhibernate.demo

import com.springhibernate.entity.Course
import com.springhibernate.entity.Instructor
import com.springhibernate.entity.InstructorDetail
import org.hibernate.cfg.Configuration
import java.lang.Exception

fun main() {
    // create session factory
    val sessionFactory = Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Instructor::class.java)
            .addAnnotatedClass(InstructorDetail::class.java)
            .addAnnotatedClass(Course::class.java)
            .buildSessionFactory()

    // create session
    val session = sessionFactory.currentSession

    try{
        session.beginTransaction()

        val id = 8L

        val instructorQuery = session.createQuery("select i from Instructor i " +
                "join fetch i.courses " +
                "where i.id = :instructorId", Instructor::class.java)

        instructorQuery.setParameter("instructorId", id)

        val instructor = instructorQuery.singleResult

        println("courses : ${instructor}")

        println("courses : ${instructor.courses}")

        session.transaction.commit()

        session.close()

        println("courses : ${instructor.courses}")

        println("Done!")
    }catch (exception : Exception) {
        exception.printStackTrace()
    }finally {
        session.close()
        sessionFactory.close()
    }
}