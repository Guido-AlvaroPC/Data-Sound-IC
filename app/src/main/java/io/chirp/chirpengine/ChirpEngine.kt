package io.chirp.chirpengine

import android.util.Log
import com.br.fiap.chirpdemo.chirp.ShortCode

class ChirpEngine {


    fun init_audio() {
        ChirpEngineJNI.init_audio()
    }

    fun free_audio() {
        ChirpEngineJNI.free_audio()
    }

    fun process_buffer(buffer: ShortArray?, n_elem: Int) {
        ChirpEngineJNI.process_buffer(buffer, n_elem)
    }

    fun set_hw_volume(hw_vol: Float) {
        ChirpEngineJNI.set_hw_volume(hw_vol)
    }

    fun encode_chirp(fd_index: Int, shortcode: String?) {
        ChirpEngineJNI.encode_chirp(fd_index, shortcode)
    }

    fun register() {
        ChirpEngineJNI.register()
    }

    companion object {
        var observer: ChirpEngineObserver? = null

        @JvmStatic
        fun onChirpReceived(shortCode: String?, errors: Int, fd_index: Int) {
            if (fd_index == 0 || errors <= 2) {
                Log.e("teste",shortCode.toString())
                observer?.onChirpReceived(ShortCode(shortCode))
            }
        }
    }


    fun initgl(w: Int, h: Int) {
        ChirpEngineJNI.initgl(w, h)
    }

    fun glstep() {
        ChirpEngineJNI.glstep()
    }

    fun setListener(observer2: ChirpEngineObserver?) {
        observer = observer2
    }
}
