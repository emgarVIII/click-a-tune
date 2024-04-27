import android.content.Context
import android.media.MediaPlayer

class MusicPlayer(private val context: Context, private val audioFilename: String) {
    private var mediaPlayer: MediaPlayer? = null

    fun startMusic() {
        try {
            val assetFileDescriptor = context.assets.openFd("music/$audioFilename")
            mediaPlayer = MediaPlayer().apply {
                setDataSource(assetFileDescriptor.fileDescriptor, assetFileDescriptor.startOffset, assetFileDescriptor.length)
                prepare()
                start()
            }
        } catch (e: Exception) {
            e.printStackTrace() // Print stack trace if there is an error
        }
    }

    fun stopMusic() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
