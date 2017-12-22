package com.liuyan.study.processor;

import com.liuyan.study.domain.Person;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.item.ItemProcessor;

/**
 * Created by liuyan on 2017/12/22.
 */
@Log4j2
public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    @Override
    public Person process(final Person person) throws Exception {
        final String firstName = person.getFirstName().toUpperCase();
        final String lastName = person.getLastName().toUpperCase();

        final Person transformedPerson = new Person(firstName, lastName);

        log.info("Converting (" + person + ") into (" + transformedPerson + ")");

        return transformedPerson;
    }

}