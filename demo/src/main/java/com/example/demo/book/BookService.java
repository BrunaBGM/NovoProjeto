package com.example.demo.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.demo.user.User;

import jakarta.validation.Valid;

@Service
public class BookService {

    @Autowired
    BookRepository repository;

    public List<Book> findAll(){
        return repository.findAll();
    }

    public boolean delete(Long id) {
        var book = repository.findById(id);
        if(book.isEmpty()) return false;
        repository.deleteById(id);
        return true;
    }

    public void save(@Valid Book book) {
        repository.save(book);
    }

    public void decrement(Long id) {
                // buscar o livro no bd
                var optional = repository.findById(id);

                if (optional.isEmpty())
                    throw new RuntimeException("livro não encontrado");
        
                var book = optional.get();
        
                // salvar
                repository.save(book);
        
    }

    public void increment(Long id) {
           // buscar o livro no bd
        var optional = repository.findById(id);

        if (optional.isEmpty())
            throw new RuntimeException("livro não encontrado");

        var book = optional.get();

        // salvar
        repository.save(book);
    }

    public void cacthBook(Long id, OAuth2User user) {

        var optional = repository.findById(id);

        if (optional.isEmpty())
            throw new RuntimeException("livro não encontrado");

        var book = optional.get();

        if (book.getUser() != null)
            throw new RuntimeException("livro já atribuído");

        book.setUser(User.convert(user));

        repository.save(book);
    }

    public void dropBook(Long id, OAuth2User user) {
        var optional = repository.findById(id);

        if (optional.isEmpty())
            throw new RuntimeException("livro não encontrado");

        var book = optional.get();

        if (book.getUser() == null)
            throw new RuntimeException("livro não atribuído");
            

        book.setUser(null);

        repository.save(book);
    
    }
    
}