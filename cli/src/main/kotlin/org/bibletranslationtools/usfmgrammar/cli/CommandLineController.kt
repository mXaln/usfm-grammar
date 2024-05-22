package org.bibletranslationtools.usfmgrammar.cli

import org.bibletranslationtools.usfmgrammar.common.domain.UsfmParser
import java.io.File
import java.util.logging.Logger

class CommandLineController {
    val logger = Logger.getLogger(javaClass.name)

    fun parse(input: File, format: String, output: File?) {
        UsfmParser().parse(input, output, format)
    }
}
