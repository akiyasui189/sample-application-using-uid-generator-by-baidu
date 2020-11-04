package io.spring.start.sample.uid.usecases

import io.spring.start.sample.uid.services.SampleService
import org.springframework.stereotype.Component

@Component
class SampleUseCase(val service: SampleService) {
    fun getUniqueId() : String {
        return service.getUniqueId()
    }
}