package ru.spbau.mit.softwaredesign

import java.nio.file.Paths

/**
 * Created by Сева on 07.09.2016.
 */

class EnvironmentImpl: Environment {
    private val variables: MutableMap<String, String> = hashMapOf()
    private var currentDirectory: String = Paths.get("").toAbsolutePath().toString();

    init {
        System.getenv().forEach { variables[it.key] = it.value }
    }

    override fun setVariable(name: String, value: String) {
        variables[name] = value
    }

    override fun getVariable(name: String) = variables[name].orEmpty()

    override fun setCurrentDirectory(directory: String) {
        currentDirectory = directory
    }

    override fun getCurrentDirectory() = currentDirectory
}