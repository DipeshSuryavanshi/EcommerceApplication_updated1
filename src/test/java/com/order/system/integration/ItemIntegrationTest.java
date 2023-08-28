package com.order.system.integration;

import com.order.system.integration.entity.ItemTest;
import com.order.system.integration.entity.OrderTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("tests")
public class ItemIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testAddOrderedItem() {
        OrderTest order = new OrderTest(/* initialize order properties */);
        ResponseEntity<OrderTest> createResponse = restTemplate.postForEntity("http://localhost:" + port + "/api/orders", order, OrderTest.class);
        assertEquals(HttpStatus.OK, createResponse.getStatusCode());

        OrderTest createdOrder = createResponse.getBody();
        Long orderId = createdOrder.getOrderId();

        // Add ordered items to the order
        Set<ItemTest> itemsToAdd = new HashSet<>();
        itemsToAdd.add(new ItemTest(/* initialize item properties */));
        itemsToAdd.add(new ItemTest(/* initialize item properties */));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Set<ItemTest>> addItemsEntity = new HttpEntity<>(itemsToAdd, headers);
        ResponseEntity<Set<ItemTest>> addItemsResponse = restTemplate.exchange(
                "http://localhost:" + port + "/api/order-items/" + orderId,
                HttpMethod.POST, addItemsEntity, new ParameterizedTypeReference<Set<ItemTest>>() {});
        assertEquals(HttpStatus.OK, addItemsResponse.getStatusCode());

        Set<ItemTest> addedItems = addItemsResponse.getBody();
        assertEquals(1, addedItems.size());

        // You can also verify the details of added items if needed
    }

    @Test
    public void testGetAllListOfOrderedItemsById() {
        // Create an order first
        OrderTest order = new OrderTest(/* initialize order properties */);
        order.setOrderId(1L);
        order.setCustomerName("C1");
        order.setNumberOfItems(33);
        order.setOrderAmount(37);
        order.setCustomerAddress("CA1");
        ItemTest item=new ItemTest();
        item.setItemId(2L);
        ResponseEntity<OrderTest> createResponse = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/orders", order, OrderTest.class);
        assertEquals(HttpStatus.OK, createResponse.getStatusCode());

        OrderTest createdOrder = createResponse.getBody();
        Long orderId = createdOrder.getOrderId();

        // Add items to the order
        Set<OrderTest> itemsToAdd = new HashSet<>();
        itemsToAdd.add(new OrderTest(/* initialize item properties */));
        itemsToAdd.add(new OrderTest(/* initialize item properties */));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Set<OrderTest>> addItemsEntity = new HttpEntity<>(itemsToAdd, headers);
        ResponseEntity<Void> addItemsResponse = restTemplate.exchange(
                "http://localhost:" + port + "/api/order-items/" + orderId,
                HttpMethod.POST, addItemsEntity, Void.class);
        assertEquals(HttpStatus.OK, addItemsResponse.getStatusCode());

        // Retrieve the list of ordered items
        if(item.getItemId()!=null) {
            ResponseEntity<List<OrderTest>> getItemsResponse = restTemplate.exchange("http://localhost:" + port + "/api/order-items", HttpMethod.GET, null, new ParameterizedTypeReference<List<OrderTest>>() {});
            // ResponseEntity<List<OrderTest>> getItemsResponse = restTemplate.getForEntity("http://localhost:" + port + "/api/order-items/" + orderId,new ParameterizedTypeReference<List<OrderTest>>() {},orderId);
            assertEquals(HttpStatus.OK, getItemsResponse.getStatusCode());

            List<OrderTest> orderedItems = getItemsResponse.getBody();
            assertEquals(1, orderedItems.size());
        }
        // You can also verify the details of ordered items if needed
    }
    @Test
    public void testUpdateExistingOrderItem() {
        // Create an order first
        OrderTest order = new OrderTest(/* initialize order properties */);
        ResponseEntity<OrderTest> createResponse = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/orders", order, OrderTest.class);
        assertEquals(HttpStatus.OK, createResponse.getStatusCode());

        OrderTest createdOrder = createResponse.getBody();
        Long orderId = createdOrder.getOrderId();

        // AdId an item to the order
        ItemTest newItem = new ItemTest(/* initialize item properties */);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ItemTest> addItemEntity = new HttpEntity<>(newItem, headers);
        ResponseEntity<ItemTest> addItemResponse = restTemplate.exchange(
                "http://localhost:" + port + "/api/order-items/" + orderId,
                HttpMethod.PUT, addItemEntity, ItemTest.class);
        assertEquals(HttpStatus.OK, addItemResponse.getStatusCode());

        ItemTest addedItem = addItemResponse.getBody();
        Long itemId = addedItem.getItemId();
         newItem.setItemId(2L);

        // Update the existing ordered item
        ItemTest updatedItem = new ItemTest(/* updated item properties */);
        updatedItem.setItemId(itemId);
        updatedItem.setItemName("updated item");
        HttpHeaders updateHeaders = new HttpHeaders();
        updateHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ItemTest> updateItemEntity = new HttpEntity<>(updatedItem, updateHeaders);
       // ResponseEntity<ItemTest> updateItemResponse = restTemplate.exchange("http://localhost:" + port + "/api/order-items/" + orderId, HttpMethod.PUT, updateItemEntity, ItemTest.class);
        ResponseEntity<ItemTest> updateItemResponse = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/order-items/" + orderId, order, ItemTest.class);
        assertNotEquals(HttpStatus.OK, updateItemResponse.getStatusCode());

        ItemTest updatedItemDTO = updateItemResponse.getBody();
        assertEquals(updatedItem.getItemId(), updatedItemDTO.getItemId());

        // You can also verify the details of the updated item if needed
    }
    @Test
    public void testDeleteOrderedItemById() {
        // Create an order first
        OrderTest order = new OrderTest(/* initialize order properties */);
        ResponseEntity<OrderTest> createResponse = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/orders", order, OrderTest.class);
        assertEquals(HttpStatus.OK, createResponse.getStatusCode());

        OrderTest createdOrder = createResponse.getBody();
        Long orderId = createdOrder.getOrderId();

        // Add an item to the order
        ItemTest newItem = new ItemTest(/* initialize item properties */);
        newItem.setItemId(2L);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ItemTest> addItemEntity = new HttpEntity<>(newItem, headers);
        ResponseEntity<ItemTest> addItemResponse = restTemplate.exchange(
                "http://localhost:" + port + "/api/order-items/" + orderId,
                HttpMethod.POST, addItemEntity, ItemTest.class);
        assertNotEquals(HttpStatus.OK, addItemResponse.getStatusCode());

        ItemTest addedItem = addItemResponse.getBody();
        Long itemId = addedItem.getItemId();

        // Delete the ordered item
        ResponseEntity<String> deleteResponse = restTemplate.exchange("http://localhost:" + port + "/api/order-items/" + newItem.getItemId(), HttpMethod.DELETE, null, String.class);
        assertEquals(HttpStatus.OK, deleteResponse.getStatusCode());
        assertEquals("Item deleted successfully", deleteResponse.getBody());

        // Verify that the item is deleted
//        ResponseEntity<ItemDTO> getItemResponse = restTemplate.getForEntity("http://localhost:" + port + "/api/order-items/" +  newItem.getItemId(), ItemDTO.class);
//        assertEquals(HttpStatus.NOT_FOUND, getItemResponse.getStatusCode());
    }

}
