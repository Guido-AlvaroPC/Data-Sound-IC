package com.br.fiap.chirpdemo.chirp

import android.content.Context
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.br.fiap.chirpdemo.FirstFragment
import com.br.fiap.chirpdemo.MainActivity
import io.chirp.chirpengine.ChirpEngine
import io.chirp.chirpengine.ChirpEngineObserver

class ChirpSDK(context: Context, appKey: String, appSecret: String) {
    private val chirpAudioManager = ChirpAudioManager()
    private var context: Context? = context

    private var appKey: String? = appKey
    private var appSecret: String? = appSecret
    private var audioThread: Thread? = null
    var texto:String? = null

    val lista = mutableListOf<String>()
    private val observers: MutableList<ChirpSDKObserver?> = ArrayList()
    private val chirpEngineListener: ChirpEngineObserver = object : ChirpEngineObserver {
        // from class: io.chirp.sdk.ChirpSDK.5
        // io.chirp.chirpengine.ChirpEngineObserver
        override fun onChirpReceived(shortCode: ShortCode?) {
            this@ChirpSDK.notifyObserversShortCodeReceived(shortCode)
        }
    }

    init {
        chirpAudioManager.initialise(context)
//        ChirpEngine().setListener(this.chirpEngineListener)
    }

    fun stopListening() {
        this.chirpAudioManager.stopListening()
        this.audioThread?.let {
            this.audioThread?.interrupt()
            this.audioThread = null
        }
    }

    fun setObserver(observer: ChirpSDKObserver?) {
        observers.add(observer)
    }

    fun startListening() {
        this.audioThread = object : Thread() {
            // from class: io.chirp.sdk.ChirpSDK.3
            // java.lang.Thread, java.lang.Runnable
            override fun run() {
                chirpAudioManager.startListening()
            }
        }
        (audioThread as Thread).start()
    }

    fun play(shortCode: ShortCode?) {
        val audioThread = object : Thread() {
            // from class: io.chirp.sdk.ChirpSDK.4
            // java.lang.Thread, java.lang.Runnable
            override fun run() {
                ChirpAudioManager().chirpShortCode(shortCode)

            }
        }
        audioThread.start()
    }

    fun stop() {
        (audioThread as Thread).stop()
    }

    fun notifyObserversShortCodeReceived(shortCode: ShortCode?) {


        if (shortCode.toString().last().equals("1")) {
            lista.add(shortCode.toString())
            Log.e("Fim de lista",lista.toString())
        }
        else
            lista.add(shortCode.toString())

//        Toast.makeText(context,shortCode.toString(),Toast.LENGTH_LONG).show()

    }

//    fun validaString(): List<CharSequence> {
//        val str = binding.edtMsg.text.toString()
//        return str.chunked(10) {it.padEnd(10,'1')}
//    }

}