package com.springhibernate.jdbc

import java.sql.DriverManager
import java.sql.SQLException

fun main() {
    try {
        DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/crmapp", "postgres", "admin").use { conn ->
            if (conn != null) {
                println("Connected to the database!")
            } else {
                println("Failed to make connection!")
            }
        }
    } catch (e: SQLException) {
        System.err.format("SQL State: %s\n%s", e.sqlState, e.message)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}