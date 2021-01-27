package com.example.socket_test

import java.io.OutputStream
import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.Socket
import java.nio.charset.Charset

// Class Send_Frame
class Send_Frame() {

    // Function run()
    // Send Frame to IP
    fun run(address: String, port: Int, message: String) : String {

        try {
            // Creates new Socket Object
            val connection: Socket = Socket(address, port)
            // Create new Output Stream
            val writer: OutputStream = connection.getOutputStream()
            // Send Message
            // message : String -> Ascii Code
            // Frame convert Ascii to Hex
            // \n necessary!
            writer.write((message + '\n').toByteArray(Charset.defaultCharset()))
            writer.close()
            connection.close()

            // Return "" for no Error
            return ""

        } catch (e : ConnectException) {
            // Return Error Message for faild Connection
            // ToDo: English Text
            return "Verbindung fehlgeschlagen... Bitte überprüfe die IP-Adresse und die Verbindung des TV's!"
        } catch (e : NoRouteToHostException) {
            // Return Error Message for faild Connection
            // ToDo: English Text
            return "Verbindung fehlgeschlagen... Bitte überprüfe die IP-Adresse und die Verbindung des TV's!"
        } catch (e : Exception) {
            // Returns Error Message
            return e.toString()
        }

    }

}
