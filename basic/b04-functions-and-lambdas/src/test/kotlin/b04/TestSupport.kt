package b04

import java.io.ByteArrayOutputStream
import java.io.PrintStream

// 標準出力に書かれた文字列を取り出す。改行は \n にそろえる
fun captureStdout(block: () -> Unit): String {
    val original = System.out
    val buffer = ByteArrayOutputStream()
    System.setOut(PrintStream(buffer, true, Charsets.UTF_8))
    try {
        block()
    } finally {
        System.setOut(original)
    }
    return buffer.toString(Charsets.UTF_8).replace("\r\n", "\n")
}
