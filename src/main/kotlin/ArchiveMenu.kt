import java.util.Scanner

class ArchiveMenu {
    private val archives = mutableListOf<Archive>()
    private val scanner = Scanner(System.`in`)

    fun start() {
        println("Приложение 'Заметки'")

        val menu = Menu(
            listOf("Создать архив", "Выбрать архив"),
            listOf(
                { createArchive() },
                { chooseArchive() }
            )
        )
        menu.show()
    }

    private fun createArchive() {
        println("Введите название архива:")
        val name = scanner.nextLine().trim()
        if (name.isNotEmpty()) {
            archives.add(Archive(name))
            println("Архив '$name' создан.")
        } else {
            println("Ошибка: имя архива не может быть пустым.")
        }
    }

    private fun chooseArchive() {
        if (archives.isEmpty()) {
            println("Нет доступных архивов.")
            return
        }
        println("Список доступных архивов:")
        archives.forEachIndexed { index, archive ->
            println("${index + 1}. ${archive.name}")
        }
        println("${archives.size + 1}. Выход")

        println("Выберите архив:")
        val choice = getUserInput()
        if ((choice - 1) in archives.indices) {
            showArchiveMenu(archives[choice - 1])
        } else if (choice == (archives.size + 1)) {
            return
        } else {
            println("Ошибка: некорректный номер архива.")
        }
    }

    private fun showArchiveMenu(archive: Archive) {
        val menu = Menu(
            listOf("Добавить заметку", "Просмотреть заметки"),
            listOf(
                { archive.addNote() },
                { showNotesMenu(archive) }
            )
        )
        menu.show()
    }

    private fun showNotesMenu(archive: Archive) {
        archive.showNotes()
        if (archive.notes.isEmpty()) {
            return
        } else {
            println("Введите номер заметки для просмотра или нажмите Выход для возврата:")
        }
        val choice = getUserInput()
        if (choice == (archive.notes.size + 1)) {
            return
        } else {
            archive.viewNote(choice)
        }
    }

    private fun getUserInput(): Int {
        while (true) {
            val input = scanner.nextLine()
            val number = input.toIntOrNull()
            if (number != null) return number
            println("Ошибка: введите число.")
        }
    }
}