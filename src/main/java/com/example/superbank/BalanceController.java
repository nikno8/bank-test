package com.example.superbank;

import com.example.superbank.model.TransferBalance;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController("/balance")
@AllArgsConstructor
@Slf4j
public final class BalanceController {
    private BankService bankService;
    @GetMapping("/{accountId}")
    public BigDecimal getBalance(@PathVariable Long accountId){
        return bankService.getBalance(accountId);

    }
    @PostMapping ("/add")
    public BigDecimal addMoney
            (@RequestBody TransferBalance transferBalance){

        return bankService.addMoney(transferBalance.getTo(), transferBalance.getAmount());
    }

    @PostMapping ("/transfer")
    public void transfer
            (@RequestBody TransferBalance transferBalance){
        bankService.makeTransfer(transferBalance);

    }
    @ExceptionHandler(IllegalArgumentException.class)
    public String handle(IllegalArgumentException e){
        log.error(e.getMessage());
        return "I'am broke";
    }

}
