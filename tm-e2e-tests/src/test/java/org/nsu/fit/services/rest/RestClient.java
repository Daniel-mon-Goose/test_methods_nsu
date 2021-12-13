package org.nsu.fit.services.rest;

import com.mifmif.common.regex.Generex;
import org.glassfish.jersey.client.ClientConfig;
import org.nsu.fit.services.log.Logger;
import org.nsu.fit.services.rest.data.*;
import org.nsu.fit.shared.JsonMapper;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

public class RestClient {
    // Note: change url if you want to use the docker compose.
    //private static final String REST_URI = "http://localhost:8080/tm-backend/rest";
    private static final String REST_URI = "http://localhost:8089/tm-backend/rest";

    private final static Client client = ClientBuilder.newClient(new ClientConfig().register(RestClientLogFilter.class));

    public AccountTokenPojo authenticate(String login, String pass) {
        CredentialsPojo credentialsPojo = new CredentialsPojo();

        credentialsPojo.login = login;
        credentialsPojo.pass = pass;

        return post("authenticate", JsonMapper.toJson(credentialsPojo, true), AccountTokenPojo.class, null);
    }

    public CustomerPojo createAutoGeneratedCustomer(AccountTokenPojo accountToken) {
        ContactPojo contactPojo = new ContactPojo();

        // Лабораторная 3: Добавить обработку генерацию фейковых имен, фамилий и логинов.
        // * Исследовать этот вопрос более детально, возможно прикрутить специальную библиотеку для генерации фейковых данных.
        Generex namesGenerator = new Generex("([A-Z][a-z]{2,12})");
        Generex loginsGenerator = new Generex("([a-zA-Z0-9_]{1,})[@]([a-zA-Z0-9]{1,})[\\.]([a-zA-Z0-9]{1,})");

        contactPojo.firstName = namesGenerator.random();
        contactPojo.lastName = namesGenerator.random();
        contactPojo.login = loginsGenerator.random();
        contactPojo.pass = "strongpass";

        return post("customers", JsonMapper.toJson(contactPojo, true), CustomerPojo.class, accountToken);
    }

    public EmptyPojo updateUserBalance(AccountTokenPojo accountToken) {
        TopUpBalancePojo topUpBalancePojo = new TopUpBalancePojo();
        topUpBalancePojo.customerId = accountToken.id;
        topUpBalancePojo.money = 50;

        return post( "/customers/top_up_balance", JsonMapper.toJson(topUpBalancePojo, false), EmptyPojo.class, accountToken);
    }

    public List<PlanPojo> getAvailablePlans(AccountTokenPojo accountToken) {
        List<PlanPojo> plans = new Vector<>();
        
        return get("/available_plans", JsonMapper.toJson(plans, true), List.class, accountToken);
    }

    public List<PlanPojo> getPlans(AccountTokenPojo accountToken) {
        List<PlanPojo> plans = new Vector<>();

        return get("/plans", JsonMapper.toJson(plans, true), List.class, accountToken);
    }

    public PlanPojo createPlan(AccountTokenPojo accountToken) {
        PlanPojo plan = new PlanPojo();
        plan.name = "Some gorgeous plan";
        plan.details = "As said, the plan's gorgeous";
        plan.fee = 100;

        return post("/plans", JsonMapper.toJson(plan, true), PlanPojo.class, accountToken);
    }

    public EmptyPojo deletePlan(AccountTokenPojo accountToken, UUID id) {
        EmptyPojo customer = new EmptyPojo();

        return delete("/plans/" + id.toString(), JsonMapper.toJson(customer, false),
                EmptyPojo.class, accountToken);
    }

    public List<SubscriptionPojo> getSubscriptions(AccountTokenPojo accountToken) {
        List<SubscriptionPojo> plans = new Vector<>();

        return get("/subscriptions", JsonMapper.toJson(plans, true), List.class, accountToken);
    }
    public List<SubscriptionPojo> getAvailableSubscriptions(AccountTokenPojo accountToken) {
        List<SubscriptionPojo> plans = new Vector<>();

        return get("/available_subscriptions", JsonMapper.toJson(plans, true), List.class, accountToken);
    }

