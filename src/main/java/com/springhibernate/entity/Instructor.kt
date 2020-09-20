package com.springhibernate.entity

import javax.persistence.*

@Entity
@Table(name = "instructor")
data class Instructor(@Column(name = "first_name") var firstName : String?,
                      @Column(name = "last_name") val lastName : String?,
                      @Column(name = "email") val email : String?) {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    val id : Long? = null

    @OneToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "instructor_detail_id")
    var instructorDetail : InstructorDetail? = null

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "instructor", cascade = [CascadeType.DETACH,
                                                                           CascadeType.MERGE,
                                                                           CascadeType.PERSIST,
                                                                           CascadeType.REFRESH])
    var courses : MutableList<Course>? = null

    fun addCourse(tempCourse : Course) {
        if(courses == null){
            courses = mutableListOf()
        }
        courses?.add(tempCourse)
        tempCourse.instructor = this
    }
}