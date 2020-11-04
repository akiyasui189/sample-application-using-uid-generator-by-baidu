package io.spring.start.sample.uid.wrappers

import com.baidu.fsg.uid.utils.DockerUtils
import com.baidu.fsg.uid.utils.NetUtils
import com.baidu.fsg.uid.worker.WorkerIdAssigner
import com.baidu.fsg.uid.worker.WorkerNodeType
import com.baidu.fsg.uid.worker.dao.WorkerNodeDAO
import com.baidu.fsg.uid.worker.entity.WorkerNodeEntity
import org.springframework.transaction.annotation.Transactional

open class DisposableWorkerIdAssignerWrapper(val dao: WorkerNodeDAO) : WorkerIdAssigner {
    /**
     * Assign worker id base on database.
     *
     *
     * If there is host name & port in the environment, we considered that the node runs in Docker container<br></br>
     * Otherwise, the node runs on an actual machine.
     *
     * @return assigned worker id
     */
    @Transactional
    override fun assignWorkerId(): Long {
        // build worker node entity
        val workerNodeEntity = buildWorkerNode()

        // add worker node for new (ignore the same IP + PORT)
        dao.addWorkerNode(workerNodeEntity)

        // get worker node
        val storedWorkerNodeEntity: WorkerNodeEntity = dao.getWorkerNodeByHostPort(workerNodeEntity.hostName, workerNodeEntity.port)
        return storedWorkerNodeEntity.id
    }

    /**
     * Build worker node entity by IP and PORT
     */
    private fun buildWorkerNode(): WorkerNodeEntity {
        val workerNodeEntity = WorkerNodeEntity()
        if (DockerUtils.isDocker()) {
            workerNodeEntity.type = WorkerNodeType.CONTAINER.value()
            workerNodeEntity.hostName = DockerUtils.getDockerHost()
            workerNodeEntity.port = DockerUtils.getDockerPort()
        } else {
            workerNodeEntity.type = WorkerNodeType.ACTUAL.value()
            workerNodeEntity.hostName = NetUtils.getLocalAddress()
            workerNodeEntity.port = System.currentTimeMillis().toString() + "-" + (1..99999).random()
        }
        return workerNodeEntity
    }

}