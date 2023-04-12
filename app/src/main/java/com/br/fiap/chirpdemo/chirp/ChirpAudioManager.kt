package com.br.fiap.chirpdemo.chirp

import android.content.Context
import android.media.AudioManager
import android.media.AudioRecord
import android.media.AudioTrack
import android.util.Log
import io.chirp.chirpengine.ChirpEngine

class ChirpAudioManager {
    private val SAMPLE_RATE_DEVICE = 44100
    private val TAG = "ChirpAudioManager"
    private var audioPlayer: AudioTrack? = null
    private var audioRecorder: AudioRecord? = null
    private lateinit var buffer: ShortArray
    private var bufferSize = 0
    private var bufferSizeShort = 0
    private var isAudioRunning = false
    private var sampleRate = 0

    fun initialise(context: Context?) {
        ChirpEngine().register()
        ChirpEngine().init_audio()
        initialiseBuffers()
        context?.let { setVolume(it) }
    }


    fun initialiseBuffers() {

        sampleRate = SAMPLE_RATE_DEVICE

        bufferSize = determineBufferSize()
        bufferSizeShort = determineBufferSizeForShortArray(bufferSize)
        buffer = ShortArray(bufferSizeShort)
        audioRecorder = AudioRecord(1, sampleRate, 2, 2, bufferSize)
        Log.i(TAG, "Recorder state " + audioRecorder?.state)
        audioPlayer = AudioTrack(3, sampleRate, 2, 2, bufferSize, 1)
        Log.i(TAG, "Player state" + audioPlayer?.state)
    }

    private fun setVolume(context: Context) {
        val audioManager = context.getSystemService("audio") as AudioManager
        val maxVolume = audioManager.getStreamMaxVolume(3)
        val volume = maxVolume / 3
        audioManager.setStreamVolume(3, volume, 0)
        if (maxVolume > 0) {
            ChirpEngine().set_hw_volume(volume.toFloat())
        }
    }

    fun startListening() {
        Log.i(TAG, "Audio Thread started")
        isAudioRunning = true
        audioRecorder?.startRecording()
        audioPlayer?.play()
        while (isAudioRunning) {
            audioRecorder?.read(buffer, 0, bufferSizeShort)
            ChirpEngine().process_buffer(buffer, bufferSizeShort)
            audioPlayer?.write(buffer, 0, bufferSizeShort)
        }
        audioPlayer?.stop()
        audioRecorder?.stop()
    }

    fun stopListening() {
        isAudioRunning = false
    }

    fun chirpShortCode(shortCode: ShortCode?) {
        Log.i(TAG, "chirpShortCode ***")
        ChirpEngine().encode_chirp(0, shortCode?.getShortCode())
    }

    private fun determineBufferSize(): Int {
        val bufferSizeRecording = AudioRecord.getMinBufferSize(sampleRate, 2, 2)
        val bufferSizePlayer = AudioTrack.getMinBufferSize(sampleRate, 2, 2)
        Log.i(TAG, "Buffer size rec: $bufferSizeRecording")
        Log.i(TAG, "Buffer size play: $bufferSizePlayer")
        return determineBufferSize(bufferSizeRecording, bufferSizePlayer)
    }

    private fun determineBufferSize(bufferSizeRecording: Int, bufferSizePlayer: Int): Int {
        return 8192
    }

    private fun determineBufferSizeForShortArray(bufferSize: Int): Int {
        return bufferSize / 2
    }
}