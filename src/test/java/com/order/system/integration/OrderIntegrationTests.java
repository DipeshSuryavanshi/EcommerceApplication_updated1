package com.order.system.integration;

import com.order.system.dto.OrderDTO;
import com.order.system.integration.entity.OrderTest;
import com.order.system.integration.repository.OrderTestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class OrderIntegrationTests {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private OrderTestRepository testRepository;

    // Create a new order
    @Test
    public void testAddOrderWithItems() {

        OrderTest order = new OrderTest(/* initialize order properties */);
        ResponseEntity<OrderTest> createResponse = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/orders", order, OrderTest.class);
        assertEquals(HttpStatus.OK, createResponse.getStatusCode());

    }

    // Retrieve the created order
    @Test
    public void testGetOrderWithItems() {
        OrderTest order = new OrderTest();
          order.setCustomerName("C1");
          order.setNumberOfItems(44);
          order.setOrderAmount(100);
          order.setCustomerAddress("vijay nagar");
        ResponseEntity<OrderTest> createResponse = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/orders", order, OrderTest.class);
        OrderTest createOrder=createResponse.getBody();
        assert createOrder != null;
        Long orderId = createOrder.getOrderId();

        ResponseEntity<OrderTest> getResponse = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/orders/"+orderId , OrderTest.class,order);
        assertEquals(2, orderId);

    }
    // Add assertions to verify the retrieved order

    // Update the order
    @Test
    void testUpdateOrderWithItems() {
        OrderDTO order = new OrderDTO(/* initialize order properties */);
        order.setOrderId(1L);
        order.setCustomerName("dipesh");
        order.setNumberOfItems(33);
        order.setOrderAmount(37);
        order.setCustomerAddress("vijay nagar");
        ResponseEntity<OrderDTO> createResponse = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/orders", order, OrderDTO.class);

        OrderDTO retrievedOrder = createResponse.getBody();
        retrievedOrder.setCustomerName(" Customer Updated");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<OrderDTO> updateEntity = new HttpEntity<>(retrievedOrder, headers);
       ResponseEntity<String> updateResponse = restTemplate.exchange("http://localhost:" + port + "/api/orders/" + order.getOrderId(), HttpMethod.PUT, updateEntity, String.class);

// Verify the response status code
        assertEquals(HttpStatus.OK, updateResponse.getStatusCode());

        // Verify the response status code and content
        assertEquals(HttpStatus.OK, updateResponse.getStatusCode());
        assertEquals("Order Updated Successfully", updateResponse.getBody());
    }



    // Delete the order
    @Test
    void testDeleteeOrderWithItems() { OrderTest order = new OrderTest(/* initialize order properties */);
        order.setOrderId(1L);
        order.setCustomerName("C1");
        order.setNumberOfItems(33);
        order.setOrderAmount(37);
        order.setCustomerAddress("CA1");
        ResponseEntity<OrderTest> createResponse = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/orders", order, OrderTest.class);

        ResponseEntity<String> deleteResponse = restTemplate.exchange("http://localhost:" + port + "/api/orders/" + order.getOrderId(), HttpMethod.DELETE, null, String.class);

        assertEquals(HttpStatus.OK, deleteResponse.getStatusCode());

        // Verify that the order is deleted
//        HttpStatusCode deletedId=deleteResponse.getStatusCode();
//        String s= deleteResponse.getBody().toString();
//        try {
//            ResponseEntity<OrderTest> deletedGetResponse = restTemplate.getForEntity(
//                    "http://localhost:" + port + "/api/orders/" + order.getOrderId(),
//                    OrderTest.class);
//
//            // This line should not be executed if the order was properly deleted
//            fail("Expected an exception or HTTP 404 response");
//        } catch (HttpClientErrorException ex) {
//            // Verify that the response status code is HTTP 404 (Not Found)
//            assertEquals(HttpStatus.NOT_FOUND, ex.getStatusCode());
//        }
    }

}
