package com.emgar.click_a_tune

// This is the model in MVVM
data class SongInfo(
    val name: String,
    val artist: String,
    val duration: String,
    val backgroundImage: String, // filename of the background image
    val audioFilename: String, // filename of the audio file
    val uniqueId: Int // unique identifier for each song
)
class Repository {
    // This is long enough that it has to scroll
    // I have a limited number of song files, so I use them twice
    // Entries are distinguished with a different time value.
    // To make list manipulation work, all entries must be distinct
    private var songResources = listOf(

                SongInfo(
                    "We Won't Be Alone (feat. Laura Brehm)",
                    "Feint",
                    "3:39",
                    "wwba.jpg",
                    "Feint - We Won't Be Alone (feat. Laura Brehm).mp3",
                    0),

                SongInfo(
                "Wishing Well",
                "Juice WRLD",
                "3:22",
            "wishingwell_background.jpg",
            "wishingwell.mp3",
                    1),

        SongInfo(
            "Crossing Field",
            "LiSA",
            "4:11",
            "newBG.jpg",
            "crossingfield.mp3",
            2)


    )
    //EEE
    fun fetchData(): List<SongInfo> {
        return songResources
    }
}