package com.bobrayeden.beernextdoor.controller;

import com.bobrayeden.beernextdoor.entity.*;
import com.bobrayeden.beernextdoor.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class TemplateController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BeerRepository beerRepository;

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    StoreRepository storeRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/login")
    public String toLog() {
        return "login";
    }

    @GetMapping("/sign-up")
    public String toSign(Model out,
                         @ModelAttribute User user) {
        out.addAttribute(user);
        return "signup";
    }

    @GetMapping("/main-page")
    public String toMain(Model out,
                         HttpSession session) {
        User user = (User) session.getAttribute("user");
        out.addAttribute("user", user);

        List<Brand> brands = brandRepository.findAll();
        out.addAttribute("brands", brands);

        List<Type> types = typeRepository.findAll();
        out.addAttribute("types", types);

        List<Beer> beers = beerRepository.findAll();
        out.addAttribute("beers", beers);

        List<Store> stores = storeRepository.findAll();
        out.addAttribute("stores", stores);

        List<Float[]> storeCoords = new ArrayList<>();
        for (Store store : stores) {
            storeCoords.add(store.getCoord());
        }
        out.addAttribute("storeCoords", storeCoords);

        return "main";
    }

    @GetMapping("/search-beer")
    public String searchByBeer(Model out,
                               HttpSession session,
                               @RequestParam Long idBeer) {
        User user = (User) session.getAttribute("user");
        out.addAttribute("user", user);

        List<Brand> brands = brandRepository.findAll();
        out.addAttribute("brands", brands);

        List<Type> types = typeRepository.findAll();
        out.addAttribute("types", types);

        List<Beer> beers = beerRepository.findAll();
        out.addAttribute("beers", beers);

        if (idBeer != null && idBeer != 0) {
            List<Store> stores = beerRepository.findById(idBeer).get().getStores();
            out.addAttribute("storeResults", stores);
            return "main";
        }
        return "redirect:/main-page";
    }

    @GetMapping("/search-type")
    public String searchByType(Model out,
                               HttpSession session,
                               @RequestParam Long idType) {
        User user = (User) session.getAttribute("user");
        out.addAttribute("user", user);

        List<Brand> brands = brandRepository.findAll();
        out.addAttribute("brands", brands);

        List<Type> types = typeRepository.findAll();
        out.addAttribute("types", types);

        List<Beer> beers = beerRepository.findAll();
        out.addAttribute("beers", beers);

        if (idType != null && idType != 0) {
            List<Store> stores = beerRepository.findById(idType).get().getStores();
            out.addAttribute("storeResults", stores);
            return "main";
        }
        return "redirect:/main-page";
    }


    @GetMapping("/search-brewery")
    public String searchByBrand(Model out,
                                HttpSession session,
                                @RequestParam Long idBrand) {
        User user = (User) session.getAttribute("user");
        out.addAttribute("user", user);

        List<Brand> brands = brandRepository.findAll();
        out.addAttribute("brands", brands);

        List<Type> types = typeRepository.findAll();
        out.addAttribute("types", types);

        List<Beer> beers = beerRepository.findAll();
        out.addAttribute("beers", beers);

        if (idBrand != null && idBrand != 0) {
            List<Store> stores = beerRepository.findById(idBrand).get().getStores();
            out.addAttribute("storeResults", stores);

            return "main";
        }
        return "redirect:/main-page";
    }

    @PostMapping("/connexion")
    public String connexion(@RequestParam String nameUser,
                            @RequestParam String password,
                            HttpSession session) {
        if (userRepository.findByNameUserAndPassword(nameUser, password).isPresent()) {
            User user = userRepository.findByNameUserAndPassword(nameUser, password).get();
            session.setAttribute("user", user);
            return "redirect:/main-page";
        } else if (userRepository.findByEmailAndPassword(nameUser, password).isPresent()) {
            User user = userRepository.findByEmailAndPassword(nameUser, password).get();
            session.setAttribute("user", user);
            return "redirect:/main-page";
        }
        return "login";
    }

    @PostMapping("/sign-in")
    public String signIn(Model out,
                         HttpSession session,
                         @RequestParam String confirmPass,
                         @ModelAttribute User user) {
        out.addAttribute("user", user);
        out.addAttribute("confirmPass", confirmPass);
        if (user.getNameUser() != null) {
            if (userRepository.findByNameUser(user.getNameUser()).isPresent()) {
                return "redirect:/sign-up";
            }
            if (user.getPassword().equals(confirmPass)) {
                userRepository.saveAndFlush(user);
                out.addAttribute("user", user);
                session.setAttribute("user", user);
                return "redirect:/main-page";
            }
        }
        return "redirect:/sign-up";
    }

    @PostMapping("/post-profile")
    public String postProfile(Model out,
                              HttpSession session,
                              @ModelAttribute User user,
                              @RequestParam(required = false) String nameUser,
                              @RequestParam(required = false) String email,
                              @RequestParam(required = false) String password) {
        out.addAttribute("user", session.getAttribute("user"));
        if (nameUser != null) {
            user.setNameUser(nameUser);
        }
        if (email != null) {
            user.setEmail(email);
        }
        if (password != null) {
            user.setPassword(password);
        }
        userRepository.saveAndFlush(user);
        session.setAttribute("user", user);

        return "redirect:/user-profile";
    }

    @PostMapping("/post-beer")
    public String postBeer(HttpSession session,
                           @RequestParam Long idBeer) {
        User user = (User) session.getAttribute("user");
        List<Beer> userBeers = userRepository.findById(user.getIdUser()).get().getFavBeers();
        if (idBeer != 0) {
            userBeers.add(beerRepository.findById(idBeer).get());
            user.setFavBeers(userBeers);
            userRepository.saveAndFlush(user);
            return "redirect:/user-profile";
        }
        return "redirect:/user-profile";
    }

    @PostMapping("/post-type")
    public String postType(HttpSession session,
                           @RequestParam Long idType) {
        User user = (User) session.getAttribute("user");
        List<Type> userTypes = userRepository.findById(user.getIdUser()).get().getFavTypes();
        if (idType != 0) {
            userTypes.add(typeRepository.findById(idType).get());
            user.setFavTypes(userTypes);
            userRepository.saveAndFlush(user);
            return "redirect:/user-profile";
        }
        return "redirect:/user-profile";
    }

    @GetMapping("/user-profile")
    public String toProfile(Model out,
                            HttpSession session) {
        List<Beer> beers = beerRepository.findAll();
        out.addAttribute("beers", beers);

        List<Type> types = typeRepository.findAll();
        out.addAttribute("types", types);

        User user = (User) session.getAttribute("user");
        out.addAttribute("user", user);

        List<Beer> userBeers = userRepository.findById(user.getIdUser()).get().getFavBeers();
        out.addAttribute("userBeers", userBeers);

        List<Type> userTypes = userRepository.findById(user.getIdUser()).get().getFavTypes();
        out.addAttribute("userTypes", userTypes);

        return "profile";
    }

    @GetMapping("/logout")
    public String logOut(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/";
    }


}