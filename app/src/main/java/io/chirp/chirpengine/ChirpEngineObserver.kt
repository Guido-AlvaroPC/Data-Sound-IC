package io.chirp.chirpengine

import com.br.fiap.chirpdemo.chirp.ShortCode

interface ChirpEngineObserver {
    fun onChirpReceived(shortCode: ShortCode?)
}