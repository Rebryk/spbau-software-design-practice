package ru.spbau.mit.softwaredesign.commands

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import ru.spbau.mit.softwaredesign.EnvironmentImpl
import kotlin.test.assertEquals

/**
 * Created by rebryk on 10/3/16.
 */

class TestCdCommand {
    private val cd = CdCommand()

    @get:Rule
    private val tmpFolder = TemporaryFolder()

    @Before
    fun createFolders() {
        tmpFolder.create()
    }

    @After
    fun removeFolders() {
        tmpFolder.delete()
    }

    @Test
    fun testCd() {
        val env = EnvironmentImpl()
        cd.execute(listOf(tmpFolder.root.path), env, "".byteInputStream())
        assertEquals(tmpFolder.root.path, env.getCurrentDirectory())
    }
}