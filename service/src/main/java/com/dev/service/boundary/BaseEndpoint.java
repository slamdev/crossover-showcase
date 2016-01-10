package com.dev.service.boundary;

import static java.util.stream.Collectors.toList;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dev.rest.model.BaseDto;
import com.dev.rest.model.Reference;
import com.dev.service.entity.BaseEntity;

public abstract class BaseEndpoint<ENTITY extends BaseEntity, DETAILS extends BaseDto, ROW extends BaseDto> {

    @RequestMapping(value = "{code}", method = DELETE)
    public void delete(@PathVariable String code) {
        getRepository().delete(code);
    }

    @RequestMapping(value = "{code}", method = GET)
    public DETAILS get(@PathVariable String code) {
        return convertToDetails(getRepository().getOne(code));
    }

    @RequestMapping(value = "", method = GET)
    public List<ROW> getAll() {
        return getRepository().findAll().stream().map(this::convertToRow).collect(toList());
    }

    @RequestMapping(value = "references", method = GET)
    public List<Reference> getAllReferences() {
        return getRepository().findAll().stream().map(this::reference).collect(toList());
    }

    @RequestMapping(value = "", method = POST)
    public DETAILS save(@RequestBody DETAILS dto) {
        return convertToDetails(getRepository().saveAndFlush(convertToEntity(dto)));
    }

    protected abstract DETAILS convertToDetails(ENTITY entity);

    protected abstract ENTITY convertToEntity(DETAILS dto);

    protected abstract ROW convertToRow(ENTITY entity);

    protected abstract JpaRepository<ENTITY, String> getRepository();

    protected abstract Reference reference(ENTITY entity);
}