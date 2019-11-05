package net.battleships.event;

public interface Handler {
	void handle(Event event);
	Event getEvent();
}
