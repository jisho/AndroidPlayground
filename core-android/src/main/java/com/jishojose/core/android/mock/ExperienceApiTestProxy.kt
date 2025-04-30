package com.jishojose.core.android.mock

import androidx.annotation.VisibleForTesting

class ExperienceApiTestProxy
@VisibleForTesting
@Deprecated("Please use the rule ExperienceApiTestProxyRule instead of building this directly")
constructor() {
    init {
        instance = this
    }
    companion object {
        @Suppress("DEPRECATION")
        internal var instance = ExperienceApiTestProxy()
            private set
    }
}
