package com.br.fiap.chirpdemo.chirp

interface ChirpSDKObserver {
    fun onChirpHeard(shortCode: ShortCode?)
}