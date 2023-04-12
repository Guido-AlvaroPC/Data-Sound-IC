package io.chirp.chirpengine

object ChirpEngineJNI {
    external fun encode_chirp(i: Int, str: String?)

    external fun free_audio()

    external fun glstep()

    external fun init_audio()

    external fun initgl(i: Int, i2: Int)

    external fun process_buffer(sArr: ShortArray?, i: Int)

    external fun register()

    external fun set_hw_volume(f: Float)

    init {
        try {
            System.loadLibrary("chirpandroid");
        } catch ( e:UnsatisfiedLinkError) {
            System.err.println("native code library failed to load.\n" + e);
            System.exit(1);
        }
    }
}