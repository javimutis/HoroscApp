package com.javimutis.horoscapp.ui.palmistry

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import androidx.fragment.app.Fragment
import com.javimutis.horoscapp.databinding.FragmentPalmistryBinding
import dagger.hilt.android.AndroidEntryPoint

// Anotación que le dice a Hilt que puede inyectar dependencias en este Fragment
@AndroidEntryPoint
class PalmistryFragment : Fragment() {

    companion object {
        // Constante que contiene el permiso de cámara que se solicitará
        private const val CAMERA_PERMISSION = android.Manifest.permission.CAMERA
    }


    // Variable que guarda la vista vinculada (binding)
    private var _binding: FragmentPalmistryBinding? = null

    // Esta propiedad asegura que siempre obtendrás una referencia no nula del binding
    private val binding get() = _binding!!

    // Permiso de cámara con ActivityResult API moderna.
    // Se ejecutará este bloque cuando el usuario acepte o rechace el permiso.
    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted -> // Si el permiso fue concedido
        if (isGranted) {
            startCamera() // Inicia la cámara
        } else {
            // Muestra un mensaje si el usuario no otorgó el permiso
            Toast.makeText(
                requireContext(),
                "Acepta los permisos para poder disfrutar de una experiencia mágica",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    // Método que se ejecuta cuando la vista ya está creada
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Verifica si el permiso ya está concedido
        if (checkCameraPermission()) {
            startCamera() // Si ya hay permiso, inicia la cámara directamente
        } else {
            requestPermissionLauncher.launch(CAMERA_PERMISSION) // Si no, pide el permiso
        }
    }

    // Función que configura e inicia la cámara
    private fun startCamera() {
        // Obtiene una instancia del proveedor de cámara (usando CameraX)
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        // Cuando la cámara esté lista, se ejecuta este bloque
        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Configura la vista previa de la cámara
            val preview = Preview.Builder()
                .build()
                .also {
                    // Asocia la vista de la cámara con el layout
                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
                }

            // Selecciona la cámara trasera
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                // Limpia cualquier uso anterior de la cámara
                cameraProvider.unbindAll()

                // Enlaza la cámara a este fragmento y muestra la vista previa
                cameraProvider.bindToLifecycle(this, cameraSelector, preview)
            } catch (e: Exception) {
                // En caso de error, lo muestra en los logs
                Log.e("Mutis", "Algo falló ${e.message}")
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    // Verifica si el permiso de cámara fue otorgado
    private fun checkCameraPermission(): Boolean {
        return PermissionChecker.checkSelfPermission(
            requireContext(),
            CAMERA_PERMISSION
        ) == PermissionChecker.PERMISSION_GRANTED
    }


    // Método que crea y retorna la vista del fragmento (se llama antes que onViewCreated)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla el layout usando ViewBinding
        _binding = FragmentPalmistryBinding.inflate(layoutInflater, container, false)
        return binding.root // Retorna la vista raíz
    }
}
