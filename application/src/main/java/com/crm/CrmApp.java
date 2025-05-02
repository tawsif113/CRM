package com.crm;


import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = {
        "com.crm"
})
@RestController
public class CrmApp {
    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(CrmApp.class, args);
    }

    private final PrintName printName;
    public CrmApp(PrintName printName) {
        this.printName = printName;
    }

    @GetMapping("/")
    public String index() {
        return "Hello World! And We r Starting a "+ printName.print() + " Application";
    }
}