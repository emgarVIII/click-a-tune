package com.emgar.click_a_tune


import MusicPlayer
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
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
    private lateinit var jump: Jump
    private lateinit var border: Border

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = PlayFragmentTilesBinding.inflate(inflater, container, false)
        repository = Repository() // Initialize the repository
        //musicPlayer.startMusic()


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val songInfo = repository.fetchData().first() // Gets the first song
        musicPlayer = MusicPlayer(requireContext(), songInfo.audioFilename)

        binding.buttonBackToSongSelect.setOnClickListener {
            musicPlayer.stopMusic()
            findNavController().navigate(R.id.action_playFragment_to_songSelectFragment)
        }

        // Initialize jump & border after the layout has been completely set
        view.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                view.viewTreeObserver.removeOnGlobalLayoutListener(this)

                border = Border(listOf(binding.frameT, binding.frameB, binding.frameS, binding.frameE))
                Log.d("Border", "MinX: ${border}, MaxX: ${border.maxX()}, MinY: ${border.minY()}, MaxY: ${border.maxY()}")
                border.resetBorderColors()
                jump = Jump(binding.puck, border)
                jump.start()

                val beatTimes = listOf(0L, 2000, 4000, 6000, 8000, 10000, 12000, 14000, 16000, 18000, 20000, 22000,
                    24000, 25500, 27000, 28500, 30000, 31500, 33000, 34500, 36000, 37500, 39000, 40500, 42000, 43500,
                    45000, 46000, 47000, 48000, 49000, 50000, 51000, 52000, 53000, 54000, 55000,
                    56000, 56500, 57000, 57500, 58000, 58500, 59000, 59500, 60000, 60500, 61000, 61500,
                    62000, 62400, 62800, 63200, 63600, 64000, 64400, 64800, 65200, 65600, 66000, 66400,
                    66800, 67200, 67600, 68000, 68400, 68800, 69200, 69600, 70000, 70400, 70800, 71200,
                    71600, 72000, 72400, 72800, 73200, 73600, 74000, 74400, 74800, 75200, 75600, 76000,
                    76400, 76800, 77200, 77600, 78000, 78400, 78800, 79200, 79600, 80000, 80400, 80800,
                    81200, 81600, 82000, 82400, 82800, 83200, 83600, 84000, 84400, 84800, 85200, 85600,
                    86000, 86400, 86800, 87200, 87600, 88000, 88400, 88800, 89200, 89600, 90000, 90400,
                    90800, 91200, 91600, 92000, 92400, 92800, 93200, 93600, 94000, 94400, 94800, 95200,
                    95600, 96000, 96400, 96800, 97200, 97600, 98000, 98400, 98800, 99200, 99600, 100000,
                    100400, 100800, 101200, 101600, 102000, 102400, 102800, 103200, 103600, 104000, 104400, 104800,
                    105200, 105600, 106000, 106400, 106800, 107200, 107600, 108000, 108400, 108800, 109200, 109600,
                    110000, 110400, 110800, 111200, 111600, 112000, 112400, 112800, 113200, 113600, 114000, 114400,
                    114800, 115200, 115600, 116000)
                val gameController = GameController(binding.scoreTV, musicPlayer, jump, beatTimes, binding.timeTV)
                gameController.startGame()

                // Set up the puck click listener
                binding.puck.setOnClickListener {
                    gameController.puckTapped()
                    jump.invis(boolean = true)
                }
            }
        })

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