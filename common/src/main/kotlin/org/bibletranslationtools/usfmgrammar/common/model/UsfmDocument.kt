package org.bibletranslationtools.usfmgrammar.common.model

class UsfmDocument(
    val book: Book,
    val chapters: List<Chapter>
) {

    fun addChapter(chapter: Chapter) {
        chapters as MutableList
        chapters.add(chapter)
    }

    fun addVerse(chapter: Chapter, verse: Content.Verse) {
        chapter.contents as MutableList
        chapter.contents.add(verse)
    }
}