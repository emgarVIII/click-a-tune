package com.emgar.click_a_tune

import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth

class MainViewModel: ViewModel() {
    private var displayName = MutableLiveData("Uninitialized")
    private var email = MutableLiveData("Uninitialized")
    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> = _userName

    private fun userLogout() {
        displayName.postValue("No user")
        email.postValue("No email, no active user")
    }

    fun updateUser(name: String) {
        // XXX Write me. Update user data in view model
        val user = FirebaseAuth.getInstance().currentUser
        _userName.value = name
        if (user != null) {
            displayName.postValue(user.displayName ?: "No name")
            email.postValue(user.email ?: "No email")
        } else {
            userLogout()
        }
    }

}
