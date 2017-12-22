package com.liuyan.study.config;

import com.liuyan.study.domain.Person;
import com.liuyan.study.job.JobCompletionNotificationListener;
import com.liuyan.study.mapper.PersonMapper;
import com.liuyan.study.processor.PersonItemProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.sql.DataSource;

/**
 * Created by liuyan on 2017/12/22.
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    @Autowired
    public MongoTemplate mongoTemplate;

    @Bean
    public JdbcCursorItemReader<Person> reader() {
        JdbcCursorItemReader<Person> itemReader = new JdbcCursorItemReader<Person>();
        itemReader.setDataSource(dataSource);
        itemReader.setSql("select idp, last_name, first_name from people");
        itemReader.setRowMapper(new PersonMapper());
        return itemReader;
    }

    @Bean
    public PersonItemProcessor processor() {
        return new PersonItemProcessor();
    }

    @Bean
    MongoItemWriter<Person> writer() {
        MongoItemWriter<Person> itemWriter = new MongoItemWriter<Person>();
        itemWriter.setTemplate(mongoTemplate);
        itemWriter.setCollection("branch");
        return itemWriter;
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("step")
                .<Person, Person>chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step())
                .end()
                .build();
    }

}
