package jp.gr.java_conf.saka.multistopwatch.preference

class PreferenceAccessors {
    companion object Factory {
        fun newAccessor(preferenceFile: String, mode: Int): PreferenceAccessor {
            return PreferenceAccessor(preferenceFile, mode)
        }
    }
}
