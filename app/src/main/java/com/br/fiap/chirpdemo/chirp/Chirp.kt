package com.br.fiap.chirpdemo.chirp

class Chirp {
    private val bottomBody: String? = null
    private val creationTime: String? = null
    private var data: String? = null
    private val data1: String? = null
    private val data2: String? = null
    private val data3: String? = null
    private val frontCaption1: String? = null
    private val frontCaption2: String? = null
    private val headline: String? = null
    private var isBlacklisted = false
    private var isSynchronized = false
    private val linkText: String? = null
    private val linkUrl: String? = null
    private var localId: Long = 0
    private val localPath: String? = null
    private val localPath1: String? = null
    private val localPath2: String? = null
    private val localPath3: String? = null
    private var longCode: String? = null
    private val shareText: String? = null
    private var shortCode: String? = null
    private val sourceName: String? = null
    private val sourceUser: String? = null
    private var title: String? = null
    private val topBody: String? = null
    private var type: ChirpType? = null

    fun Chirp(shortCode: String?) {
        type = ChirpType.values()[0]
        isBlacklisted = false
        this.shortCode = shortCode
    }

    fun Chirp(localId: Int, shortCode: String?) {
        type = ChirpType.values()[0]
        isBlacklisted = false
        this.localId = localId.toLong()
        this.shortCode = shortCode
    }

    fun Chirp(type: ChirpType?, data: String?, title: String?, isSynchronized: Boolean) {
        this.type = ChirpType.values()[0]
        isBlacklisted = false
        this.type = type
        this.data = data
        this.title = title
        this.isSynchronized = isSynchronized
    }

    fun Chirp(
        type: ChirpType?,
        data: String?,
        title: String?,
        shortCode: String?,
        longCode: String?
    ) {
        this.type = ChirpType.values()[0]
        isBlacklisted = false
        this.type = type
        this.data = data
        this.title = title
        this.shortCode = shortCode
        this.longCode = longCode
    }

}