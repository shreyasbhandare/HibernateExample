package com.springhibernate.entity

import javax.persistence.*

@Entity
@Table(name = "course")
data class Course(@Column(name = "title") var title : String?) {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    val id : Long? = null

    @ManyToOne(cascade = [CascadeType.REFRESH,
                          CascadeType.PERSIST,
                          CascadeType.MERGE,
                          CascadeType.DETACH])
    @JoinColumn(name = "instructor_id")
    var instructor : Instructor? = null

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL])
    @JoinColumn(name = "course_id")
    var reviews : MutableList<Review>? = null

    fun addReview(review : Review) {
        if(reviews == null)
            reviews = mutableListOf()
        reviews?.add(review)
    }

    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.REFRESH,
                                                    CascadeType.PERSIST,
                                                    CascadeType.MERGE,
                                                    CascadeType.DETACH])
    @JoinTable(
            name = "course_student",
            joinColumns = [JoinColumn(name = "course_id")],
            inverseJoinColumns = [JoinColumn(name = "student_id")]
    )
    var students : MutableList<Student>? = null

    fun addStudent(student : Student){
        if(students == null)
            students = mutableListOf()
        students?.add(student)
    }
}