// Paquete donde se encuentra esta clase (estructura del proyecto)
package com.javimutis.horoscapp.ui.home

// Importaciones necesarias para hacer testing con Espresso (clics, vistas, etc.)
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.withId

// Reglas y soporte para test con Activities (pantallas)
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4

// Importamos la activity que vamos a testear y otra activity que será abierta al hacer click
import com.javimutis.horoscapp.R
import com.javimutis.horoscapp.ui.detail.HoroscopeDetailActivity

// Importamos lo necesario para integrar Hilt con nuestros tests
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest

// Importamos JUnit para manejar los tests
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

// Esta anotación indica que los tests se ejecutan con el runner de AndroidJUnit4
@RunWith(AndroidJUnit4::class)
// Esta anotación activa el soporte de Hilt para inyecciones en pruebas
@HiltAndroidTest
class MainActivityTest {

    // Regla que configura Hilt antes de correr los tests
    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    // Regla que inicia MainActivity antes de cada test
    @get:Rule(order = 1)
    var mainActivityRule = ActivityScenarioRule(MainActivity::class.java)

    // Este método se ejecuta antes de cada test
    @Before
    fun setUp() {
        // Inyecta las dependencias con Hilt
        hiltRule.inject()

        // Inicializa el sistema de Intents de Espresso para poder verificar cambios de pantalla
        Intents.init()
    }

    // Este método se ejecuta después de cada test
    @After
    fun tearDown() {
        // Libera el sistema de Intents para no interferir con otros tests
        Intents.release()
    }

    // Este test verifica que se puede hacer click en el fragmento de "suerte"
    @Test
    fun when_mainactivity_is_created_then_open_luckfragment() {
        // Busca la vista con ID luckFragment y simula un clic
        onView(withId(R.id.luckFragment)).perform(click())
    }

    // Este test verifica que, al hacer click en un signo del horóscopo, se abre la pantalla de detalle
    @Test
    fun when_horoscope_is_selected_then_open_detail() {
        // Busca el RecyclerView con los signos y simula clic en el primer elemento (posición 0)
        onView(withId(R.id.rvHoroscope)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click())
        )
        // Verifica que se haya abierto la pantalla HoroscopeDetailActivity
        intended(hasComponent(HoroscopeDetailActivity::class.java.name))
    }
}
