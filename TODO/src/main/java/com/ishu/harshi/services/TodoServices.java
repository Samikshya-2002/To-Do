package com.ishu.harshi.services;

import com.ishu.harshi.model.Todoitem;
import com.ishu.harshi.repositories.Todorepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class TodoServices {
    private final Todorepository todorepository;
    @Autowired
    public TodoServices(Todorepository todorepository) {
        this.todorepository = todorepository;
    }
//    private Todorepository todorepository;

    public Iterable<Todoitem> getAll(){
        return todorepository.findAll();
    }

    public Optional<Todoitem>getById(Long id){
        return todorepository.findById(id);
    }

    public Todoitem save(Todoitem todoitem)
    {
//        Long todoitemgetid=todoitem.getId();
        if(todoitem.getId() == null)
        {
            todoitem.setCreated_at(Instant.now());
        }
        todoitem.setUpdated_at(Instant.now());
        return todorepository.save(todoitem);
    }
    public void delete(Todoitem todoitem)
    {
        todorepository.delete(todoitem);
    }

}
