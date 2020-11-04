package io.spring.start.sample.uid.services

import io.spring.start.sample.uid.repositories.SampleRepository
import org.springframework.stereotype.Service

@Service
class SampleService (val repository: SampleRepository) {

    fun getUniqueId() : String {
        return repository.getUniqueHexId()
    }

}