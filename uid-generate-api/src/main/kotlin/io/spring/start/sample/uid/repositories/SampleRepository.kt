package io.spring.start.sample.uid.repositories

import com.baidu.fsg.uid.impl.DefaultUidGenerator
import org.springframework.stereotype.Repository

@Repository
class SampleRepository(val generator: DefaultUidGenerator) {
    fun getUniqueId() : Long {
        return generator.uid
    }

    fun getUniqueHexId() : String {
        return String.format("%016x", getUniqueId())
    }
}