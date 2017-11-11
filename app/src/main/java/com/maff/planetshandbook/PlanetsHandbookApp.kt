package com.maff.planetshandbook

import android.app.Application

import com.maff.planetshandbook.data.Repository
import com.maff.planetshandbook.data.JsonRepository

import timber.log.Timber

/**
 * Created by maff on 10/31/2017.
 */

class PlanetsHandbookApp : Application()
{
    companion object
    {
        var repository: Repository? = null
            private set
    }

    override fun onCreate()
    {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        repository = JsonRepository(resources.openRawResource(R.raw.data))
    }
}
