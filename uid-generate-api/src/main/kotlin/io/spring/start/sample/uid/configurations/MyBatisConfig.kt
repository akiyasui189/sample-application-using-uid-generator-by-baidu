package io.spring.start.sample.uid.configurations

import org.apache.ibatis.session.ExecutorType
import org.apache.ibatis.session.SqlSessionFactory
import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.SqlSessionTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import javax.sql.DataSource

@Configuration
class MyBatisConfig(val dataSource: DataSource) {

    @Bean
    fun sqlSessionFactory (): SqlSessionFactory? {
        val sqlSessionFactoryBean: SqlSessionFactoryBean = SqlSessionFactoryBean()
        sqlSessionFactoryBean.setDataSource(dataSource)
        return sqlSessionFactoryBean.`object`
    }

    @Primary
    @Bean
    fun simpleSqlSessionTemplate(): SqlSessionTemplate {
        return SqlSessionTemplate(sqlSessionFactory(), ExecutorType.SIMPLE)
    }

}