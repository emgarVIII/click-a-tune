package com.emgar.click_a_tune

import MusicPlayer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.emgar.click_a_tune.databinding.PlayFragmentTilesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

class PlayFragment : Fragment(), CoroutineScope by MainScope() {
    private var _binding: PlayFragmentTilesBinding? = null
    private val binding get() = _binding!!
    private lateinit var musicPlayer: MusicPlayer
    private lateinit var repository: Repository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = PlayFragmentTilesBinding.inflate(inflater, container, false)
        repository = Repository() // Initialize the repository
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gameController = GameController(binding.scoreTV)
        gameController.startGame() // Start updating the score

        binding.buttonBackToSongSelect.setOnClickListener {
            findNavController().navigate(R.id.action_playFragment_to_songSelectFragment)
        }

        // Fetch song info from the repository
        val songInfo = repository.fetchData().first()  // Gets the first song; modify as needed for more functionality
        musicPlayer = MusicPlayer(requireContext(), songInfo.audioFilename)
        musicPlayer.startMusic()

        initGameComponents()
    }

    private fun initGameComponents() {
        // Initialize game components like borders, timers, jumps etc.
    }

    override fun onDestroyView() {
        super.onDestroyView()
        musicPlayer.stopMusic() // Stop the music when the view is destroyed
        _binding = null
    }
}
