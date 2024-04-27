import android.content.Context
import android.media.MediaPlayer
import android.util.Log

class MusicPlayer(private val context: Context, private val audioFilename: String) {
    private var mediaPlayer: MediaPlayer? = null

    fun startMusic() {
        if (mediaPlayer == null) {
            try {
                val assetFileDescriptor = context.assets.openFd("music/$audioFilename")
                mediaPlayer = MediaPlayer().apply {
                    setDataSource(assetFileDescriptor.fileDescriptor, assetFileDescriptor.startOffset, assetFileDescriptor.length)
                    prepare()
                    start()
                    Log.d("MusicPlayer", "Music started: $audioFilename")
                }

            } catch (e: Exception) {
                Log.e("MusicPlayer", "Error playing music", e)
            }
        } else {
            Log.d("MusicPlayer", "Music is already playing")
        }
    }

    fun stopMusic() {
        if (mediaPlayer?.isPlaying == true) {
            mediaPlayer?.stop()
            Log.d("MusicPlayer", "Music stopped")
        }
        mediaPlayer?.release()
        mediaPlayer = null
    }

    fun getCurrentPosition(): Int {
        return mediaPlayer?.currentPosition ?: 0
    }
}





