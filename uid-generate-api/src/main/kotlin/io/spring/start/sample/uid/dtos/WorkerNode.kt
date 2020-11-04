package io.spring.start.sample.uid.dtos

import java.sql.Timestamp
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "worker_node")
data class WorkerNode(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        val id: Long,
        @Column(name = "HOST_NAME")
        val hostName: String,
        @Column(name = "PORT")
        val port: String,
        @Column(name = "TYPE")
        val type: Int,
        @Column(name = "LAUNCH_DATE")
        val launchDate: LocalDateTime,
        @Column(name = "MODIFIED")
        val modified: Timestamp,
        @Column(name = "CREATED")
        val created: Timestamp
)