package org.bibletranslationtools.usfmgrammar.cli

import picocli.CommandLine.Option
import picocli.CommandLine.Command
import java.io.File

@Command(name = "usfm grammar")
class CommandLineApp : Runnable {

    private val controller = CommandLineController()

    @Option(names = ["-i", "--input"], description = ["Input usfm file"])
    private val input: File? = null

    @Option(names = ["-f", "--format"], description = ["Output format (json, csv, tsv). (Default: json)"])
    private var format: String = "json"

    @Option(names = ["-o", "--output"], description = ["Output file. (Default: ./input.format)"])
    private val output: File? = null

    @Option(names = ["-h", "--help"], usageHelp = true, description = ["display a help"])
    private var helpRequested = false

    private fun execute() {
        if (input == null) {
            println("Please specify input usfm file.")
            return
        }

        controller.parse(input, format, output)
    }

    override fun run() {
        execute()
    }
}
