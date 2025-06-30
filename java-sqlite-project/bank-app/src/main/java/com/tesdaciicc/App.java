package com.tesdaciicc;

import com.tesdaciicc.data.dao.BankServiceDao;
import com.tesdaciicc.data.entity.BankService;

import java.sql.*;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        BankServiceDao bankServiceDao = new BankServiceDao();
        List<BankService> services = bankServiceDao.getAll();
        System.out.println("**** BANK SERVICES ****");
        System.out.println("\n*** GET_ALL ***");
        services.forEach(System.out::println);

    }
}
