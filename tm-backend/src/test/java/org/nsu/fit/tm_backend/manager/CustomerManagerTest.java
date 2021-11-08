package org.nsu.fit.tm_backend.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.nsu.fit.tm_backend.database.data.ContactPojo;
import org.nsu.fit.tm_backend.database.data.TopUpBalancePojo;
import org.nsu.fit.tm_backend.manager.auth.data.AuthenticatedUserDetails;
import org.nsu.fit.tm_backend.shared.Authority;
import org.slf4j.Logger;
import org.nsu.fit.tm_backend.database.IDBService;
import org.nsu.fit.tm_backend.database.data.CustomerPojo;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

// Лабораторная 2: покрыть юнит тестами класс CustomerManager на 100%.

class CustomerManagerTest {
    private Logger logger;
    private IDBService dbService;
    private CustomerManager customerManager;

    private CustomerPojo createCustomerInput;

    @BeforeEach
    void init() {
        // Создаем mock объекты.
        dbService = mock(IDBService.class);
        logger = mock(Logger.class);

        // Создаем класс, методы которого будем тестировать,
        // и передаем ему наши mock объекты.
        customerManager = new CustomerManager(dbService, logger);
    }

    @Test
    void testCreateCustomer() {
        // настраиваем mock.
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "John";
        createCustomerInput.lastName = "Wick";
        createCustomerInput.login = "john_wick@example.com";
        createCustomerInput.pass = "Baba_Jaga";
        createCustomerInput.balance = 0;

        CustomerPojo createCustomerOutput = new CustomerPojo();
        createCustomerOutput.id = UUID.randomUUID();
        createCustomerOutput.firstName = "John";
        createCustomerOutput.lastName = "Wick";
        createCustomerOutput.login = "john_wick@example.com";
        createCustomerOutput.pass = "Baba_Jaga";
        createCustomerOutput.balance = 0;

        when(dbService.createCustomer(createCustomerInput)).thenReturn(createCustomerOutput);

        // Вызываем метод, который хотим протестировать
        CustomerPojo customer = customerManager.createCustomer(createCustomerInput);

        // Проверяем результат выполенния метода
        assertEquals(customer.id, createCustomerOutput.id);

        // Проверяем, что метод по созданию Customer был вызван ровно 1 раз с определенными аргументами
        verify(dbService, times(1)).createCustomer(createCustomerInput);

    }

