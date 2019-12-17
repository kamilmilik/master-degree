package com.example.masterdegree.trash;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/users")
public class UsersController {

    private UsersRepository usersRepository;

    public UsersController(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @GetMapping("/all")
    public List<Users> getAll(){
        return usersRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Users getById(@PathVariable("id") Integer id){
        return usersRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }
    @PutMapping("/put/{id}")
    public void modifyById(@PathVariable("id") Integer id, @Valid @RequestBody Users users){
        users.setId(id);
        usersRepository.save(users);
    }

    @PostMapping("/post")
    public Users createUser(@Valid @RequestBody Users users){
        usersRepository.save(users);
        return users;
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id){
        usersRepository.delete(usersRepository.findById(id).orElseThrow(() -> new RuntimeException()));
    }
}
