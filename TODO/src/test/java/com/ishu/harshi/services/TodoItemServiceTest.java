package com.ishu.harshi.services;

import com.ishu.harshi.model.Todoitem;
import com.ishu.harshi.repositories.Todorepository;
import jakarta.validation.constraints.NotBlank;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TodoItemServiceTest {

    @Autowired
    private TodoServices service;

    @Autowired
    private Todorepository repository;

    @AfterEach
    void deleteAllItems() {
        repository.deleteAll();
    }


    @Test
    @DisplayName("find a given TodoItem by its Id")
    void findATodoItemById() {
        // find a specific TodoItem by its id
        Todoitem item = new Todoitem();
        item.setDescription("todo item1");
        item.setComplete(false);

        item = service.save(item);

        Optional<Todoitem> testItem = service.getById(item.getId());
        assertEquals(testItem.isPresent(), true);
        assertEquals(testItem.get().getId(), item.getId());
    }

    @Test
    void getAllTodoItems() {

        Todoitem item1 = new Todoitem();
        item1.setDescription("todo item1");
        item1.setComplete(false);

        item1 = service.save(item1);

        Todoitem item2 = new Todoitem();
        item2.setDescription("todo item1");
        item2.setComplete(false);

        item2 = service.save(item2);

        Iterable<Todoitem> items = service.getAll();
        List<Todoitem> list = new ArrayList<>();
        items.iterator().forEachRemaining(list::add);
        assertNotEquals(list.size(), 0);
        assertEquals(list.size(), 2);
        assertEquals(list.get(0).getId(), item1.getId());
        assertEquals(list.get(1).getId(), item2.getId());
    }

    @Test
    void savingAValidTodoItemSucceeds() {
        Todoitem item = new Todoitem();
        item.setDescription("todo item1");
        item.setComplete(false);

        item = service.save(item);
        assertNotEquals(item.getId(), null);
    }

    @Test
    void savingAnInvalidTodoItemFails() {

        Todoitem item = new Todoitem();
        Exception exception = assertThrows(Exception.class, () -> service.save(item));
        assertEquals("Could not commit JPA transaction", exception.getMessage());
    }

    @Test
    void deletingAValidTodoItemSucceeds() {
        Todoitem item = new Todoitem();
        item.setDescription("todo item1");
        item.setComplete(false);

        item = service.save(item);
        service.delete(item);

        Iterable<Todoitem> items = service.getAll();
        List<Todoitem> list = new ArrayList<>();
        items.iterator().forEachRemaining(list::add);
        assertEquals(list.size(), 0);
    }
}