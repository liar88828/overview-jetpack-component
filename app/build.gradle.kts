plugins {
	alias(libs.plugins.android.application)
	alias(libs.plugins.kotlin.android)
	alias(libs.plugins.kotlin.compose)
	kotlin("kapt")
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

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
	dependencies {
		implementation(libs.androidx.room.ktx)
		//noinspection KaptUsageInsteadOfKsp
		kapt(libs.androidx.room.compiler)
		implementation(libs.androidx.room.runtime)
	}
	// LiveData
	implementation(libs.androidx.lifecycle.livedata.ktx)
// permission handle
	implementation(libs.accompanist.permissions)
//	navigation
	implementation(libs.androidx.lifecycle.runtime.compose)
	implementation(libs.androidx.navigation.compose)
	// DataStore
	implementation(libs.androidx.datastore.preferences)
	// Hilt
	implementation(libs.dagger.hilt.android)
	kapt(libs.hilt.compiler)
//	Icon
	implementation(libs.androidx.material.icons.extended)
//
	implementation(libs.retrofit)
	implementation(libs.converter.gson)
	implementation(libs.okhttp)
	implementation(libs.logging.interceptor)
	// Coil
	implementation(libs.coil.compose)
}