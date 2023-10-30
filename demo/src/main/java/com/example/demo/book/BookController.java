package com.example.demo.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService service;

    @Autowired
    MessageSource messageSource;

  @GetMapping
    public String index(Model model, @AuthenticationPrincipal OAuth2User user){
        model.addAttribute("username", user.getAttribute("name"));
        model.addAttribute("avatar_url", user.getAttribute("avatar_url"));
        model.addAttribute("books", service.findAll());
        return "book/index";
    }
    
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect){
        if (service.delete(id)){
            redirect.addFlashAttribute("success", messageSource.getMessage("book.delete.success", null, LocaleContextHolder.getLocale()));
        }else{
            redirect.addFlashAttribute("error", "Livro não encontrado");
        }
        return "redirect:/book";
    }

    @GetMapping("new")
    public String form(Book book){
        return "book/form";
    }

    @PostMapping
    public String create(@Valid Book book, BindingResult result, RedirectAttributes redirect){
        if (result.hasErrors()) return "book/form";
        
        service.save(book);
        redirect.addFlashAttribute("success", "Livro cadastrado com sucesso");
        return "redirect:/book";
    }
    
    @GetMapping("/dec/{id}")
    public String decrement(@PathVariable Long id){
        service.decrement(id);
        return "redirect:/book";
    }

    @GetMapping("/inc/{id}")
    public String increment(@PathVariable Long id){
        service.increment(id);
        return "redirect:/book";
    }

    @GetMapping("/catch/{id}")
    public String cacthBook(@PathVariable Long id, @AuthenticationPrincipal OAuth2User user){
        service.cacthBook(id, user);
        return "redirect:/book";
    }

    @GetMapping("/drop/{id}")
    public String dropBook(@PathVariable Long id, @AuthenticationPrincipal OAuth2User user){
        service.dropBook(id, user);
        return "redirect:/book";
    }

}