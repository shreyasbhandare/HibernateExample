package com.springhibernate.entity

import javax.persistence.*

@Entity
@Table(name = "student")
data class Student(@Column(name = "first_name") var firstName : String?,
                   @Column(name = "last_name") val lastName : String?,
                   @Column(name = "email") val email : String?) {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    val id : Long? = null

    @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.REFRESH,
        CascadeType.PERSIST,
        CascadeType.MERGE,
        CascadeType.DETACH])
    @JoinTable(
            name = "course_student",
            joinColumns = [JoinColumn(name = "student_id")],
            inverseJoinColumns = [JoinColumn(name = "course_id")]
    )
    var courses : MutableList<Course>? = null
}