
-keepclassmembers class * {
    @kotlinx.serialization.Serializable *;
}

-keep class com.yourpackage.shared.** { *; }
