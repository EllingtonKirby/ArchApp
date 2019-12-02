object ApplicationId {
    val id = "com.ellington.architecture"
}

object Modules {
    val app = ":app"
    val dagger = ":common:dagger"
    val mvvm = ":common:mvvm"
    val home = ":Home"
    val navigation = ":navigation"
}

object Releases {
    val versionCode = 1
    val versionName = "1.0"
}

object Versions {
    val gradle = "4.0.0-alpha01"

    val compileSdk = 28
    val minSdk = 19
    val targetSdk = 28

    val kotlin = "1.3.60-eap-25"

    val googleAuth = "16.0.1"
    val googleServices = "4.3.0"

    val firebaseAuth = "16.0.4"
    val firebaseCore = "16.0.4"

    val fabric = "1.30.0"

    val appcompat = "1.0.2"
    val design = "1.0.0"
    val cardview = "1.0.0"
    val recyclerview = "1.0.0"
    val maps = "15.0.1"
    val constraint_layout = "2.0.0-beta2"
    val material = "1.2.0-alpha02"
    val multiDex = "2.0.1"

    val ktx = "1.0.0-alpha1"

    val timber = "4.7.1"
    val rxjava = "2.2.10"
    val rxkotlin = "2.3.0"
    val rx_android = "2.0.1"
    val retrofit = "2.6.0"
    val loggingInterceptor = "4.0.0"
    val lifecycle = "2.1.0"
    val leakCanary = "2.0-alpha-2"
    val crashlytics = "2.10.1"
    val glide = "4.10.0"
    val okhttp = "3.9.1"

    val playCore = "1.6.1"

    val junit = "4.12"
    val androidXjunit = "1.1.1"
    val assertjCore = "3.12.2"
    val mockitoKotlin = "2.1.0"
    val mockitoInline = "3.0.0"

    val dagger = "2.16"
}

object Libraries {
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    val ktx = "androidx.core:core-ktx:${Versions.ktx}"

    val maps = "com.google.android.gms:play-services-maps:${Versions.maps}"

    val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"
    val rxkotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxkotlin}"
    val rxAndroid = "io.reactivex.rxjava2:rxandroid:${Versions.rx_android}"

    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val rxjavaAdapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    val loggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}"
    val retrofitGsonAdapter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val okHttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"

    val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
    val lifecycleKotlinExtensions =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    val lifecycleReactiveStreams = "androidx.lifecycle:lifecycle-reactivestreams:${Versions.lifecycle}"

    val leakCanaryAndroid = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"

    val crashlytics = "com.crashlytics.sdk.android:crashlytics:${Versions.crashlytics}"

    val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    val daggerAnnotationProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
}

object SupportLibraries {
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val design = "com.google.android.material:material:${Versions.design}"
    val cardview = "androidx.cardview:cardview:${Versions.cardview}"
    val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerview}"
    val constraintLayout =
        "com.android.support.constraint:constraint-layout:${Versions.constraint_layout}"
    val materialComponents = "com.google.android.material:material:${Versions.material}"
    val multiDex = "androidx.multidex:multidex:${Versions.multiDex}"
}

object GoogleLibraries {
    val auth = "com.google.android.gms:play-services-auth:${Versions.googleAuth}"
    val playCore = "com.google.android.play:core:${Versions.playCore}"
}

object FirebaseLibraries {
    val auth = "com.google.firebase:firebase-auth:${Versions.firebaseAuth}"
    val core = "com.google.firebase:firebase-core:${Versions.firebaseCore}"
}

object TestLibraries {
    val junit = "junit:junit:${Versions.junit}"
    val androidXJunit = "androidx.test.ext:junit:1.1.1"
    val assertjCore = "org.assertj:assertj-core:${Versions.assertjCore}"
    val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    val mockitoInline = "org.mockito:mockito-inline:${Versions.mockitoInline}"
    val lifecycleTesting = "androidx.arch.core:core-testing:${Versions.lifecycle}"
}

