package com.example.firebaseauth.pages

import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.firebaseauth.AuthViewModel
@Composable
fun LoginPage(modifier: Modifier = Modifier,navHostController: NavHostController,authViewModel: AuthViewModel){
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement. Center,
        horizontalAlignment = Alignment.CenterHorizontally){
                Text(text = "Login Page", fontSize = 32.sp)
    Spacer (modifier = Modifier .height (16.dp))
    OutlinedTextField(
        value = email,
        onValueChange = {
            email = it
        },
        label = {
            Text(text = "Email")
        })
        Spacer (modifier = Modifier .height (8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(text = "Password")
            })
        Spacer (modifier = Modifier .height (16.dp))
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Login")
        }
        Spacer (modifier = Modifier .height (8.dp))
        TextButton(onClick = {
            navHostController.navigate("signup")
        }) {
            Text(text = "Don't have an account,Signup")
        }
        }
}