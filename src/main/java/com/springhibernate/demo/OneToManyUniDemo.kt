package com.springhibernate.demo

import com.springhibernate.entity.Course
import com.springhibernate.entity.Instructor
import com.springhibernate.entity.InstructorDetail
import com.springhibernate.entity.Review
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
            .buildSessionFactory()

    // create session
    val session = sessionFactory.currentSession

    try{
        session.beginTransaction()
        // create course
        val tempCourse = session.get(Course::class.java, 47L)

        //create reviews
        val review1 = Review("This is a good course!")
        val review2 = Review("Instructor is rude")

        // add reviews to course
        tempCourse.addReview(review1)
        tempCourse.addReview(review2)

        session.save(tempCourse)

        session.transaction.commit()

    }catch (exception : Exception) {
        exception.printStackTrace()
    }finally {
        session.close()
        sessionFactory.close()
    }
}