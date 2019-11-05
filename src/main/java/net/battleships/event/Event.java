package net.battleships.event;

/**
 * Interface for an in-game event
 * @author Philip Damianik
 * @version 2019-11-05
 */

public interface Event {
	/**
	 * This method is for getting the events basic payload
	 * @return the events basic payload
	 */
	Object getEventData();
}
