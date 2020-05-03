package com.example.watchlist.feature.repositories

import androidx.lifecycle.LiveData
import com.example.watchlist.feature.datamodels.db.Watchlist
import com.example.watchlist.feature.domain.dao.WatchlistDao

class WatchlistRepository(private val watchlistDao: WatchlistDao) {

    fun getAll(): LiveData<List<Watchlist>> {
        return watchlistDao.getAll()
    }

    suspend fun insert(watchlist: Watchlist) {
        watchlistDao.insert(watchlist)
    }

    suspend fun delete(watchlist: Watchlist) {
        watchlistDao.delete(watchlist)
    }
}