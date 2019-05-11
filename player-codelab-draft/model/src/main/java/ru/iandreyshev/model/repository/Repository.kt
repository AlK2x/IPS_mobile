package ru.iandreyshev.model.repository

import ru.iandreyshev.model.R

class Repository : IRepository {

    private val mSongs: List<Song> = listOf(
            Song(0, "Grand Theft Auto Vice City - Mission completed", R.raw.mission_completed),
            Song(1, "Bomfunk Mc's - Freestyler", R.raw.freestyler)
    )

    private val mImages: Map<Long, String> = mapOf(
        0L to "https://images.g2a.com/newlayout/323x433/1x1x0/49d265303aa4/5912a1e2ae653a2fb1529963",
        1L to "https://upload.wikimedia.org/wikipedia/ru/thumb/8/8f/Freestyler.jpg/270px-Freestyler.jpg"
    )

    override fun getAllSongs(): List<ISong> {
        return mSongs
    }

    override fun getSongById(id: Long): ISong? {
        return mSongs.first { song -> song.id == id }
    }

    override fun getPictureUrl(id: Long): String? {
        return mImages[id]
    }
}
