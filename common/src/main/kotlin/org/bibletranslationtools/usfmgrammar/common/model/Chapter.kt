package org.bibletranslationtools.usfmgrammar.common.model

data class Chapter(
    val chapterNumber: String,
    val contents: List<Content>
)