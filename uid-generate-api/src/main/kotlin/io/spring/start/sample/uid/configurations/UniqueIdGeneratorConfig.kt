package io.spring.start.sample.uid.configurations

import com.baidu.fsg.uid.impl.DefaultUidGenerator
import com.baidu.fsg.uid.worker.dao.WorkerNodeDAO
import io.spring.start.sample.uid.wrappers.DisposableWorkerIdAssignerWrapper
import org.apache.ibatis.session.ExecutorType
import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.SqlSessionTemplate
import org.mybatis.spring.annotation.MapperScan
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import javax.sql.DataSource

@Configuration
@MapperScan(value = ["com.baidu.fsg.uid"])
class UniqueIdGeneratorConfig(val dataSource: DataSource) {

    @Value("\${uid.generator.timebits:39}")
    private val timeBits = 39

    @Value("\${uid.generator.workerbits:16}")
    private val workerBits = 16

    @Value("\${uid.generator.seqbits:8}")
    private val seqBits = 8

    @Value("\${uid.generator.epochbits:2020-02-02}")
    private val epochStr: String = "2020-02-02"

    @Bean
    fun defaultUidGenerator () : DefaultUidGenerator {
        val sqlSessionFactoryBean = SqlSessionFactoryBean()
        sqlSessionFactoryBean.setDataSource(dataSource)

        val resolver = PathMatchingResourcePatternResolver()
        sqlSessionFactoryBean.setMapperLocations(*resolver.getResources("classpath*:/META-INF/mybatis/mapper/WORKER*.xml"))

        val sqlSessionTemplate = SqlSessionTemplate(sqlSessionFactoryBean.`object`, ExecutorType.BATCH)

        val dao = sqlSessionTemplate.getMapper(WorkerNodeDAO::class.java)

        val workerIdAssigner = DisposableWorkerIdAssignerWrapper(dao)

        val generator = DefaultUidGenerator()
        generator.setWorkerIdAssigner(workerIdAssigner)
        generator.setSeqBits(seqBits)
        generator.setWorkerBits(workerBits)
        generator.setTimeBits(timeBits)
        generator.setEpochStr(epochStr)
        return generator
    }
}