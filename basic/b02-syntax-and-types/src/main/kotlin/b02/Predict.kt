package b02

// 出力予測の問題。各関数を読み、出力を ANSWERS.md に書いてから実行する。
// 実行: リポジトリのルートで ./gradlew -q :b02:run --args=p1（p1 の部分を問題の番号にする）

fun p1() {
    println(7 / 2)
    println(7 / 2.0)
    println(-7 / 2)
}

fun p2() {
    val n = 2_000_000_000
    println(n + n)

    val m = 3_000_000_000
    println(m + m)
}

fun p3() {
    var a = 1
    val s1 = "a is $a"
    a = 2
    println(s1)
    println("${s1.replace("is", "was")}, but now is $a")
}

// p4 はコンパイルできるかを予測する問題。予測を書いてから、下の 4 行の先頭の // を外し、
// ./gradlew -q :b02:run --args=p4 を実行する。確かめたら // を戻す
fun p4() {
    // val total = 100
    // var count = 1
    // count = 2
    // total = 200
    println("p4 の 4 行はコメントのまま")
}

fun main(args: Array<String>) {
    when (args.firstOrNull()) {
        "p1" -> p1()
        "p2" -> p2()
        "p3" -> p3()
        "p4" -> p4()
        else -> println("問題の番号を指定する: ./gradlew -q :b02:run --args=p1")
    }
}
