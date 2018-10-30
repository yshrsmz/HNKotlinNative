package sample

actual class Sample actual constructor() {
    actual fun checkMe(): Int = 9
}

actual object Platform {
    actual val name: String = "Android"
}
