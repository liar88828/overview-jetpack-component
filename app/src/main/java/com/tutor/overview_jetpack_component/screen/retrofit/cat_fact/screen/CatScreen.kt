package com.tutor.overview_jetpack_component.screen.retrofit.cat_fact.screen

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tutor.overview_jetpack_component.screen.retrofit.cat_fact.model.CatFactModel
import com.tutor.overview_jetpack_component.screen.retrofit.cat_fact.utils.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

@SuppressLint("UnrememberedMutableState")
@Composable
fun CatScreen(
	modifier: Modifier = Modifier
) {
	val fact = mutableStateOf(CatFactModel())
//	var fact by remember { mutableStateOf(CatFactModel()) }
	val context = LocalContext.current
	val scope = rememberCoroutineScope()

	fun sendRequest() {
		scope.launch() {
			val response = try {
				RetrofitInstance.api.getCatFact()
			} catch (e: HttpException) {
				Toast.makeText(context, "An unknown error occured", Toast.LENGTH_LONG)
					.show()
				return@launch
			} catch (e: Exception) {
				Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show()
				return@launch
			}
			if (response.isSuccessful && response.body() is CatFactModel && response.body() != null) {
				withContext(Dispatchers.Main) {
					fact.value = response.body()!!
				}
			}
		}
	}

	LaunchedEffect(key1 = true) {
		sendRequest()
	}
//		scope.launch(Dispatchers.IO) {
//			val response = try {
//				RetrofitInstance.api.getCatFact()
//			} catch (e: HttpException) {
//				Toast.makeText(context, "An unknown error occured", Toast.LENGTH_LONG).show()
//				return@launch
//			} catch (e: Exception) {
//				Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG).show()
//				return@launch
//			}
//			if (
//				response.isSuccessful && response.body() is CatFactModel && response.body() != null) {
//				withContext(Dispatchers.Main) {
//					fact.value = response.body()!!
//				}
//			}
//		}
//	}
	Scaffold(
		floatingActionButton = {
			FloatingActionButton(onClick = {
				sendRequest()
			}) {
				Icon(
					imageVector = Icons.Default.Refresh,
					contentDescription = "Icon Add"
				)
			}
		},
		modifier = modifier.fillMaxSize(),
		containerColor = MaterialTheme.colorScheme.background,
		contentColor = MaterialTheme.colorScheme.onBackground,
	) { paddingValue ->
		Surface(
			modifier
				.padding(paddingValue)
				.fillMaxSize()
		) {
			MyFactCatScreen(modifier, fact.value)
		}
	}

}

@Composable
private fun MyFactCatScreen(
	modifier: Modifier = Modifier,
	fact: CatFactModel
) {
	Column(
		modifier.fillMaxWidth(),
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Text(
			text = "Cat fact : ",
			Modifier.padding(25.dp), fontSize = 26.sp
		)
		Text(
			text = fact.fact,
			Modifier.padding(25.dp),
			fontSize = 30.sp,
			fontWeight = FontWeight.Bold,
			lineHeight = 50.sp,
			textAlign = TextAlign.Center
		)
		Text(text = fact.length.toString())
	}
}

@Preview(showBackground = true)
@Composable
private fun CatScreenPrev() {
	CatScreen()
}