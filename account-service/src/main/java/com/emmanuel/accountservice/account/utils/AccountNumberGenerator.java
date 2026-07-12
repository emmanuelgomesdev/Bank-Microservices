package com.emmanuel.accountservice.account.utils;

import org.springframework.stereotype.Component;

@Component
public class AccountNumberGenerator {

   private static final String BRANCH = "0001";

   public String generate(Long sequence){
       return String.format("%08d", sequence);
   }

   public String defaultBranch(){
       return BRANCH;
   }
}
