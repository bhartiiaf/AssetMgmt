package com.unifiedweb360.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.unifiedweb360.modal.master.MailMaster;
import com.unifiedweb360.service.MailService;

@RestController
@RequestMapping("api/send/mail")
@CrossOrigin("*")
public class MailRestController {

    @Autowired
    private MailService mailService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> sendMail(@Valid @RequestBody MailMaster mail, Errors errors){
        if(errors.hasErrors()){
            return new ResponseEntity<>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        return mailService.sendMail(mail);
    }

}