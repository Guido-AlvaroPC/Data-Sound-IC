package com.br.fiap.chirpdemo.chirp

import android.app.Activity
import android.app.Service
import android.content.Intent
import android.media.AudioManager
import android.media.AudioManager.OnAudioFocusChangeListener
import android.os.Binder
import android.os.IBinder
import android.util.Log
import com.br.fiap.chirpdemo.MainActivity
import javax.inject.Inject


class ChirpService: Service() {
    val KEY = "chirp_android_1_4_0"
    val PWD = "Yur87CLY9Hg4hdebqYGAJjVhlRMAb7SPhXjiV1U7Qo5cx6D16W"

    var receivingChirp = false
    @Inject
    var audioNotifications: AudioNotifications? = null
    var chirpModel: ChirpModel? = null
    @Inject
    var chirpSdk: ChirpSDK? = null
    private val binder: IBinder = ChirpBinder()


    private val modelChangeListener: ChirpModel.OnModelChangeListener =
        object : ChirpModel.DefaultOnModelChangeListener() {
            // from class: io.chirp.service.ChirpService.1
            // io.chirp.model.ChirpModel.DefaultOnModelChangeListener, io.chirp.model.ChirpModel.OnModelChangeListener
            override fun onChirpNotFound(c: Chirp?) {
                audioNotifications?.playSoundNotification(2)
            }

            // io.chirp.model.ChirpModel.DefaultOnModelChangeListener, io.chirp.model.ChirpModel.OnModelChangeListener
            override fun onNetworkUnavailable(c: Chirp?) {
                audioNotifications?.playSoundNotification(2)
            }

            // io.chirp.model.ChirpModel.DefaultOnModelChangeListener, io.chirp.model.ChirpModel.OnModelChangeListener
            override fun onNewChirpLoaded(c: Chirp?) {
                audioNotifications?.playSoundNotification(1)
            }
        }

    private val chirpSDKObserver: ChirpSDKObserver = object : ChirpSDKObserver {
        override fun onChirpHeard(shortCode: ShortCode?) {
            chirpModel?.hearChirp(shortCode?.getShortCode())
        }
    }

    private val onAudioFocusChangeListener =
        OnAudioFocusChangeListener { focusChange ->

            // from class: io.chirp.service.ChirpService.3
            // android.media.AudioManager.OnAudioFocusChangeListener
            if (focusChange == 1) {
                Log.i("ChirpService", "OnAudioFocusChangeListener audio focus gained")
            } else if (focusChange == -1) {
                Log.i("ChirpService", "OnAudioFocusChangeListener audio focus lost")
            }
        }

    override fun onBind(p0: Intent?): IBinder? {
        return binder
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("ChirpService", "onDestroy")
        chirpSdk?.stopListening()
        audioNotifications?.cleanup()
    }

    override fun onCreate() {
        super.onCreate()
        Log.i("ChirpService", "onCreate")

        audioNotifications = baseContext.let { it1 -> AudioNotifications(it1) }
        chirpModel = ChirpModel()
//        (applicationContext as ChirpApplication).inject(this)
        chirpSdk = baseContext.let { it1 -> ChirpSDK(it1, KEY, PWD) }
        this.chirpModel?.addModelChangeListener(modelChangeListener)
        chirpSdk?.setObserver(chirpSDKObserver)
        setAudioFocus()
        chirpSdk?.startListening()
    }

    private fun setAudioFocus() {
        val am = getSystemService("audio") as AudioManager
        am.requestAudioFocus(this.onAudioFocusChangeListener, 3, 1)
    }

    class ChirpBinder : Binder() {
        fun getService(): ChirpService? {
            return this.getService()
        }

    }
}