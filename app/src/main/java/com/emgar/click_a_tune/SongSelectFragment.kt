package com.emgar.click_a_tune

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.emgar.click_a_tune.databinding.FragmentSongselectBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class SongSelectFragment : Fragment() {

    private var _binding: FragmentSongselectBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSongselectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonBack.setOnClickListener {
            findNavController().navigate(R.id.action_songSelectFragment_to_welcomeFragment)
        }
        binding.buttonPlay.setOnClickListener {
            findNavController().navigate(R.id.action_songSelectFragment_to_playFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}