    @Test
    void testCreateCustomerWithNullArgument_Right() {
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                customerManager.createCustomer(null));
        assertEquals("Argument 'customer' is null.", exception.getMessage());
    }

    @Test
    void testCreateCustomerWithNullPassword() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "John";
        createCustomerInput.lastName = "Wick";
        createCustomerInput.login = "john_wick@example.com";
        createCustomerInput.pass = null;
        createCustomerInput.balance = 0;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
        assertEquals("Field 'customer.pass' is null.", exception.getMessage());
    }

    @Test
    void testCreateCustomerWithNullLogin() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "John";
        createCustomerInput.lastName = "Wick";
        createCustomerInput.login = null;
        createCustomerInput.pass = "Baba_Jaga";
        createCustomerInput.balance = 0;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
        assertEquals("Field 'customer.login' is null.", exception.getMessage());
    }

    @Test
    void testCreateCustomerWithNullFirstName() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = null;
        createCustomerInput.lastName = "Wick";
        createCustomerInput.login = "john_wick@example.com";
        createCustomerInput.pass = "Baba_Jaga";
        createCustomerInput.balance = 0;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
        assertEquals("Field 'customer.firstName' is null.", exception.getMessage());
    }

    @Test
    void testCreateCustomerWithShortPassword() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "John";
        createCustomerInput.lastName = "Wick";
        createCustomerInput.login = "john_wick@example.com";
        createCustomerInput.pass = "123qwe";
        createCustomerInput.balance = 0;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
        assertEquals("Password is very easy.", exception.getMessage());
    }

    @Test
    void testAlmostShortCustomer() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "Jo";
        createCustomerInput.lastName = "Wi";
        createCustomerInput.login = "john_wick@example.com";
        createCustomerInput.pass = "Baba_J";
        createCustomerInput.balance = 0;

        CustomerPojo createCustomerOutput = new CustomerPojo();
        createCustomerOutput.id = UUID.randomUUID();
        createCustomerOutput.firstName = "Jo";
        createCustomerOutput.lastName = "Wi";
        createCustomerOutput.login = "john_wick@example.com";
        createCustomerOutput.pass = "Baba_J";
        createCustomerOutput.balance = 0;

        when(dbService.createCustomer(createCustomerInput)).thenReturn(createCustomerOutput);

        CustomerPojo customer = customerManager.createCustomer(createCustomerInput);

        assertEquals(customer.id, createCustomerOutput.id);

        verify(dbService, times(1)).createCustomer(createCustomerInput);

    }

    @Test
    void testAlmostLongCustomer() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "Johnjohnjohn";
        createCustomerInput.lastName = "Wickwickwick";
        createCustomerInput.login = "john_wick@example.com";
        createCustomerInput.pass = "Baba_JagaBab";
        createCustomerInput.balance = 0;

        CustomerPojo createCustomerOutput = new CustomerPojo();
        createCustomerOutput.id = UUID.randomUUID();
        createCustomerOutput.firstName = "Johnjohnjohn";
        createCustomerOutput.lastName = "Wickwickwick";
        createCustomerOutput.login = "john_wick@example.com";
        createCustomerOutput.pass = "Baba_JagaBab";
        createCustomerOutput.balance = 0;

        when(dbService.createCustomer(createCustomerInput)).thenReturn(createCustomerOutput);

        CustomerPojo customer = customerManager.createCustomer(createCustomerInput);

        assertEquals(customer.id, createCustomerOutput.id);

        verify(dbService, times(1)).createCustomer(createCustomerInput);

    }

    @Test
    void testShortFirstName() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "J";
        createCustomerInput.lastName = "Wick";
        createCustomerInput.login = "john_wick@example.com";
        createCustomerInput.pass = "Baba_Jaga";
        createCustomerInput.balance = 0;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
        assertEquals("FirstName's length should be more or equal 2 symbols and less or equal 12 symbols.", exception.getMessage());
    }

    @Test
    void testLongFirstName() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "JohnJohnJohnJ";
        createCustomerInput.lastName = "Wick";
        createCustomerInput.login = "john_wick@example.com";
        createCustomerInput.pass = "Baba_Jaga";
        createCustomerInput.balance = 0;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
        assertEquals("FirstName's length should be more or equal 2 symbols and less or equal 12 symbols.", exception.getMessage());
    }

    @Test
    void testInvalidSymbolFirstName() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "John1";
        createCustomerInput.lastName = "Wick";
        createCustomerInput.login = "john_wick@example.com";
        createCustomerInput.pass = "Baba_Jaga";
        createCustomerInput.balance = 0;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
        assertEquals("FirstName should start with uppercase letter and other than that contain only lowercase letters.", exception.getMessage());
    }

    @Test
    void testShortPassword() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "John";
        createCustomerInput.lastName = "Wick";
        createCustomerInput.login = "john_wick@example.com";
        createCustomerInput.pass = "Baba_";
        createCustomerInput.balance = 0;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
        assertEquals("Password's length should be more or equal 6 symbols and less or equal 12 symbols.", exception.getMessage());
    }

    @Test
    void testLongPassword() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "John";
        createCustomerInput.lastName = "Wick";
        createCustomerInput.login = "john_wick@example.com";
        createCustomerInput.pass = "Baba_JagaBaba";
        createCustomerInput.balance = 0;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
        assertEquals("Password's length should be more or equal 6 symbols and less or equal 12 symbols.", exception.getMessage());
    }

    @Test
    void testLowBalance() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "John";
        createCustomerInput.lastName = "Wick";
        createCustomerInput.login = "john_wick@example.com";
        createCustomerInput.pass = "Baba_Jaga";
        createCustomerInput.balance = -1;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
        assertEquals("Balance should be 0.", exception.getMessage());
    }

    @Test
    void testHighBalance() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "John";
        createCustomerInput.lastName = "Wick";
        createCustomerInput.login = "john_wick@example.com";
        createCustomerInput.pass = "Baba_Jaga";
        createCustomerInput.balance = 1;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
        assertEquals("Balance should be 0.", exception.getMessage());
    }

    @Test
    void testInvalidLogin() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "John";
        createCustomerInput.lastName = "Wick";
        createCustomerInput.login = "john_wick";
        createCustomerInput.pass = "Baba_Jaga";
        createCustomerInput.balance = 0;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput));
        assertEquals("Customer's login should be an email address.", exception.getMessage());
    }

    @Test
    void testUsedLogin() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "John";
        createCustomerInput.lastName = "Wick";
        createCustomerInput.login = "used@example.com";
        createCustomerInput.pass = "Baba_Jaga";
        createCustomerInput.balance = 0;

        customerManager.createCustomer(createCustomerInput);
        List<CustomerPojo> list = new ArrayList<>();
        list.add(createCustomerInput);
        when(dbService.getCustomers()).thenReturn(list);

        CustomerPojo createCustomerInput1 = new CustomerPojo();
        createCustomerInput1.firstName = "Johne";
        createCustomerInput1.lastName = "Wicke";
        createCustomerInput1.login = "used@example.com";
        createCustomerInput1.pass = "Baba_Jagaa";
        createCustomerInput1.balance = 0;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.createCustomer(createCustomerInput1));
        assertEquals("Customer with this login already exist.", exception.getMessage());
    }

    @Test
    void testGetCustomers() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "John";
        createCustomerInput.lastName = "Wick";
        createCustomerInput.login = "john_wick@example.com";
        createCustomerInput.pass = "Baba_Jaga";
        createCustomerInput.balance = 0;

        customerManager.createCustomer(createCustomerInput);
        List<CustomerPojo> list = new ArrayList<>();
        list.add(createCustomerInput);
        when(dbService.getCustomers()).thenReturn(list);

        assertTrue(customerManager.getCustomers().contains(createCustomerInput));
        assertEquals(1, customerManager.getCustomers().size());
    }

    @Test
    void testGetCustomer() {
        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "John";
        createCustomerInput.lastName = "Wick";
        createCustomerInput.login = "john_wick@example.com";
        createCustomerInput.pass = "Baba_Jaga";
        createCustomerInput.balance = 0;

        CustomerPojo createCustomerOutput = new CustomerPojo();
        createCustomerOutput.id = UUID.randomUUID();
        createCustomerOutput.firstName = "John";
        createCustomerOutput.lastName = "Wick";
        createCustomerOutput.login = "john_wick@example.com";
        createCustomerOutput.pass = "Baba_Jaga";
        createCustomerOutput.balance = 0;

        when(dbService.getCustomer(createCustomerOutput.id)).thenReturn(createCustomerOutput);
        customerManager.createCustomer(createCustomerInput);

        assertEquals(customerManager.getCustomer(createCustomerOutput.id), createCustomerOutput);
    }

    @Test
    void testMeAdmin() {
        AuthenticatedUserDetails admin = new AuthenticatedUserDetails(UUID.randomUUID().toString(), "admin", Collections.singleton(Authority.ADMIN_ROLE));

        ContactPojo me = customerManager.me(admin);
        assertEquals("admin", me.login);
        assertNull(me.firstName);
        assertNull(me.lastName);
        assertNull(me.pass);
    }

    @Test
    void testMeUser() {
        AuthenticatedUserDetails user = new AuthenticatedUserDetails(UUID.randomUUID().toString(), "john_wick@example.com", Collections.singleton(Authority.CUSTOMER_ROLE));

        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "John";
        createCustomerInput.lastName = "Wick";
        createCustomerInput.login = "john_wick@example.com";
        createCustomerInput.pass = "Baba_Jaga";
        createCustomerInput.balance = 0;

        when(dbService.getCustomerByLogin("john_wick@example.com")).thenReturn(createCustomerInput);

        ContactPojo me = customerManager.me(user);
        assertEquals("john_wick@example.com", me.login);
        assertEquals("John", me.firstName);
        assertEquals("Wick", me.lastName);
        assertEquals("Baba_Jaga", me.pass);
        assertEquals(0, me.balance);
    }

    @Test
    void testMeUserType() {
        AuthenticatedUserDetails user = new AuthenticatedUserDetails(UUID.randomUUID().toString(), "john_wick@example.com", Collections.singleton(Authority.CUSTOMER_ROLE));

        createCustomerInput = new CustomerPojo();
        createCustomerInput.firstName = "John";
        createCustomerInput.lastName = "Wick";
        createCustomerInput.login = "john_wick@example.com";
        createCustomerInput.pass = "Baba_Jaga";
        createCustomerInput.balance = 0;

        when(dbService.getCustomerByLogin("john_wick@example.com")).thenReturn(createCustomerInput);
        assertEquals(ContactPojo.class, customerManager.me(user).getClass());
    }

    @Test
    void testDeleteCustomer() {
        UUID id = UUID.randomUUID();
        doNothing().when(dbService).deleteCustomer(id);

        customerManager.deleteCustomer(id);
                verify(dbService, times (1)).deleteCustomer(id);
    }

    @Test
    void testTopUpBalance() {
        UUID id = UUID.randomUUID();
        TopUpBalancePojo topBalance = new TopUpBalancePojo();
        topBalance.customerId = id;
        topBalance.money = 42;

        createCustomerInput = new CustomerPojo();
        createCustomerInput.balance = 0;
        when(dbService.getCustomer(id)).thenReturn(createCustomerInput);
        doNothing().when(dbService).editCustomer(createCustomerInput);

        customerManager.topUpBalance(topBalance);
        verify(dbService, times (1)).editCustomer(createCustomerInput);
    }

    @Test
    void testTopUpBalanceZero() {
        UUID id = UUID.randomUUID();
        TopUpBalancePojo topBalance = new TopUpBalancePojo();
        topBalance.customerId = id;
        topBalance.money = 0;

        createCustomerInput = new CustomerPojo();
        createCustomerInput.balance = 0;
        when(dbService.getCustomer(id)).thenReturn(createCustomerInput);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> customerManager.topUpBalance(topBalance));
        assertEquals("Field 'topUpBalancePojo.money' must be > 0.", exception.getMessage());
    }
}

