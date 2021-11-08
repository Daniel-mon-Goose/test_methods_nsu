package org.nsu.fit.tm_backend.operations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentMatchers;
import org.nsu.fit.tm_backend.database.data.CustomerPojo;
import org.nsu.fit.tm_backend.database.data.SubscriptionPojo;
import org.nsu.fit.tm_backend.manager.CustomerManager;
import org.nsu.fit.tm_backend.manager.SubscriptionManager;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StatisticOperationTest {
    // Лабораторная 2: покрыть юнит тестами класс StatisticOperation на 100%.
    private CustomerManager customerManager;
    private SubscriptionManager subscriptionManager;
    private List<UUID> customerIds;

    @BeforeEach
    void init() {
        customerManager = mock(CustomerManager.class);
        subscriptionManager = mock(SubscriptionManager.class);
    }

    @Test
    void testExecuteEmpty() {
        StatisticOperation operation = new StatisticOperation(customerManager, subscriptionManager,
                Collections.emptyList());
        StatisticOperation.StatisticOperationResult emptyResult = operation.Execute();
        assertEquals(0, emptyResult.overallBalance);
        assertEquals(0, emptyResult.overallFee);
    }

    @Test
    void testStatisticOperationsExceptions() {
        Exception firstException = assertThrows(IllegalArgumentException.class, () -> {
            StatisticOperation operation = new StatisticOperation(null, null, null);
        });
        assertEquals("customerManager", firstException.getMessage());

        Exception secondException = assertThrows(IllegalArgumentException.class, () -> {
            StatisticOperation operation = new StatisticOperation(customerManager, null, null);
        });
        assertEquals("subscriptionManager", secondException.getMessage());

        Exception thirdException = assertThrows(IllegalArgumentException.class, () -> {
            StatisticOperation operation = new StatisticOperation(customerManager, subscriptionManager, null);
        });
        assertEquals("customerIds", thirdException.getMessage());
    }

    @Test
    void testExecute() {
        List<CustomerPojo> dbCustomers = new ArrayList<>();
        customerIds = new ArrayList<>();

        int[] balances = {10000, 150};
        int balanceSum = Arrays.stream(balances).sum();

        Arrays.stream(balances).forEach((balance) -> {
            CustomerPojo dummyCustomer = new CustomerPojo();
            dummyCustomer.firstName = "John";
            dummyCustomer.lastName = "Wick";
            dummyCustomer.login = "john_wick@example.com";
            dummyCustomer.pass = "qwerty";
            dummyCustomer.balance = balance;

            dummyCustomer.id = UUID.randomUUID();
            dbCustomers.add(dummyCustomer);
            customerIds.add(dummyCustomer.id);
        });

        int[] fees = {50, 30, 40};
        int feeSum = Arrays.stream(fees).sum();

        List<SubscriptionPojo> dbSubscriptions = new ArrayList<>();
        Arrays.stream(fees).forEach((fee) -> {
            SubscriptionPojo dummySubscription = new SubscriptionPojo();
            dummySubscription.id = UUID.randomUUID();
            dummySubscription.planFee = fee;
            dbSubscriptions.add(dummySubscription);
        });

        when(customerManager.getCustomer(ArgumentMatchers.any(UUID.class))).thenAnswer(invocation ->
                dbCustomers.stream().filter(customer -> customer.id == invocation.getArgument(0))
                        .collect(Collectors.toList()).get(0));
        when(subscriptionManager.getSubscriptions(ArgumentMatchers.any(UUID.class))).thenAnswer(invocation ->
                dbSubscriptions.stream().filter(subscription -> subscription.customerId == invocation.getArgument(0))
                        .collect(Collectors.toList()));

        dbSubscriptions.get(0).customerId = dbCustomers.get(0).id;
        dbSubscriptions.get(1).customerId = dbCustomers.get(0).id;
        dbSubscriptions.get(2).customerId = dbCustomers.get(1).id;

        StatisticOperation operation = new StatisticOperation(customerManager, subscriptionManager, customerIds);
        StatisticOperation.StatisticOperationResult result = operation.Execute();

        assertEquals(balanceSum, result.overallBalance);
        assertEquals(feeSum, result.overallFee);
    }
}
