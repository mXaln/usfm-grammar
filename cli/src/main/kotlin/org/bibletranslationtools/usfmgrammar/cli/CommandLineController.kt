package org.bibletranslationtools.usfmgrammar.cli

import org.bibletranslationtools.usfmgrammar.common.domain.UsfmParser
import java.io.File

class CommandLineController {

    fun parse(input: File, format: String, output: File?) {
        val outText = UsfmParser().parse(input, format)
        output?.writeText(outText) ?: run {
            println(outText)
        }
    }
}
