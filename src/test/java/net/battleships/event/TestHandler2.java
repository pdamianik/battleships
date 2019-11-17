package net.battleships.event;

import javax.swing.*;

public class TestHandler2 implements Handler {
	@Override
	public boolean handle(Event event) {
		return event.getEventData().equals("Test2");
	}
}
