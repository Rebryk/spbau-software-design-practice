package ru.spbau.mit.softwaredesign.commands

import ru.spbau.mit.softwaredesign.Environment
import java.io.InputStream
import java.nio.file.Paths

/**
 * Created by rebryk on 10/2/16.
 */

/**
 * Command that allows to change the current directory
 */
class CdCommand: Command {
    override fun execute(args: List<String>, env: Environment, stdin: InputStream): InputStream {
        if (args.isEmpty()) {
            env.setCurrentDirectory(System.getProperty("user.home"));
        } else {
            var dir = args[0]
            if (dir == "~" || dir == "~/") {
                dir = System.getProperty("user.home")
            }

            val file = Paths.get(env.getCurrentDirectory()).resolve(dir).toFile()
            if (!file.isDirectory) {
                System.err.println("cd: $dir: Not a directory")
            } else {
                env.setCurrentDirectory(file.absolutePath)
            }
        }

        return "".byteInputStream()
    }
}