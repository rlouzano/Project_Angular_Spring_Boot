package br.api.angular.crud_angular_spring.controllers;

import br.api.angular.crud_angular_spring.models.Todo;
import br.api.angular.crud_angular_spring.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/todos")
public class TodoResource {

    @Autowired
    private TodoService todoService;


    @GetMapping("/{id}")
    public ResponseEntity<Todo> findById(@PathVariable Integer id){
        Todo obj = todoService.findId(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/open")
    public ResponseEntity<List<Todo>> listOpen(){
        List<Todo> list = todoService.findAllOpen();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/close")
    public ResponseEntity<List<Todo>> listClose(){
        List<Todo> list = todoService.findAllClose();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping
    public ResponseEntity<List<Todo>>listAll(){
        List<Todo> list = this.todoService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @PostMapping
    public ResponseEntity<Todo> create(@RequestBody Todo obj){
        obj = todoService.create(obj);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        todoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Todo> update(@PathVariable Integer id, @RequestBody Todo obj){
        Todo newObj = todoService.update(id, obj);
        return ResponseEntity.ok().body(newObj);
    }

}
