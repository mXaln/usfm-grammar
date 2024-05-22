package org.bibletranslationtools.usfmgrammar.common.domain

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.bibletranslationtools.usfmgrammar.common.model.*
import org.wycliffeassociates.usfmtools.USFMParser
import org.wycliffeassociates.usfmtools.models.markers.CMarker
import org.wycliffeassociates.usfmtools.models.markers.IDMarker
import org.wycliffeassociates.usfmtools.models.markers.TextBlock
import org.wycliffeassociates.usfmtools.models.markers.VMarker
import java.io.File

class UsfmParser {

    fun parse(input: File, format: String): String {
        val parser = USFMParser()
        val parsedDoc = parser.parseFromString(input.readText())

        val (bookCode, description) = parsedDoc
            .contents
            .filterIsInstance<IDMarker>()
            .singleOrNull()
            ?.let {
                it.textIdentifier.split(" ", limit = 2).let { list ->
                    when (list.size) {
                        2 -> list
                        1 -> listOf(list.first(), "Unknown Book")
                        else -> listOf("unknown", "Unknown Book")
                    }
                }
            } ?: listOf("unknown", "Unknown Book")

        val doc = UsfmDocument(
            Book(bookCode, description),
            arrayListOf()
        )

        parsedDoc.contents.filterIsInstance<CMarker>().forEach { marker ->
            val chapter = Chapter(marker.number.toString(), arrayListOf())
            doc.addChapter(chapter)

            marker.getChildMarkers(VMarker::class.java).forEach { verseMarker ->
                val text = verseMarker.getChildMarkers(TextBlock::class.java)
                    .joinToString(" ") { it.text }

                val verse = Content.Verse(
                    verseMarker.verseNumber,
                    text
                )

                doc.addVerse(chapter, verse)
            }
        }

        val outText = formatOutput(format, doc) ?: throw IllegalArgumentException("Format is not supported.")
        return outText
    }

    private fun formatOutput(format: String, document: UsfmDocument): String? {
        return when (format) {
            "json" -> formatJson(document)
            else -> null
        }
    }

    private fun formatJson(document: UsfmDocument): String {
        val mapper = jacksonObjectMapper()
        val json = mapper.writeValueAsString(document)

        return json
    }
}