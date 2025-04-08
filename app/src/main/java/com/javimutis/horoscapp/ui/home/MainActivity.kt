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

    // Método onCreate que se ejecuta cuando la actividad es creada
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge() // Habilita el modo de pantalla completa sin bordes (pantalla más grande)

        // Inflar el layout de la actividad usando ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // Asignar la vista inflada a la actividad

        initUI() // Llamada a un método para inicializar la interfaz de usuario
    }

    // Método para inicializar la interfaz de usuario
    private fun initUI() {
        initNavigation() // Inicializa la navegación de la app
    }

    // Método para inicializar la navegación entre fragmentos
    private fun initNavigation() {
        // Encuentra el fragmento que gestiona la navegación en el contenedor de fragmentos
        val navHost: NavHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment

        // Asigna el controlador de navegación al NavController
        navController = navHost.navController

        // Configura el BottomNavigationView para que interactúe con el NavController y gestione la navegación
        binding.bottomNavView.setupWithNavController(navController)
    }
}