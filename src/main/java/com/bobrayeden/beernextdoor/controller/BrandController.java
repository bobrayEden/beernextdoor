package com.bobrayeden.beernextdoor.controller;

import com.bobrayeden.beernextdoor.entity.Brand;
import com.bobrayeden.beernextdoor.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class BrandController {

    @Autowired
    BrandRepository brandRepository;

    @GetMapping("/brands")
    public List<Brand> getAllBrands() {
        return brandRepository.findAll();
    }

    @GetMapping("/brands/{idBrand}")
    public Brand getBrandByIdBrand(@PathVariable Long idBrand) {

        Optional<Brand> brandOptional = brandRepository.findById(idBrand);
        if (brandOptional.isPresent()) {
            return brandOptional.get();
        }
        return null;
    }

    @PostMapping("/brands")
    public Brand postBrand(@RequestBody Brand brand) {
        return brandRepository.save(brand);
    }

    @PutMapping("/brands/{idBrand}")
    public Brand putBrand(@PathVariable Long idBrand, @RequestBody Brand brand) {

        Optional<Brand> brandOptional = brandRepository.findById(idBrand);
        if (brandOptional.isPresent()) {
            brand.setIdBrand(idBrand);
            return brandRepository.save(brand);
        }
        return null;
    }

    @DeleteMapping("/brands/{idBrand}")
    public boolean deleteBrand(@PathVariable Long idBrand) {

        Optional<Brand> brandOptional = brandRepository.findById(idBrand);
        if (brandOptional.isPresent()) {
            brandRepository.deleteById(idBrand);
            return true;
        }
        return false;
    }
}