    public SubscriptionPojo createSubscription(AccountTokenPojo accountToken, UUID planID) {
        SubscriptionPojo subscription = new SubscriptionPojo();
        subscription.customerId = accountToken.id;
        subscription.planId = planID;

        return post("/subscriptions", JsonMapper.toJson(subscription, true), SubscriptionPojo.class, accountToken);
    }

    public EmptyPojo deleteSubscription(AccountTokenPojo accountToken, UUID id) {
        EmptyPojo subscription = new EmptyPojo();

        return delete("/subscriptions/" + id.toString(), JsonMapper.toJson(subscription, false),
                EmptyPojo.class, accountToken);
    }

    public List<CustomerPojo> getUsers(AccountTokenPojo accountToken) {
        List<CustomerPojo> customers = new Vector<>();

        return get("/customers", JsonMapper.toJson(customers, true), List.class, accountToken);
    }

    public EmptyPojo deleteUser(AccountTokenPojo accountToken, UUID id) {
        EmptyPojo customer = new EmptyPojo();

        return delete("/customers/" + id.toString(), JsonMapper.toJson(customer, false),
                EmptyPojo.class, accountToken);
    }

    private static <R> R post(String path, String body, Class<R> responseType, AccountTokenPojo accountToken) {
        // Лабораторная 3: Добавить обработку Responses и Errors. Выводите их в лог.
        // Подумайте почему в filter нет Response чтобы можно было удобно его сохранить.
        Invocation.Builder request = client
                .target(REST_URI)
                .path(path)
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);


        if (accountToken != null) {
            request.header("Authorization", "Bearer " + accountToken.token);
        }

        String response = request.post(Entity.entity(body, MediaType.APPLICATION_JSON), String.class);

        Logger.info("RESPONCE: " + response);
        if (responseType == EmptyPojo.class) {
            return JsonMapper.fromJson("{}", responseType);
        }
        return JsonMapper.fromJson(response, responseType);
    }

    private static <R> R get(String path, String body, Class<R> responseType, AccountTokenPojo accountToken) {
        Invocation.Builder request = client
                .target(REST_URI)
                .path(path)
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);


        if (accountToken != null) {
            request.header("Authorization", "Bearer " + accountToken.token);
        }

        String response = request.get(String.class);

        Logger.info("RESPONCE: " + response);

        if (responseType == EmptyPojo.class) {
            return JsonMapper.fromJson("{}", responseType);
        }

        return JsonMapper.fromJson(response, responseType);
    }

    private static <R> R delete(String path, String body, Class<R> responseType, AccountTokenPojo accountToken) {
        Invocation.Builder request = client
                .target(REST_URI)
                .path(path)
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);


        if (accountToken != null) {
            request.header("Authorization", "Bearer " + accountToken.token);
        }

        String response = request.delete(String.class);

        Logger.info("RESPONCE: " + response);

        if (responseType == EmptyPojo.class) {
            return JsonMapper.fromJson("{}", responseType);
        }

        return JsonMapper.fromJson(response, responseType);
    }

    private static <R> R put(String path, String body, Class<R> responseType, AccountTokenPojo accountToken) {
        Invocation.Builder request = client
                .target(REST_URI)
                .path(path)
                .request(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);


        if (accountToken != null) {
            request.header("Authorization", "Bearer " + accountToken.token);
        }

        String response = request.put(Entity.entity(body, MediaType.APPLICATION_JSON), String.class);

        Logger.info("RESPONCE: " + response);

        if (responseType == EmptyPojo.class) {
            return JsonMapper.fromJson("{}", responseType);
        }

        return JsonMapper.fromJson(response, responseType);
    }

    private static class RestClientLogFilter implements ClientRequestFilter {
        @Override
        public void filter(ClientRequestContext requestContext) {
            if (requestContext.hasEntity()) {
                Logger.debug(requestContext.getEntity().toString());
            }
            Logger.info(requestContext.getMethod());
            MultivaluedMap<String, Object> headers = requestContext.getHeaders();
            headers.forEach((header, values) -> {
                for (Object value : values) {
                    Logger.info(header + ": " + value.toString());
                }
            });

            // Лабораторная 3: разобраться как работает данный фильтр
            // и добавить логирование METHOD и HEADERS.
        }
    }
}
