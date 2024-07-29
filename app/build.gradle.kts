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
	testImplementation(libs.junit)
	androidTestImplementation(libs.androidx.junit)
	androidTestImplementation(libs.androidx.espresso.core)
	androidTestImplementation(platform(libs.androidx.compose.bom))
	androidTestImplementation(libs.androidx.ui.test.junit4)
	debugImplementation(libs.androidx.ui.tooling)
	debugImplementation(libs.androidx.ui.test.manifest)
	// Room Database
	dependencies {
		val roomVersion = "2.6.1"
		implementation("androidx.room:room-ktx:$roomVersion")
		kapt("androidx.room:room-compiler:$roomVersion")
		implementation("androidx.room:room-runtime:$roomVersion")
	}
	// LiveData
	implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")
// permission handle
	implementation(
		"com.google.accompanist:accompanist-permissions:0.32.0"
	)
//	navigation
	implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")
	dependencies {
		val nav_version = "2.7.7"
		implementation("androidx.navigation:navigation-compose:$nav_version")
	}
	// DataStore
	implementation("androidx.datastore:datastore-preferences:1.1.1")
	// Hilt
	implementation("com.google.dagger:hilt-android:2.48.1")
	kapt("com.google.dagger:hilt-compiler:2.48.1")
}