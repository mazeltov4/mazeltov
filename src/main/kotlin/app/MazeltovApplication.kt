package app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MazeltovApplication

fun main(args: Array<String>) {
	runApplication<MazeltovApplication>(*args)
}
