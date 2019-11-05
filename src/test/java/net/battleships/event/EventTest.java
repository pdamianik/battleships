package net.battleships.event;

import org.junit.Test;

import static org.junit.Assert.*;

public class EventTest {
	@Test
	public void basicEventHandlerTest() {
		EventHandler testEventHandler = new EventHandler();

		TestHandler1 testHandler1 = new TestHandler1();

		TestEvent1 testEvent1 = new TestEvent1();

		testEventHandler.register(testHandler1, testEvent1.getClass());

		assertSame("Simple Handler didn't work!", true, testEventHandler.handle(testEvent1));
	}

	@Test
	public void dualEventHandleTest() {
		EventHandler testEventHandler = new EventHandler();

		TestHandler1 testHandler1 = new TestHandler1();
		TestHandler2 testHandler2 = new TestHandler2();

		TestEvent1 testEvent1 = new TestEvent1();
		TestEvent2 testEvent2 = new TestEvent2();

		testEventHandler.register(testHandler1, testEvent1.getClass());
		testEventHandler.register(testHandler2, testEvent2.getClass());

		assertSame("Simple Handler didn't work!", true, testEventHandler.handle(testEvent1));
		assertSame("Simple Handler didn't work!", true, testEventHandler.handle(testEvent2));
	}

	@Test
	public void dualEventHandlerOneEventDifferentPriorityTest() {
		EventHandler testEventHandler = new EventHandler();

		TestHandler1 testHandler1 = new TestHandler1();
		TestHandler3 testHandler3 = new TestHandler3();

		TestEvent1 testEvent1 = new TestEvent1();

		testEventHandler.register(testHandler1, testEvent1.getClass(), EventPriority.LOW);
		testEventHandler.register(testHandler3, testEvent1.getClass(), EventPriority.HIGH);

		assertSame("Simple Handler didn't work!", true, testEventHandler.handle(testEvent1));
	}
}
