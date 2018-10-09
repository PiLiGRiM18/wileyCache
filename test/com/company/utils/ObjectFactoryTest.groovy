package com.company.utils

import org.junit.Test

class ObjectFactoryTest extends GroovyTestCase {

    @Test
    void test() {
        ObjectFactory objectFactory = new ObjectFactory(100)
        println objectFactory.toString()
    }
}
