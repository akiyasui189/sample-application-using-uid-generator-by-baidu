package io.spring.start.sample.uid.controllers
import io.spring.start.sample.uid.controllers.interfaces.BaseResponse
import io.spring.start.sample.uid.usecases.SampleUseCase
import org.slf4j.Logger
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/uid")
class SampleController (val useCase: SampleUseCase, val logger: Logger) {

    @PostMapping("/generator")
    fun uidGenerator() : ResponseEntity<BaseResponse> {
        val id = useCase.getUniqueId()
        return ResponseEntity.ok(BaseResponse(id))
    }

}