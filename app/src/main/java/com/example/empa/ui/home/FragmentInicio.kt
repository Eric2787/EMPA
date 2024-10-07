package com.example.empa.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.empa.R
import com.example.empa.databinding.FragmentInicioBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentInicioBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentInicioBinding.inflate(inflater, container, false)

        binding.btnFragmentIni1.setOnClickListener {

            view : View -> view.findNavController().navigate(R.id.action_nav_inicio_to_nav_pedidos)

        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}