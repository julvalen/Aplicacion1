package com.example.aplicacion1

import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material.TextField
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Surface



data class Contact(val name: String, val phoneNumber: String)
class Aplicacion :ComponentActivity(){
    override fun onCreate (saveInstanceState: Bundle?) {
        super.onCreate(saveInstanceState)
        setContent { 
            MaterialTheme{
                ContactListApp()
            }
        }
    }
}


@Composable
fun ContactListApp() {

    var searchText by remember { mutableStateOf("") }
    val contactos = listOf(
        Contact("Juan Pérez", "555-1234"),
        Contact("María López", "555-5678"),
        Contact("Pedro Ramirez", "555-9876"),
        Contact("Susana Guerra", "555-1234"),
        Contact("Pedro Salazar", "555-9034"),
        Contact("Esteban Guerrero", "555-8943"),
        
    )

    Column(modifier = Modifier.fillMaxSize()) {
        // Barra de búsqueda
        TextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Buscar Contacto") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        // Lista de contactos
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            items(contactos.filter {
                it.name.contains(searchText, ignoreCase = true)
            }) { contact ->
                ContactItem(contact)
            }
        }
    }
}

@Composable
fun ContactItem(contact: Contact) {
    val  yourCustomTypography = Typography(
        bodyLarge = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.15.sp
        ),
        bodyMedium = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 0.15.sp
        ),
        bodySmall = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            letterSpacing = 0.15.sp
        ),
    )
    MaterialTheme(
        typography = yourCustomTypography
    ) {
    }
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = contact.name,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = contact.phoneNumber,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

