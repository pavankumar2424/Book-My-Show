package com.show.demo.Controllers;

import com.show.demo.DTOs.RequestDto.ShowDto;
import com.show.demo.DTOs.RequestDto.ShowSeatDto;
import com.show.demo.Services.ShowService;
import com.sun.net.httpserver.HttpsServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("show")
public class ShowController {

    @Autowired
    private ShowService showService;

    @PostMapping("/add")
    public ResponseEntity<String> addShow(@RequestBody ShowDto showDto){
        try{
            String res=showService.addShow(showDto);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/associate-seats")
    public ResponseEntity<String> associateSeats(@RequestBody ShowSeatDto showSeatDto){
        try {
            String res=showService.associateSeats(showSeatDto);
            return new ResponseEntity<>(res,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
