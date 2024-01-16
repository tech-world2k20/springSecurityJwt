package com.example.springSecurity.Service;

import com.example.springSecurity.Entity.Product;
import com.example.springSecurity.Entity.UserInfo;
import com.example.springSecurity.Repository.UserDetailsRepo;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
@Service
public class ProductService {
    @Autowired private UserDetailsRepo userDetailsRepo;
    @Autowired    PasswordEncoder passwordEncoder;

    @Getter
    List<Product> productList ;

    @PostConstruct
    public void loadProductsFromDB(){
        productList = IntStream.rangeClosed(1,50)
                .mapToObj(i -> Product.builder()
                        .id(i)
                        .name("product"+i)
                        .qty(new Random().nextInt(10))
                        .price(new Random().nextInt(5000))
                        .build()
                ).collect(Collectors.toList());
    }

    public Product getProductById(int id){
        return productList.stream()
                .filter(i -> i.getId()==id)
                .findAny()
                .orElseThrow(() -> new RuntimeException("product "+id +" not found"));
    }

    public String addUser(UserInfo userInfo){
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        userDetailsRepo.save(userInfo);
        return "user added successfully";
    }
}
