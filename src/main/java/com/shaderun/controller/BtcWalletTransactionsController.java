package com.shaderun.controller;

import com.shaderun.api.HistoryRequestRange;
import com.shaderun.api.Result;
import com.shaderun.api.Transaction;
import com.shaderun.api.WalletHistoryResult;
import com.shaderun.exception.ApiWalletException;
import com.shaderun.exception.InternalApiWalletException;
import com.shaderun.service.BtcWalletTransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.function.Supplier;

@RestController
@RequestMapping("/v1/transaction")
public class BtcWalletTransactionsController {

    private BtcWalletTransactionsService transactionsService;

    public BtcWalletTransactionsController(){}

    @Autowired
    public BtcWalletTransactionsController(BtcWalletTransactionsService transactionsService){
        this.transactionsService = transactionsService;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<Result> saveTransact(@RequestBody Transaction transaction) {
        return new ResponseEntity<>(
                BtcWalletTransactionsController.callFunction(()->transactionsService.saveTransaction(transaction)),
                HttpStatus.CREATED
        );
    }

    @GetMapping(value = "/history")
    public ResponseEntity<WalletHistoryResult> getHistoryTransactions(@RequestBody HistoryRequestRange range){
        return new ResponseEntity<>(
                BtcWalletTransactionsController.callFunction(() -> transactionsService.getWalletHistory(range)),
                HttpStatus.OK
        );
    }

    public static <T> T callFunction(Supplier<T> functionToCall){
        T res;
        try {
            res = functionToCall.get();
        } catch (ApiWalletException e) {
            throw e;
        } catch (Exception ex) {
            throw new InternalApiWalletException(ex.getMessage(), ex.getCause());
        }
        return res;
    }


}