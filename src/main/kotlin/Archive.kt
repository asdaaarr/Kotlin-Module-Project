
class Archive(val name: String) {
    val notes = mutableListOf<Note>()

    fun addNote() {
        println("Введите название заметки:")
        val title = readLine()?.trim().orEmpty()
        if (title.isEmpty()) {
            println("Ошибка: название не может быть пустым.")
            return
        }

        println("Введите текст заметки:")
        val content = readLine()?.trim().orEmpty()
        if (content.isEmpty()) {
            println("Ошибка: содержание не может быть пустым.")
            return
        }

        notes.add(Note(title, content))
        println("Заметка '$title' добавлена в архив '$name'.")
    }

    fun showNotes() {
        if (notes.isEmpty()) {
            println("В архиве '$name' пока нет заметок.")
        } else {
            println("Список заметок архива ${this.name}:")
            notes.forEachIndexed { index, note ->
                println("${index + 1}. ${note.title}")
            }
            println("${notes.size + 1}. Выход")
        }
    }

    fun viewNote(index: Int) {
        if ((index - 1) in notes.indices) {
            val note = notes[index - 1]
            println("Заметка: ${note.title}")
            println("Содержание: ${note.content}")
        } else {
            println("Ошибка: некорректный номер заметки.")
        }
    }
}