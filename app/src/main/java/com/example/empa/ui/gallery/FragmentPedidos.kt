package com.example.empa.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.empa.databinding.FragmentPedidosBinding
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

class GalleryFragment : Fragment() {

    private var _binding: FragmentPedidosBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPedidosBinding.inflate(inflater, container, false)

        binding.btnFragmentPed1.setOnClickListener {

            lifecycleScope.launch {

                try {

                    val nombre = binding.editTxtPed1.text.toString()
                    val grado = binding.editNbrPed1.text.toString()
                    val grupo = binding.editTxtPed2.text.toString()
                    val tipo1 = binding.editTxtPed3.text.toString()
                    val tipo2 = binding.editTxtPed5.text.toString()
                    val cantidad = binding.editNbrPed2.text.toString()
                    val dia = binding.editTxtPed4.text.toString()

                    val response = ApiService.getInstance().postRegistro(nombre, grado, grupo, tipo1, tipo2, cantidad, dia)
                    if (response.isSuccessful) {

                        Toast.makeText(activity, "El pedido se ha realizado con exito", Toast.LENGTH_LONG).show()

                    }

                } catch (_: Exception) {

                    Toast.makeText(activity, "Error al realizar el pedido", Toast.LENGTH_LONG).show()

                }

            }

        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

interface ApiService {
    companion object RetrofitClient {
        @Volatile
        private var instance: ApiService? = null
        fun getInstance(): ApiService = instance ?: crearInstancia()
        private fun crearInstancia(): ApiService = Retrofit.Builder()
            .baseUrl("http://chocolateconleche.ddns.net/api.php/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
            .create(ApiService::class.java)
    }
    @FormUrlEncoded
    @POST("registrar")
    suspend fun postRegistro(
        @Field("NOMBRE") nombre: String,
        @Field("GRADO") grado: String,
        @Field("GRUPO") grupo: String,
        @Field("TIPO1") tipo1: String,
        @Field("TIPO2") tipo2: String,
        @Field("CANTIDAD") cantidad: String,
        @Field("DIA") dia: String,
    ): Response<Unit>
}