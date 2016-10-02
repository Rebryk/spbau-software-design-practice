package ru.spbau.mit.softwaredesign.commands

import ru.spbau.mit.softwaredesign.Environment
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Collectors

/**
 * Created by rebryk on 10/2/16.
 */

/**
 * Command that shows files which are located in given directories
 * If there is no arguments, command will show files located in the current directory
 */
class LsCommand : Command {
    override fun execute(args: List<String>, env: Environment, stdin: InputStream): InputStream {
        val dirs = if (args.isNotEmpty()) args else listOf(".")
        return dirs
                .map { if (it == "~" || it == "~/") System.getProperty("user.home") else it }
                .map {
                    try {
                        Files.walk(Paths.get(env.getCurrentDirectory()).resolve(it), 1)
                                .map { it.fileName.toString() }
                                .skip(1)
                                .sorted()
                                .filter { it != "." }
                                .collect(Collectors.joining("\n", if (dirs.size > 1) "$it:\n" else "", ""))
                    } catch (e: NoSuchFileException) {
                        System.err.println("ls: $it: No such file or directory")
                        ""
                    } catch (e: Exception) {
                        System.err.println("ls: $it: Couldn't open file or directory")
                        ""
                    }
                }.filter { it.isNotBlank() }
                .joinToString("\n\n", "", "\n")
                .byteInputStream()
    }
}