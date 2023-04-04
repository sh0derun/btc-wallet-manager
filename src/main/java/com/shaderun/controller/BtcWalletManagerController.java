package com.shaderun.controller;

import com.shaderun.api.Result;
import com.shaderun.api.Transaction;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BtcWalletManagerController {

    @PostMapping(value = "/savebtc")
    public Result saveTransaction(Transaction transaction){
        return new Result("Created", "okay");
    }

}