package com.gcdominium

import android.app.Application
import java.lang.IllegalStateException

class GCDApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {
        // singleton
        private var appInstance: GCDApplication?  = null
        fun getInstance(): GCDApplication {
            if (appInstance == null) {
                throw IllegalStateException("Configurar application no Android Manifest")
            }
            return appInstance!!
        }
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}