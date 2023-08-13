package com.codefarm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventProducerTest {

    EventProducer eventProducer = new EventProducer();

    @Test
    void sendEventTest(){
        eventProducer.sendEvent("My first event from super java project API... ");
    }
}