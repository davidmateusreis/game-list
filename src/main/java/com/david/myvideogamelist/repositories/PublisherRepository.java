package com.david.myvideogamelist.repositories;

import com.david.myvideogamelist.models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    @Query("select d from Publisher d where lower(d.name) like lower(concat(:term, '%'))") //busca no db, autocomplete
    public List<Publisher> searchByName(@Param("term") String term);
}
