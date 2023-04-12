package com.br.fiap.chirpdemo.chirp

class ChirpModel {

    private val listeners: MutableList<OnModelChangeListener?> = ArrayList()

    fun hearChirp(shortCode: String?) {
//        pullChirp(shortCode, this.newChirpLoadedCallback)
        for (listener in this.listeners) {
            listener?.onHeardChirp()
        }
    }

    fun addModelChangeListener(callback: OnModelChangeListener?) {
        listeners.add(callback)
    }

    /* loaded from: classes.dex */
    open class DefaultOnModelChangeListener : OnModelChangeListener {
        // io.chirp.model.ChirpModel.OnModelChangeListener
        override fun onHeardChirp() {}

        // io.chirp.model.ChirpModel.OnModelChangeListener
        override fun onDefaultChirpLoaded(c: Chirp?) {}

        // io.chirp.model.ChirpModel.OnModelChangeListener
        override fun onNewChirpLoaded(c: Chirp?) {}

        // io.chirp.model.ChirpModel.OnModelChangeListener
        override fun onNewChirpAdded(c: Chirp?) {}

        // io.chirp.model.ChirpModel.OnModelChangeListener
        override fun onChirpPushed(c: Chirp?) {}

        // io.chirp.model.ChirpModel.OnModelChangeListener
        override fun onChirpAlreadyExists(c: Chirp?) {}

        // io.chirp.model.ChirpModel.OnModelChangeListener
        override fun onOfflineChirpLoaded(c: Chirp?) {}

        // io.chirp.model.ChirpModel.OnModelChangeListener
        override fun onChirpNotFound(c: Chirp?) {}

        // io.chirp.model.ChirpModel.OnModelChangeListener
        override fun onNetworkUnavailable(c: Chirp?) {}

        // io.chirp.model.ChirpModel.OnModelChangeListener
        override fun onShortenFailed(c: Chirp?) {}

        // io.chirp.model.ChirpModel.OnModelChangeListener
        override fun onPictureDownloadStarted(filename: String?) {}

        // io.chirp.model.ChirpModel.OnModelChangeListener
        override fun onPictureDownloadProgress(progress: Int) {}

        // io.chirp.model.ChirpModel.OnModelChangeListener
        override fun onPictureDownloadFinished(path: String?) {}

        // io.chirp.model.ChirpModel.OnModelChangeListener
        override fun onPictureDownloadFailed() {}

        // io.chirp.model.ChirpModel.OnModelChangeListener
        override fun onPictureUploadStarted() {}

        // io.chirp.model.ChirpModel.OnModelChangeListener
        override fun onPictureUploadProgress(progress: Int) {}

        // io.chirp.model.ChirpModel.OnModelChangeListener
        override fun onPictureUploadFinished() {}

        // io.chirp.model.ChirpModel.OnModelChangeListener
        override fun onPictureUploadFailed() {}

        // io.chirp.model.ChirpModel.OnModelChangeListener
        override fun onLowDiskSpace() {}

        // io.chirp.model.ChirpModel.OnModelChangeListener
        override fun onShowTooltip() {}

        // io.chirp.model.ChirpModel.OnModelChangeListener
        override fun onBlacklistedUrl(c: Chirp?) {}

        // io.chirp.model.ChirpModel.OnModelChangeListener
        override fun onTooManyChirps() {}
    }

    interface OnModelChangeListener {
        fun onBlacklistedUrl(chirp: Chirp?)
        fun onChirpAlreadyExists(chirp: Chirp?)
        fun onChirpNotFound(chirp: Chirp?)
        fun onChirpPushed(chirp: Chirp?)
        fun onDefaultChirpLoaded(chirp: Chirp?)
        fun onHeardChirp()
        fun onLowDiskSpace()
        fun onNetworkUnavailable(chirp: Chirp?)
        fun onNewChirpAdded(chirp: Chirp?)
        fun onNewChirpLoaded(chirp: Chirp?)
        fun onOfflineChirpLoaded(chirp: Chirp?)
        fun onPictureDownloadFailed()
        fun onPictureDownloadFinished(str: String?)
        fun onPictureDownloadProgress(i: Int)
        fun onPictureDownloadStarted(str: String?)
        fun onPictureUploadFailed()
        fun onPictureUploadFinished()
        fun onPictureUploadProgress(i: Int)
        fun onPictureUploadStarted()
        fun onShortenFailed(chirp: Chirp?)
        fun onShowTooltip()
        fun onTooManyChirps()
    }
}