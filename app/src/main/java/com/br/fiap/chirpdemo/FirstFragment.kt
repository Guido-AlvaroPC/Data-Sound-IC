package com.br.fiap.chirpdemo

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.br.fiap.chirpdemo.chirp.ChirpSDK
import com.br.fiap.chirpdemo.chirp.ShortCode
import com.br.fiap.chirpdemo.databinding.FragmentFirstBinding
import io.chirp.chirpengine.ChirpEngine
import io.chirp.chirpengine.ChirpEngineObserver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.FileOutputStream
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    var editValor : EditText? = null
    val KEY = "chirp_android_1_4_0"
    val PWD = "Yur87CLY9Hg4hdebqYGAJjVhlRMAb7SPhXjiV1U7Qo5cx6D16W"
    private val handler = Handler()
    private var _binding: FragmentFirstBinding? = null
    val lista = mutableListOf<String>()

    private val chirpEngineListener: ChirpEngineObserver = object : ChirpEngineObserver {
        // from class: io.chirp.sdk.ChirpSDK.5
        // io.chirp.chirpengine.ChirpEngineObserver
        @RequiresApi(Build.VERSION_CODES.O)
        override fun onChirpReceived(shortCode: ShortCode?) {
            notifyObserversShortCodeReceived(shortCode)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun notifyObserversShortCodeReceived(shortCode: ShortCode?) {
        if (shortCode?.getShortCode().toString().last().equals('1')) {
            lista.add(shortCode?.getShortCode().toString())
            Log.e("Fim de lista",lista.toString())
            val concatenaString =   lista.joinToString("")
            val trocaString = concatenaString.replace("1","")
            saveFile(this.requireContext(),"log_recebimento.txt")
            activity?.runOnUiThread {
                binding.msgRecebido.text =  trocaString
            }
            lista.clear()
        }
        else
            lista.add(shortCode?.getShortCode().toString())

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveFile(context: Context, filename:String) {
        try {
            val outputStream: FileOutputStream = context.openFileOutput(filename, Context.MODE_APPEND)
            val horario = getCurrentDateTime()
            val palavra = "$horario\n"
            outputStream.write(palavra.toByteArray())
            outputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentDateTime(): String {
        val currentDateTime: LocalDateTime = LocalDateTime.now()
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy")

        return currentDateTime.format(formatter)
    }




    @Inject
    var chirpSDK: ChirpSDK? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        chirpSDK = activity?.baseContext?.let { it1 -> ChirpSDK(it1, KEY, PWD) }
        ChirpEngine().setListener(this.chirpEngineListener)
        binding.sendMsg.setOnClickListener {
            saveFile(this.requireContext(),"log_envio.txt")
            val repeatableJob = CoroutineScope(Dispatchers.IO).launch {
                for (palavra in splitString()) {

                    chirpSDK?.play(ShortCode(palavra.toString()))
                    delay(2000)
                }
            }



        }
    }

    fun splitString(): List<CharSequence> {
//            val str = "otorrinolaringologista"
        val str = binding.edtMsg.text.toString()
        return str.chunked(10) {it.padEnd(10,'1')}
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

    }
}