package b03

// 出力予測の問題。各関数を読み、出力を ANSWERS.md に書いてから実行する。
// 実行: リポジトリのルートで ./gradlew -q :b03:run --args=p1（p1 の部分を問題の番号にする）

fun p1() {
    val a = 10
    val b = if (a > 5) {
        println("checking")
        "large"
    } else {
        "small"
    }
    println(b)
}

fun p2() {
    for (i in 1..3) print(i)
    println()
    for (i in 1..<3) print(i)
    println()
    for (i in 3..1) print(i)
    println()
    for (i in 3 downTo 1) print(i)
    println()
}

fun p3() {
    val n = 4
    val label = when {
        n > 2 -> "over 2"
        n > 3 -> "over 3"
        else -> "small"
    }
    println(label)
}

fun main(args: Array<String>) {
    when (args.firstOrNull()) {
        "p1" -> p1()
        "p2" -> p2()
        "p3" -> p3()
        else -> println("問題の番号を指定する: ./gradlew -q :b03:run --args=p1")
    }
}
