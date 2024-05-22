package org.bibletranslationtools.usfmgrammar.common.model

sealed class Content {
    data class Verse(
        val verseNumber: String,
        val verseText: String,
    ) : Content()
}