package net.battleships.event;

/**
 * Interface for an in-game event-handler
 * @author Philip Damianik
 * @version 2019-11-05
 */

public interface Handler {
	/**
	 * Used to handle events (is called when an event is fired)
	 * @param event the object of the event to handle
	 * @return if the handling was successful
	 */
	boolean handle(Event event);
}
