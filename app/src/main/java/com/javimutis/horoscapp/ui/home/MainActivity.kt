package com.javimutis.horoscapp.ui.home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.javimutis.horoscapp.R
import com.javimutis.horoscapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

// Anotación de Hilt para inyección de dependencias en esta actividad
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    // ViewBinding para acceder a las vistas de la interfaz sin usar findViewById
    private lateinit var binding: ActivityMainBinding

    // Controlador de navegación para gestionar los fragmentos
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge() // Habilita el modo de pantalla completa sin bordes

        binding = ActivityMainBinding.inflate(layoutInflater) // Infla el layout usando ViewBinding
        setContentView(binding.root) // Asigna la vista inflada a la actividad

        initUI() // Llama al método que inicializa la interfaz de usuario
    }

    private fun initUI() {
        initNavigation() // Inicializa la navegación de la app
    }

    private fun initNavigation() {
        // Obtiene el fragmento que gestiona la navegación dentro del contenedor de fragmentos
        val navHost: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        // Asigna el controlador de navegación al NavController
        navController = navHost.navController

        // Conecta el BottomNavigationView con el NavController para gestionar la navegación
        binding.bottomNavView.setupWithNavController(navController)
    }
}