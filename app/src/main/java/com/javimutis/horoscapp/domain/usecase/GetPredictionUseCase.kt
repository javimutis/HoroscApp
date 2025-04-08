package com.javimutis.horoscapp.domain.usecase

import com.javimutis.horoscapp.domain.Repository
import javax.inject.Inject

// Caso de uso que se encarga de obtener la predicción para un signo dado.
// Sigue el principio de separación de responsabilidades: la lógica de negocio va aquí.
// Se inyecta el repositorio para poder acceder a los datos.

class GetPredictionUseCase @Inject constructor(
    private val repository: Repository // Repositorio inyectado por Hilt
) {
    // Sobrecargamos el operador invoke, así podemos usarlo como si fuera una función.
    // suspend: indica que esta función es "suspendida", ideal para usar con corrutinas.
    suspend operator fun invoke(sign: String) = repository.getPrediction(sign)
}