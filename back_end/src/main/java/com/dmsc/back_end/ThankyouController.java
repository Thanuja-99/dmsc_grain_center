package com.dmsc.back_end;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThankyouController {

    @GetMapping("/thankyou")
    public String thankyou(){
        return "Thank you for your Advice Halidha Mettiiiiiiiiiiiiii";
    }
}