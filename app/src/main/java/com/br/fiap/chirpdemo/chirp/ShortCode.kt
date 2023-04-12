package com.br.fiap.chirpdemo.chirp

class ShortCode(shortCode: String?) {
    private var shortCode: String? = null

    init {
        this.shortCode = shortCode
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val shortCode1 = o as ShortCode
        if (shortCode != null) {
            if (shortCode == shortCode1.shortCode) {
                return true
            }
        } else if (shortCode1.shortCode == null) {
            return true
        }
        return false
    }

    override fun hashCode(): Int {
        return if (shortCode != null) {
            shortCode.hashCode()
        } else 0
    }

    fun getShortCode(): String? {
        return shortCode
    }

}