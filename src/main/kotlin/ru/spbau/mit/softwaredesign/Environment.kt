package ru.spbau.mit.softwaredesign

/**
 * Created by Сева on 14.09.2016.
 */

/**
 * Interface for storing and retrieving environment variables
 * Be very accurate with using of current directory. Don't forget to use it to get correct paths.
 */

interface Environment {
    fun setVariable(name: String, value: String)
    fun getVariable(name: String): String
    fun setCurrentDirectory(directory: String)
    fun getCurrentDirectory(): String
}