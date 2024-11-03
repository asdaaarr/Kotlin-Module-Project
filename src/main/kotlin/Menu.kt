import java.util.Scanner

class Menu(private val options: List<String>, private val actions: List<() -> Unit>) {
    private val scanner = Scanner(System.`in`)

    fun show() {
        while (true) {
            println("Выберите действие:")
            options.forEachIndexed { index, option ->
                println("${index + 1}. $option")
            }
            println("${options.size + 1}. Выход")

            val userChoice = getUserInput()
            if (userChoice == (options.size + 1)) {
                break
            }
            if (userChoice in 1..options.size) {
                actions[userChoice - 1]()
            } else {
                println("Ошибка: введите номер пункта меню от 1 до ${options.size + 1}")
            }
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