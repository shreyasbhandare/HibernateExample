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
    var session = sessionFactory.currentSession

    try{
        // ------------ One to One uni-directional example ------------
        val instructor = Instructor("Johnny", "Lawrence", "jlawrence@ck.com")
        val instructorDetail = InstructorDetail("www.youtube.com/cobrakai", "karate")
        instructor.instructorDetail = instructorDetail

        session.beginTransaction()
        session.save(instructor)
        session.transaction.commit()

        // ------------ One to One Bi-directional example ------------
        session = sessionFactory.currentSession
        session.beginTransaction()
        val instructorDetailGet = session.get(InstructorDetail::class.java, 44L)

        println(instructorDetailGet)
        println(instructorDetailGet.instructor)

        session.transaction.commit()

        // ------------ One to One Bi-directional Delete example ------------
        session = sessionFactory.currentSession
        session.beginTransaction()
        val instructorDetailDel = session.get(InstructorDetail::class.java, 44L)

        println(instructorDetailDel)
        println(instructorDetailDel.instructor)
        println("deleting..")

        //important - breaking bi-directional link
        instructorDetailDel.instructor?.instructorDetail = null

        // delete InstructorDetail without deleting Instructor
        session.delete(instructorDetailDel)
        session.transaction.commit()

        println("Done!")

    }catch (exception : Exception) {
        exception.printStackTrace()
    }finally {
        session.close()
        sessionFactory.close()
    }
}