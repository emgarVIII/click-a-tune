//import be.tarsos.dsp.AudioDispatcher
//import be.tarsos.dsp.io.android.AudioDispatcherFactory
//import be.tarsos.dsp.beatroot.BeatRootOnsetEventHandler
//import be.tarsos.dsp.beatroot.BeatRootOnsetEventHandler.BeatHandler
//import be.tarsos.dsp.io.jvm.AudioDispatcherFactory
//import be.tarsos.dsp.io.jvm.
//
//class AudioAnalyzer(private val onBeatDetected: () -> Unit) {
//
//    fun start() {
//        // You might need to modify this to fit where you get your audio from
//        val dispatcher = AudioDispatcherFactory.fromDefaultMicrophone(22050, 1024, 0)
//
//        val beatDetector = BeatRootOnsetEventHandler(object : BeatHandler {
//            override fun handleBeat(time: Double, salience: Double) {
//                onBeatDetected() // This will be called on every beat detected
//            }
//        })
//
//        dispatcher.addAudioProcessor(beatDetector)
//        Thread(dispatcher, "Audio Dispatcher").start()
//    }
//}
