plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.kotlin.compose)
	kotlin("kapt")//  version "2.0.0"
	id("kotlin-kapt")
	id("com.google.dagger.hilt.android")
}

android {
	namespace = "com.tutor.overview_jetpack_component"
	compileSdk = 34

	defaultConfig {
		applicationId = "com.tutor.overview_jetpack_component"
		minSdk = 33
		targetSdk = 34
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner.HiltTestRunner"
		vectorDrawables {
			useSupportLibrary = true
		}
	}

	buildTypes {
		release {
			isMinifyEnabled = false
			proguardFiles(
				getDefaultProguardFile("proguard-android-optimize.txt"),
				"proguard-rules.pro"
			)
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions {
		jvmTarget = "1.8"
	}
	buildFeatures {
		compose = true
	}
}

dependencies {
	implementation(libs.androidx.core.ktx)
	implementation(libs.androidx.lifecycle.runtime.ktx)
	implementation(libs.androidx.activity.compose)
	implementation(platform(libs.androidx.compose.bom))
	implementation(libs.androidx.ui)
	implementation(libs.androidx.ui.graphics)
	implementation(libs.androidx.ui.tooling.preview)
	implementation(libs.androidx.material3)
	implementation(libs.androidx.runtime.livedata)
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(libs.androidx.ui.test.junit4)
	debugImplementation(libs.androidx.ui.tooling)
	debugImplementation(libs.androidx.ui.test.manifest)

	// Room Database
	implementation(libs.androidx.room.ktx)
	kapt(libs.androidx.room.compiler) // @Note: Kapt is used for annotation processing
	implementation(libs.androidx.room.runtime)

	// LiveData
	implementation(libs.androidx.lifecycle.livedata.ktx)

	// Permissions Handling
	implementation(libs.accompanist.permissions)

	// Navigation
	implementation(libs.androidx.lifecycle.runtime.compose)
	implementation(libs.androidx.navigation.compose)

	// DataStore
	implementation(libs.androidx.datastore.preferences)

	// Dependency Injection (Hilt)
	//	implementation(libs.dagger.hilt.android)
	//	kapt(libs.hilt.compiler)
	//	kapt(libs.androidx.hilt.compiler)
	//	implementation(libs.androidx.hilt.lifecycle.viewmodel)
	//	implementation(libs.androidx.hilt.navigation.compose)
	// Dagger - Hilt
	implementation(libs.dagger.hilt.android)
	kapt(libs.hilt.compiler)
	kapt(libs.androidx.hilt.compiler)
	implementation(libs.androidx.hilt.navigation.compose)
//
	androidTestImplementation(libs.hilt.android.testing)
	kaptAndroidTest(libs.hilt.android.compiler)

	// Icons
	implementation(libs.androidx.material.icons.extended)

	// Networking (Retrofit, OkHttp, Gson)
	implementation(libs.retrofit)
	implementation(libs.converter.gson)
	implementation(libs.okhttp)
	implementation(libs.logging.interceptor)

	// Image Loading (Coil)
	implementation(libs.coil.compose)

	// Local Tests
	testImplementation(libs.junit)
	testImplementation(libs.truth)
	testImplementation(libs.androidx.core)
	testImplementation(libs.androidx.core.testing)
	testImplementation(libs.kotlinx.coroutines.test)
	testImplementation(libs.mockwebserver)
	testImplementation(libs.mockk)
	debugImplementation(libs.ui.test.manifest)

	// Instrumented Tests
//	androidTestImplementation(libs.core.ktx)
//	androidTestImplementation(libs.truth)
//	androidTestImplementation(libs.mockwebserver)
//	androidTestImplementation(libs.mockk.android)
//
//	androidTestImplementation(libs.androidx.espresso.core)
//	androidTestImplementation(libs.androidx.junit)
//	androidTestImplementation(libs.androidx.ui.test.junit4)
//	androidTestImplementation(platform(libs.androidx.compose.bom))
//	androidTestImplementation(libs.androidx.core.testing)
//	androidTestImplementation(libs.androidx.runner)
//
//	androidTestImplementation(libs.kotlinx.coroutines.test)
}
// Allow references to generated code
kapt {
	correctErrorTypes = true
}