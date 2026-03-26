package com.example.composenavigationapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.composenavigationapp.ui.theme.ComposeNavigationAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeNavigationAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorResource(id = R.color.olive)
                ) {
                    AppNavigation()
                }
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "screen_a") {
        composable("screen_a") {
            ScreenA(navController = navController)
        }
        composable("screen_b?message={message}&imageName={imageName}") {
            val message = it.arguments?.getString("message")
            val imageName = it.arguments?.getString("imageName")
            ScreenB(navController = navController, message = message, imageName = imageName)
        }
        composable("screen_c?message={message}"){
            val message = it.arguments?.getString("message")
            ScreenC(navController=navController, message = message)
        }
    }
}

@Composable
fun ScreenA(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo Blackjack Records",
            modifier = Modifier
                .size(120.dp)
                .padding(bottom = 16.dp)
        )

        Text(text = "Bem-vindo a Blackjack Records!")
        Button(onClick = { navController.navigate("screen_b?message=Disco Akuma no Uta&imageName=disco") },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.red)
            )) {
            Text("Ver nossos produtos")
        }
        Button(onClick = { navController.navigate("screen_b?message=Email: suporte@blackjack.com") },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.red)
            )) {
            Text("Informações para contato")
        }
    }
}

@Composable
fun ScreenB(navController: NavController, message: String?, imageName: String?) {

    val context = androidx.compose.ui.platform.LocalContext.current

    val imageResId = imageName?.let {
        context.resources.getIdentifier(it, "drawable", context.packageName)
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        if (imageResId != null && imageResId != 0) {
            Image(
                painter = painterResource(id = imageResId),
                contentDescription = "imagem do produto",
                modifier = Modifier
                    .size(150.dp)
                    .padding(bottom = 16.dp)
            )
        }

        message?.let { Text(text = " $it") }

        Button(onClick = { navController.navigate("screen_c?message=Serviço de Pagamento Indisponível") },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.red)
            )) {
            Text("Comprar")
        }

        Button(onClick = { navController.popBackStack() },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.red)
            )) {
            Text("Voltar para home")
        }
    }
}

@Composable
fun ScreenC(navController: NavController, message: String?) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        message?.let { Text(text = " $it") }

        Button(onClick = { navController.popBackStack() },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.red)
            )) {
            Text("Voltar")
        }
    }
}



