package com.david.myvideogamelist.repositories;

import com.david.myvideogamelist.models.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {

}
