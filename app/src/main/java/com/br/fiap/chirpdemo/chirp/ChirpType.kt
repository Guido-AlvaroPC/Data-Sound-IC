package com.br.fiap.chirpdemo.chirp

import android.util.Log

enum class ChirpType(
    var NULL: ChirpType? = null,
    var NOTE: ChirpType? = null,
    var URL: ChirpType? = null,
    var PICTURE: ChirpType? = null,
    var TOPSHOP: ChirpType? = null,
    var VIDEO: ChirpType? = null,
    var mimeType: String = ""
)
