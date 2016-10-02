package ru.spbau.mit.softwaredesign.commands

import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import org.apache.commons.io.IOUtils
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import ru.spbau.mit.softwaredesign.Environment
import java.nio.file.Files
import kotlin.test.assertEquals

/**
 * Created by rebryk on 10/2/16.
 */

class TestLsCommand {
    private val ls = LsCommand()
    private val env = mock<Environment>()

    @get:Rule
    private val tmpFolder = TemporaryFolder()

    @Before
    fun createFolders() {
        whenever(env.getCurrentDirectory()).thenReturn("")

        tmpFolder.create()

        val testFolder1 = tmpFolder.newFolder("folder1")
        Files.createFile(testFolder1.toPath().resolve("file1"))
        Files.createFile(testFolder1.toPath().resolve("file2"))

        val testFolder2 = tmpFolder.newFolder("folder2")
        Files.createFile(testFolder2.toPath().resolve("file1"))
        Files.createFile(testFolder2.toPath().resolve("file2"))
    }

    @After
    fun removeFolders() {
        tmpFolder.delete()
    }

    @Test
    fun testOneDirectory() {
        val out = ls.execute(listOf(tmpFolder.root.path), env, "".byteInputStream())
        val lines = IOUtils.readLines(out, "UTF-8")

        assertEquals(listOf("folder1", "folder2"), lines)
    }

    @Test
    fun testTwoDirectories() {
        val out = ls.execute(listOf(tmpFolder.root.path + "/folder1", tmpFolder.root.path + "/folder2"), env, "".byteInputStream())
        val lines = IOUtils.readLines(out, "UTF-8")

        val correctOutput = listOf(tmpFolder.root.path + "/folder1:", "file1", "file2", "",
                tmpFolder.root.path + "/folder2:", "file1", "file2")

        assertEquals(correctOutput, lines)
    }
}