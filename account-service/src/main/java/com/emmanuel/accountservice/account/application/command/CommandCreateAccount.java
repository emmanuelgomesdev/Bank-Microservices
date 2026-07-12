package com.emmanuel.accountservice.account.application.command;

import java.util.UUID;

public record CommandCreateAccount(
        UUID customerId

){}
