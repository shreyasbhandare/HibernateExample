package com.springhibernate.entity

import javax.persistence.*

@Entity
@Table(name = "review")
data class Review(@Column(name="comment") val comment : String?) {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    val id : Long? = null
}