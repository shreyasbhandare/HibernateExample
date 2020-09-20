package com.springhibernate.entity

import javax.persistence.*

@Entity
@Table(name = "instructor_detail")
data class InstructorDetail(@Column(name = "youtube_channel") var youtubeChannel : String?,
                            @Column(name = "hobby") val hobby : String?) {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    val id : Long? = null

    // not included CascadeType.REMOVE - deleting instructor detail won't delete instructor
    @OneToOne(mappedBy = "instructorDetail", cascade = [CascadeType.DETACH,
                                                        CascadeType.MERGE,
                                                        CascadeType.PERSIST,
                                                        CascadeType.REFRESH])
    val instructor : Instructor? = null
}