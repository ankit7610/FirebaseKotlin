package com.example.firebaseauth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth

class AuthViewModel : ViewModel() {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val _authState = MutableLiveData<AuthState>()
    val authState: LiveData<AuthState> = _authState

    init {
        checkAuthStatus()
    }

    fun checkAuthStatus() {
        val currentUser = auth.currentUser
        if (currentUser != null) {
            _authState.value = AuthState.Authenticated
        } else {
            _authState.value = AuthState.Unauthenticated
        }
    }

    fun login(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Enter valid values")
            return
        }
        _authState.value = AuthState.Loading
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }

    fun signup(email: String, password: String) {
        if (email.isEmpty() || password.isEmpty()) {
            _authState.value = AuthState.Error("Enter valid values")
            return
        }
        _authState.value = AuthState.Loading
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value = AuthState.Authenticated
                } else {
                    _authState.value = AuthState.Error(task.exception?.message ?: "Something went wrong")
                }
            }
    }

    fun signout() {
        auth.signOut()
        _authState.value = AuthState.Unauthenticated
    }
}

sealed class AuthState {
    object Authenticated : AuthState()
    object Unauthenticated : AuthState()
    object Loading : AuthState()
    data class Error(val message: String) : AuthState()
}

//package com.example.firebaseauth
//
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import com.google.firebase.auth.FirebaseAuth
//
//class AuthViewModel : ViewModel()
//{
//    private val auth:FirebaseAuth = FirebaseAuth.getInstance()
//    private val _authState = MutableLiveData<AuthState>()
//    val authState : LiveData<AuthState> = _authState
//    init {
//         checkAuthStatus()
//    }
//    fun checkAuthStatus(){
//        val currentUser = auth.currentUser
//        if (currentUser != null) {
//            _authState.value = AuthState.Authenticated
//        } else {
//            _authState.value = AuthState.Unauthenticated
//        }
//    }
//    fun login(email:String,password:String){
//        if(email.isEmpty()||password.isEmpty()){
//            _authState.value=AuthState.Error("Enter Valid Values")
//            return
//        }
//        _authState.value = AuthState.Loading
//        auth.signInWithEmailAndPassword(email,password)
//            .addOnCompleteListener{task->
//                if(task.isSuccessful){
//                    _authState.value=AuthState.Authenticated
//                }
//                else{
//                    _authState.value=AuthState.Error(task.exception?.message?:"Something went wrong")
//                }
//            }
//    }
//    fun signup(email:String,password:String){
//        if(email.isEmpty()||password.isEmpty()){
//            _authState.value=AuthState.Error("Enter Valid Values")
//            return
//        }
//        _authState.value = AuthState.Loading
//        auth.createUserWithEmailAndPassword(email,password)
//            .addOnCompleteListener{task->
//                if(task.isSuccessful){
//                    _authState.value=AuthState.Authenticated
//                }
//                else{
//                    _authState.value=AuthState.Error(task.exception?.message?:"Something went wrong")
//                }
//            }
//    }
//    fun signout(){
//        auth.signOut()
//        _authState.value=AuthState.Unauthenticated
//    }
//}
//sealed class AuthState{
//    object Authenticated:AuthState()
//    object Unauthenticated:AuthState()
//    object Loading:AuthState()
//    data class Error(val message:String):AuthState()
//}