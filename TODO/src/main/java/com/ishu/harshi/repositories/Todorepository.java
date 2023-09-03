package com.ishu.harshi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ishu.harshi.model.Todoitem;
@Repository

public interface Todorepository extends JpaRepository<Todoitem,Long> {
}
