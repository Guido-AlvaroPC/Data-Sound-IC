package com.br.fiap.chirpdemo.chirp

import android.content.Context
import android.media.SoundPool
import com.br.fiap.chirpdemo.R

class AudioNotifications(context: Context) {
    val SOUND_ERROR = 2
    val SOUND_SUCCESS_1 = 0
    val SOUND_SUCCESS_2 = 1
    private var error_id = 0
    private var soundPool = SoundPool(1, 3, 0)
    private var success_1_id = 0
    private var success_2_id = 0

    init {
        success_1_id = 0
        success_2_id = 0
        error_id = 0
        success_1_id = soundPool.load(context, R.raw.success_1, 1)
        success_2_id = soundPool.load(context, R.raw.success_2, 1)
        error_id = soundPool.load(context, R.raw.error, 1)
    }

    fun playSoundNotification(idx: Int) {
        val res_id: Int = when (idx) {
            0 -> success_1_id
            1 -> success_2_id
            2 -> error_id
            else -> 0
        }
        soundPool.play(res_id, 1.0f, 1.0f, 0, 0, 1.0f)
    }

    fun cleanup() {
        soundPool.release()
    }
}