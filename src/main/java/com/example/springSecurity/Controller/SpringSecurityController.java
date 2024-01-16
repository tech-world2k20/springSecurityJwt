package com.example.springSecurity.Controller;

import com.example.springSecurity.DTO.AuthRequest;
import com.example.springSecurity.Entity.Product;
import com.example.springSecurity.Entity.UserInfo;
import com.example.springSecurity.Service.JwtService;
import com.example.springSecurity.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class SpringSecurityController {

    @Autowired private ProductService productService;

    @Autowired private JwtService jwtService;
    @Autowired AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome(){
        return "welcome... this endpoint is not secured";
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<Product> getAllProducts(){
        return productService.getProductList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public Product getProductById(@PathVariable int id){
        return productService.getProductById(id);
    }

    @PostMapping("/new")
    public String  addNewUser(@RequestBody UserInfo userInfo){
        return productService.addUser(userInfo);
    }

    @PostMapping("/generateToken")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) throws UserPrincipalNotFoundException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.generateToken(authRequest.getName());
        }else  throw new UsernameNotFoundException("Invalid user request");
    }

}
