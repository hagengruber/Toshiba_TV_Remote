package com.example.socket_test

import java.io.OutputStream
import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.Socket
import java.nio.charset.Charset

class Send_Frame() {

    fun run(address: String, port: Int, message: String) : String {

        try {
            val connection: Socket = Socket(address, port)
            if (connection.isConnected) { println("Ja") } else { println("Nein") }

            val writer: OutputStream = connection.getOutputStream()
            writer.write((message + '\n').toByteArray(Charset.defaultCharset()))
            writer.close()
            connection.close()
            return ""
        } catch (e : ConnectException) {
            return "Verbindung fehlgeschlagen... Bitte überprüfe die IP-Adresse und die Verbindung des TV's!"
        } catch (e : NoRouteToHostException) {
            return "Verbindung fehlgeschlagen... Bitte überprüfe die IP-Adresse und die Verbindung des TV's!"
        } catch (e : Exception) {
            return e.toString()
        }

    }

}
