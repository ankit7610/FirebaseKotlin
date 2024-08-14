package com.example.firebaseauth.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavHostController
import com.example.firebaseauth.AuthViewModel
import com.example.firebaseauth.AuthState

@Composable
fun SignUpPage(modifier: Modifier = Modifier, navHostController: NavHostController, authViewModel: AuthViewModel) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val authState by authViewModel.authState.observeAsState()

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "SignUp Page", fontSize = 32.sp)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text(text = "Email") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text(text = "Password") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { authViewModel.signup(email, password) }) {
            Text(text = "Create Account")
        }
        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = { navHostController.navigate("login") }) {
            Text(text = "Already have an account? Login")
        }

        // Display auth state message
        authState?.let {
            Spacer(modifier = Modifier.height(8.dp))
            when (it) {
                is AuthState.Loading -> Text(text = "Loading...", fontSize = 18.sp)
                is AuthState.Authenticated -> Text(text = "Signup Successful!", fontSize = 18.sp)
                is AuthState.Error -> Text(text = "Error: ${it.message}", fontSize = 18.sp)
                is AuthState.Unauthenticated -> Text(text = "Unauthenticated", fontSize = 18.sp)
            }
        }
    }
}

//package com.example.firebaseauth.pages
//
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.material3.Button
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.material3.TextButton
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.compose.runtime.livedata.observeAsState
//import androidx.navigation.NavHostController
//import com.example.firebaseauth.AuthViewModel
//
//@Composable
//fun SinUpPage(modifier: Modifier = Modifier, navHostController: NavHostController, authViewModel: AuthViewModel){
//    var email by remember {
//        mutableStateOf("")
//    }
//    var password by remember {
//        mutableStateOf("")
//    }
//    val authState by authViewModel.authState.observeAsState()
//    Column(
//        modifier = modifier.fillMaxSize(),
//        verticalArrangement = Arrangement. Center,
//        horizontalAlignment = Alignment.CenterHorizontally){
//        Text(text = "SignUp Page", fontSize = 32.sp)
//        Spacer (modifier = Modifier .height (16.dp))
//        OutlinedTextField(
//            value = email,
//            onValueChange = {
//                email = it
//            },
//            label = {
//                Text(text = "Email")
//            })
//        Spacer (modifier = Modifier .height (8.dp))
//        OutlinedTextField(
//            value = password,
//            onValueChange = {
//                password = it
//            },
//            label = {
//                Text(text = "Password")
//            })
//        Spacer (modifier = Modifier .height (16.dp))
//        Button(onClick = {
//            authViewModel.signup(email,password)
//        }) {
//            Text(text = "Create Account")
//        }
//        Spacer (modifier = Modifier .height (8.dp))
//        TextButton(onClick = {
//            navHostController.navigate("login")
//        }) {
//            Text(text = "Already, have an account,Login")
//        }
//    }
//}