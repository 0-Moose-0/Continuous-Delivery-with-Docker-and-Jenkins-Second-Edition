package com.leszko.calculator;

import com.hazelcast.client.config.ClientConfig;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the Calculator application.
 */
public class CalculatorTest {
     private Calculator calculator = new Calculator();

     @Test
     public void testSum() {
          assertEquals(5, calculator.sum(2, 3));
     }

     @Test
     public void testHazelcastClientConfig() {
          CalculatorApplication application = new CalculatorApplication();
          ClientConfig config = application.hazelcastClientConfig();

          assertEquals("hazelcast",
               config.getNetworkConfig().getAddresses().get(0));
     }
}