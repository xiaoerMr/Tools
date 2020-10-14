package com.sai.utils.socket

import java.io.InputStream
import java.io.OutputStream
import java.net.InetSocketAddress
import java.net.Socket

class SocketUtils(private val hostName: String, private val port: Int) {

    private var outputStream: OutputStream? = null
    private var inputStream: InputStream? = null
    private val socket by lazy { Socket() }

    init {
        val socketAddress = InetSocketAddress(hostName, port)
        socket.connect(socketAddress)
    }

    fun send(msg: String): Boolean {
        return if (socket.isConnected) {
            outputStream = socket.getOutputStream()
            outputStream?.let {
                it.write(msg.toByteArray())
                it.flush()
            }
            true
        } else {
            false
        }
    }


    fun receive(): String? {
        return if (socket.isConnected) {
            inputStream = socket.getInputStream()
            val byte = ByteArray(1024)
            val read = inputStream!!.read(byte)
            if (read > 0) {
                val readMsg = String(byte, 0, read)
                readMsg
            } else {
                null
            }
        } else {
            null
        }
    }

    fun reconnect() {
        val socketAddress = InetSocketAddress(hostName, port)
        socket.connect(socketAddress)
    }

    fun close() {
        inputStream?.close()
        outputStream?.close()
        socket.close()
    }
}