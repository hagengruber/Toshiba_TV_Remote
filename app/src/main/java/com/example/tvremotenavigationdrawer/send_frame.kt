package com.example.socket_test

import java.io.OutputStream
import java.net.Socket
import java.nio.charset.Charset

class Send_Frame() {

    fun run(address: String, port: Int, message: String) {

        val connection: Socket = Socket(address, port)

        if (connection.isConnected) { println("Ja") } else { println("Nein") }

        val writer: OutputStream = connection.getOutputStream()
        writer.write((message + '\n').toByteArray(Charset.defaultCharset()))
        writer.close()
        connection.close()

    }

}
