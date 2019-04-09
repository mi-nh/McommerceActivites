package com.mexpedition.dao;

import com.mexpedition.model.Expedition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpeditionDao extends JpaRepository<Expedition, Integer> {


}
