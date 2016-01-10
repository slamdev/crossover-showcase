package com.dev.frontend.services;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

import java.lang.reflect.Array;
import java.net.URI;
import java.util.List;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.dev.frontend.panels.ComboBoxItem;
import com.dev.rest.model.BaseDto;
import com.dev.rest.model.Reference;

public abstract class BaseService {

    @SuppressWarnings("unchecked")
    private static Class<BaseDto[]> arrayType(Class<? extends BaseDto> type) {
        return (Class<BaseDto[]>) Array.newInstance(type, 0).getClass();
    }

    private static ComboBoxItem toComboBoxItem(Reference reference) {
        ComboBoxItem item = new ComboBoxItem();
        item.setKey(reference.getCode());
        item.setValue(reference.getName());
        return item;
    }

    public boolean delete(String code) {
        new RestTemplate().delete(uri("{code}", code));
        return true;
    }

    public BaseDto get(String code) {
        return new RestTemplate().getForObject(uri("{code}", code), detailsType());
    }

    public List<BaseDto> getAll() {
        BaseDto[] records = new RestTemplate().getForObject(uri(""), arrayType(rowType()));
        return asList(records);
    }

    public List<ComboBoxItem> getAllReferences() {
        Reference[] references = new RestTemplate().getForObject(uri("references"), Reference[].class);
        return stream(references).map(BaseService::toComboBoxItem).collect(toList());
    }

    public BaseDto save(BaseDto details) {
        return new RestTemplate().postForObject(uri(""), details, detailsType());
    }

    protected abstract Class<? extends BaseDto> detailsType();

    protected abstract String endpoint();

    protected abstract Class<? extends BaseDto> rowType();

    protected URI uri(String path, Object... values) {
        UriComponentsBuilder target = UriComponentsBuilder.fromHttpUrl("http://localhost:8080");
        return target.path(endpoint()).path("/").path(path).buildAndExpand(values).encode().toUri();
    }
}
