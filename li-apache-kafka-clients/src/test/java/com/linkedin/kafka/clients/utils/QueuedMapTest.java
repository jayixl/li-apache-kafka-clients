/*
 * Copyright 2017 LinkedIn Corp. Licensed under the BSD 2-Clause License (the "License").  See License in the project root for license information.
 */

package com.linkedin.kafka.clients.utils;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Unit test for QueuedMap
 */
public class QueuedMapTest {

  @Test
  public void testPutAndGet() {
    QueuedMap<Integer, Integer> map = new QueuedMap<>();
    assertEquals(map.getEldestKey(), null, "Empty map should return null for eldest key.");
    assertEquals(map.size(), 0, "Map size should be 0");
    map.put(0, 0);
    assertEquals(map.size(), 1, "Map size should be 1 now");
    assertEquals((int) map.get(0), 0, "0 should be in the map now");
    assertEquals((int) map.getEldestKey(), 0, "Eldest key should be 0");
  }

  @Test
  public void testRemove() {
    QueuedMap<Integer, Integer> map = new QueuedMap<>();
    map.put(0, 0);
    // Remove non-exist key
    map.remove(1);
    assertEquals(map.size(), 1, "Map size should be 1");
    assertEquals((int) map.get(0), 0, "0 should be in the map");
    assertEquals((int) map.getEldestKey(), 0, "Eldest key should be 0");
    // Remove exist key
    map.remove(0);
    assertEquals(map.get(0), null, "0 should be in the map now");
    assertEquals(map.getEldestKey(), null, "Empty map should return null for eldest key.");
    assertEquals(map.size(), 0, "Map size should be 0");
  }

  @Test
  public void testEldestKey() {
    QueuedMap<Integer, Integer> map = new QueuedMap<>();
    assert map.getEldestKey() == null;
    map.put(0, 0);
    map.put(1, 1);
    assertEquals((int) map.getEldestKey(), 0, "Eldest key should be 0");
    map.remove(0);
    assertEquals((int) map.getEldestKey(), 1, "Eldest key should be 1 now");
    assertEquals(map.get(0), null, "0 should have been removed.");
    map.put(0, 0);
    assertEquals((int) map.getEldestKey(), 1, "Eldest key should be 1 now");
    map.put(1, 1);
    assertEquals((int) map.getEldestKey(), 0, "Eldest key should be 0 now");
  }
}
