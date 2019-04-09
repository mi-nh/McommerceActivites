package com.mexpedition.web.controller;

import com.mexpedition.dao.ExpeditionDao;
import com.mexpedition.model.Expedition;
import com.mexpedition.web.exceptions.ExpeditionNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
public class ExpeditionController {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ExpeditionDao expeditionDao;


    @PostMapping(value="/Expeditions")
    public ResponseEntity<Void> addExpedition(@Valid @RequestBody Expedition expedition){

        Expedition expeditionAdded = expeditionDao.save(expedition);

        if (expeditionAdded == null) {
            return ResponseEntity.noContent().build();
        }

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                            .path("/{id}")
                            .buildAndExpand(expeditionAdded.getId())
                            .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping(value = "/Expeditions/{id}")
    public Optional<Expedition> getExpedition(@PathVariable int id) {
        Optional<Expedition> expedition = expeditionDao.findById(id);

        if (!expedition.isPresent()) {
            throw new ExpeditionNotFoundException("L'expedition id = " + id + " n'existe pas");
        }

        return expedition;
    }

    @PutMapping(value = "Expeditions")
    public void updateExpedition(@RequestBody Expedition expedition) {
        expeditionDao.save(expedition);
    }
}
