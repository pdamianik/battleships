package net.battleships.event;

public class TestHandler1 implements Handler {

	public void handle(TestEvent1 event) {
		System.out.println(event.getEventData());
	}

	@Override
	public boolean handle(Event event) {
		return event.getEventData().equals("Test1");
	}
}
