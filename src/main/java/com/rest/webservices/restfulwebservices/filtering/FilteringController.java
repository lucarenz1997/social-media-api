package com.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RestController
public class FilteringController {


//     dynamic filtering uses MappingJacksonValue. Important side-note: need to use @JsonFilter in the class
    @GetMapping("/filtering")
    public MappingJacksonValue filtering () {
        SomeBean someBean = new SomeBean("value1", "value2", "value3");
        Set<String> filteredVariables = Set.of("field1", "field3");
        MappingJacksonValue mappingJacksonValue = getMappingJacksonValue(new MappingJacksonValue(someBean), filteredVariables);
        return mappingJacksonValue;
    }



//    we just want field2 & field3

    @GetMapping("/filtering-list")
    public MappingJacksonValue filteringList () {
        List<SomeBean> someBeans = Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("value4", "value5", "value6"));
        Set<String> filteredVariables = Set.of("field2", "field3");
        MappingJacksonValue mappingJacksonValue = getMappingJacksonValue(new MappingJacksonValue(someBeans), filteredVariables);

        return mappingJacksonValue;
    }

    private static MappingJacksonValue getMappingJacksonValue(MappingJacksonValue someBean, Set<String> filteredVariables) {
        MappingJacksonValue mappingJacksonValue = someBean;
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(filteredVariables);
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }
}
